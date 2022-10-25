package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //mysql Username
    private static final String USERNAME = "root";

    //DB password
    private static final String PASSWORD = "";

    //Caminho do banco de dados, porta, nome banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    /*
     * Conexão com o banco de dados
     */
    public static Connection createConnectionToMySQL() throws Exception {
        //faz com que a classe seja carregada pela JVM
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return  connection;
    }

    public static void main(String[] args) throws Exception {

        //Recuperar conexão com o banco de dados
        Connection con = createConnectionToMySQL();

        //Testar se a conexõ é nula
        if(con!=null) {
            System.out.println("Conexão obtida com sucesso!");
            con.close();
        }
    }
}
