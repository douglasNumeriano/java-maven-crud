package mapper.impl;

import entities.Article;
import mapper.ArticleRowMapper;
import mapper.AuthorRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleSetRowMapperImpl implements ArticleRowMapper<ResultSet> {

    private final AuthorRowMapper<ResultSet> autorRowMapper;

    public ArticleSetRowMapperImpl(AuthorRowMapper<ResultSet> autorRowMapper){
        this.autorRowMapper = autorRowMapper;
    }

    @Override
    public Article map(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getLong("id"));
        article.setTitle(resultSet.getString("title"));
        article.setDescription(resultSet.getString("description"));
        article.setPublicationDate(resultSet.getDate("publicationDate"));
        article.setAuthor(autorRowMapper.map(resultSet));
        return article;
    }
}
