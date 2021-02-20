package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Singleton é um padrão de projeto de software. Este padrão garante a existência de apenas
// uma instância de uma classe, mantendo um ponto global de acesso ao seu objeto.
public class ConnectionSingleton {

    private static Connection CONNECTION = null;

    public ConnectionSingleton(){

    }

    private static Connection createConnection() throws ClassNotFoundException, SQLException{
        // Para obter-se uma classe a partir de seu nome, você pode usar o método Class.
        // forName(String) . O newInstance() , é o método responsável por chamar o construtor
        // sem parâmetros de uma dada classe a partir do objeto Class correspondente.
        Class.forName("com.mysql.jdbc.Driver");
        CONNECTION = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/base de dados", "rostname", "password");
        return CONNECTION;
    }

    private static  boolean isExistsConnection(){
        return CONNECTION != null;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException{

        if(isExistsConnection()){
            return CONNECTION;
        }

        return createConnection();
    }
}

