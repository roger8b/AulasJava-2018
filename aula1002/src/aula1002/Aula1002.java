/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula1002;

import funcionario.Funcionario;
import funcionario.FuncionarioDAO;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Aula1002 {

    public static void main(String[] args) {
        FuncionarioDAO fdao = new FuncionarioDAO();
        Funcionario funcionario = new Funcionario();
        
        funcionario.setNome("Fulano");
        funcionario.setCargo("Developer");
        funcionario.setSalario((float) 1000.50);
        
        //fdao.inserirFuncionario(funcionario);
        
        listaDeFuncionarios(fdao);
        
        
        
        
    }

    private static void listaDeFuncionarios(FuncionarioDAO fdao) {
        try {
            List<Funcionario> funcionarios = new LinkedList<>();
           
            funcionarios = fdao.consultarFuncionarios();
            
            if(funcionarios != null){
                if(funcionarios.size() > 0){
                    for(Funcionario funcionario : funcionarios){
                        System.out.println(
                                " Id: "             + funcionario.getId() +
                                " Nome: "           + funcionario.getNome()+
                                " Cargo: "          + funcionario.getCargo() +
                                " Salario: "        + funcionario.getSalario()
                                );
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erro na requisição dos dados dos funcionarios");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    
}
