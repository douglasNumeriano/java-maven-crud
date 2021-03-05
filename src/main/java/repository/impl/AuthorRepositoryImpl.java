package repository.impl;

import entities.Author;
import mapper.AuthorRowMapper;
import repository.AuthorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class AuthorRepositoryImpl implements AuthorRepository {

    private static final  String FIND_ALL = "SELECT * FROM author";

    private static final String FIND_BY_ID = "SELECT * FROM author WHERE id = ?";

    private static final String UPDATE_BY_ID = "UPDATE author SET name = ?, bio = ?, birthdate = ? WHERE id = ? ";

    private static final String INSERT = "INSERT INTO author(name, bio, birthdate) VALUES(?,?,?) ";

    private static final String  DELETE = " DELETE FROM author WHERE id = ? ";

    private final Connection connection;

    private AuthorRowMapper<ResultSet> authorRowMapper;

    public AuthorRepositoryImpl(Connection connection, AuthorRowMapper<ResultSet> authorRowMapper){
        this.connection = connection;
        this.authorRowMapper = authorRowMapper;
    }

    @Override
    public Collection<Author> findAll() throws SQLException {
        Collection<Author> authors = null;

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_ALL)){

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                authors = new ArrayList<>();
                while(resultSet.next()){
                    authors.add(authorRowMapper.map(resultSet));
                }
            }
        }
        return authors;
    }

    @Override
    public Author findById(Long id) throws SQLException{
        Author author = null;
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    author = authorRowMapper.map(resultSet);
                }
            }
        }
        return author;
    }

   @Override
   public void updateById(Author object) throws SQLException{
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_BY_ID)){
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setString(3, object.getBio());
            preparedStatement.executeQuery();
        }
   }

    @Override
    public void insert(Author object) throws SQLException {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(INSERT)) {
            prepareStatement.setString(2, object.getName());
            prepareStatement.setString(3, object.getBio());
            prepareStatement.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (PreparedStatement prepareStatement = this.connection.prepareStatement(DELETE)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        }
    }
    

}
