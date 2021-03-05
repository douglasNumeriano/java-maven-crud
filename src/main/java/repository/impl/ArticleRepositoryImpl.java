package repository.impl;

import entities.Article;
import entities.Author;
import mapper.ArticleRowMapper;
import repository.ArticleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ArticleRepositoryImpl implements ArticleRepository {

    private static final String FIND_ALL = "SELECT * FROM article";

    private static final String FIND_BY_ID = "SELECT * FROM article WHERE id = ?";

    private static final String UPDATE_BY_ID = "UPDATE article SET title = ?, description = ?, publicationDate = ?, author_id = ? WHERE id = ?";

    private static final String INSERT = "INSERT INTO article(title, description, publicationDate, author_id) VALUES(?,?,?,?) ";

    private static final String DELETE = "DELETE FROM article WHERE id = ?";

    private final ArticleRowMapper<ResultSet> articleRowMapper;

    private final Connection connection;

    public ArticleRepositoryImpl(Connection connection, final ArticleRowMapper<ResultSet> articleRowMapper) {
        this.connection = connection;
        this.articleRowMapper = articleRowMapper;
    }

    public Collection<Article> findAll() throws SQLException {
        Collection<Article> articles = null;
        try(PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_ALL)){
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                articles = new ArrayList<>();
                while (resultSet.next()) {
                    articles.add(articleRowMapper.map(resultSet));
                }
            }
        }
        return articles;
    }

    public Article findById(Long id) throws SQLException {
        Article article = null;
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setLong(1, id);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                while (resultSet.next()) {
                    article = articleRowMapper.map(resultSet);
                }
                resultSet.close();
                prepareStatement.close();
            }
        }
        return article;
    }

    public void updateById(Article object) throws SQLException {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(UPDATE_BY_ID)) {
            prepareStatement.setLong(1, object.getId());
            prepareStatement.setString(2, object.getTitle());
            prepareStatement.setString(3, object.getDescription());
            prepareStatement.setDate(4, new java.sql.Date(object.getPublicationDate().getTime()));
            prepareStatement.setLong(5, object.getAuthor().getId());
            prepareStatement.executeUpdate();
        }
    }

    public void insert(Article object) throws SQLException {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(INSERT)) {
            prepareStatement.setLong(1, object.getId());
            prepareStatement.setString(2, object.getTitle());
            prepareStatement.setString(3, object.getDescription());
            prepareStatement.setDate(4, new java.sql.Date(object.getPublicationDate().getTime()));
            prepareStatement.setLong(5, object.getAuthor().getId());
            prepareStatement.executeUpdate();
        }
    }

    public void deleteById(Long id) throws SQLException {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(DELETE)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        }
    }
}

