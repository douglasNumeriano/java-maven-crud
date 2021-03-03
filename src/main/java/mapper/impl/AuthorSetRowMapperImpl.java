package mapper.impl;

import entities.Author;
import mapper.AuthorRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//ResultSet é uma interface utilizada pra guardar dados vindos de um banco de dados.
//Basicamente, ela guarda o resultado de uma pesquisa numa estrutura de dados que pode
// ser percorrida, de forma que você possa ler os dados do banco.
public class AuthorSetRowMapperImpl implements AuthorRowMapper<ResultSet> {


    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setName(resultSet.getString("nome"));
        author.setBio(resultSet.getString("bio"));
        author.setBirthDate(resultSet.getDate("birthDate"));
        return author;
    }
}
