/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author internet
 */
abstract public class BancoDados {
    
    final private String URL = "jdbc:mysql://localhost:3306/aula1001";
    final private String USER = "root";
    final private String PASSWORD = "";
    private Connection mConnection;
    
    public Connection conectar() {
        try {
            mConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");
            return mConnection;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
        return null;
    }
    
    public void desconectar(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conecção fechada com sucesso");
            } else {
                System.out.println("Não existem conecções");
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }
    
}
