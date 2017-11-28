
package br.edu.ifpb.infra.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {

                
//                String url = "jdbc:postgresql://host-banco:5432/dac-jsf";
//                String usuario = "postgres";
//                String senha = "12345";
                String url = "jdbc:postgresql://localhost:5432/dac-jsf";
                String usuario = "postgres";
                String senha = "12345";

                Class.forName("org.postgresql.Driver");

                connection = DriverManager.getConnection(url, usuario, senha);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
    
}
