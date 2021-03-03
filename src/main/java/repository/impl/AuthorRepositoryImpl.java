package repository.impl;

import repository.AuthorRepository;

import java.sql.Connection;

public class AuthorRepositoryImpl implements AuthorRepository {

    private static final  String FIND_ALL = "SELECT * FROM AUTHOR ";

    private static final String FIND_BY_ID = "SELECT * FROM AUTHOR WHERE ID = ?";

    private static final String UPDATE_BY_ID = "UPDATE AUTHOR SET NAME = ?, BIO = ?, BIRTHDATE = ? WHERE ID = ? ";

    private static final String INSERT = "INSERT INTO AUTHOR(NAME, BIO, BIRTHDATE) VALUES(?,?,?) ";

    private static final String  DELETE = " DELETE FROM AUTHOR WHERE ID = ? ";

    private final Connection connection;
}
