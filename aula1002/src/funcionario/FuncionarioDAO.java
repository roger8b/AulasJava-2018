/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionario;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author internet
 */
public class FuncionarioDAO extends BancoDados{
   
    final private String FUNCIONARIO_ID = "id";
    final private String FUNCIONARIO_NOME = "nome";
    final private String FUNCIONARIO_CARGO = "cargo";
    final private String FUNCIONARIO_SALARIO = "salario";
    

    public void inserirFuncionario(Funcionario funcionario) {
        
        Connection connection = conectar();
            
        String query = " INSERT INTO `funcionario`(`nome` ,`cargo` ,`salario`)"
                + " values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCargo());
            preparedStatement.setDouble(3, funcionario.getSalario());
            preparedStatement.execute();
            
            desconectar(connection);
            
            System.out.println("Dados gravador com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public void consultarFuncionario(int id){
        Connection connection = conectar();
        
        String query = "SELECT * FROM `funcionario` WHERE id = " + id;
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                System.out.println(
                        " Id:"      +resultSet.getString(FUNCIONARIO_ID)+
                        " Nome:"    +resultSet.getString(FUNCIONARIO_NOME) +
                        " Cargo:"   +resultSet.getString(FUNCIONARIO_CARGO) +
                        " Salario:" +resultSet.getDouble(FUNCIONARIO_SALARIO));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
    }
    
    public List<Funcionario> consultarFuncionarios(){
        List<Funcionario> list = new LinkedList<>();
        String nome;
        String cargo;
        int id;
        double salario;
        
        Connection connection = conectar();
        
        String query = "SELECT * FROM `funcionario`" ;
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                
                id = resultSet.getInt(FUNCIONARIO_ID);
                nome = resultSet.getString(FUNCIONARIO_NOME);
                cargo = resultSet.getString(FUNCIONARIO_CARGO);
                salario = resultSet.getDouble(FUNCIONARIO_SALARIO);
                
                list.add(new Funcionario(id, nome, cargo, salario));
            }
        } catch (SQLException e) {
            System.out.println("Erro na requisição da lista de funcionarios");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return list;
    }
    
    public void atualizarSalarioFuncionario(int id, double salario){
        Connection connection = conectar();
        String query = "UPDATE `funcionario` SET `salario` = ? WHERE `funcionario`.`id` = ?" ;
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setDouble(1,salario);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Dados Atualizados com sucesso");
            consultarFuncionario(id);
            desconectar(connection);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o salario do funcionario");
        }
    }
    
    public void apagarFuncionario(int id){
        Connection connection = conectar();
        String query = "DELETE FROM `funcionario` WHERE `funcionario`.`id` = ?" ;
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Dados Atualizados com sucesso");
            desconectar(connection);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o salario do funcionario");
        }
    }

}
