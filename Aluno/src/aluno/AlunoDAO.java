/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aluno;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class AlunoDAO extends BancoDados{
   
    final private String ALUNO_ID = "ra";
    final private String ALUNO_NOME = "nome";
    final private String ALUNO_CURSO = "curso";
    

    public void insertAluno(AlunoModel aluno) {
        
        Connection connection = conectar();
            
        String query = " INSERT INTO `aluno`(`nome` ,`curso`)"
                + " values (?, ?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getCurso());
            preparedStatement.execute();
            
            desconectar(connection);
            
            System.out.println("Dados gravador com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    public void selectAluno(int id){
        Connection connection = conectar();
        
        String query = "SELECT * FROM `aluno` WHERE ra = " + id;
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                System.out.println(
                        " RA:"      +resultSet.getString(ALUNO_ID)+
                        " Nome:"    +resultSet.getString(ALUNO_NOME) +
                        " Curso:"   +resultSet.getString(ALUNO_CURSO));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        
    }
    
    public List<AlunoModel> selectAlunos(){
        List<AlunoModel> list = new LinkedList<>();
        String nome;
        String curso;
        int ra;
        
        Connection connection = conectar();
        
        String query = "SELECT * FROM `aluno`" ;
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                
                ra = resultSet.getInt(ALUNO_ID);
                nome = resultSet.getString(ALUNO_NOME);
                curso = resultSet.getString(ALUNO_CURSO);
                
                list.add(new AlunoModel(ra, nome, curso));
            }
        } catch (SQLException e) {
            System.out.println("Erro na requisição da lista de alunos");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return list;
    }
    
    public void updateCurso(int ra, String curso){
        Connection connection = conectar();
        String query = "UPDATE `aluno` SET `salario` = ? WHERE `aluno`.`ra` = ?" ;
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setString(1,curso);
            ps.setInt(2, ra);
            ps.executeUpdate();
            System.out.println("Dados Atualizados com sucesso");
            selectAluno(ra);
            desconectar(connection);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o salario do aluno");
        }
    }
    
    public void deleteAluno(int ra){
        Connection connection = conectar();
        String query = "DELETE FROM `aluno` WHERE `aluno`.`ra` = ?" ;
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, ra);
            ps.executeUpdate();
            System.out.println("Dados Atualizados com sucesso");
            desconectar(connection);
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o salario do aluno");
        }
    }

}
