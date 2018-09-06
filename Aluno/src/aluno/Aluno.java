/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aluno;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author internet
 */
public class Aluno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AlunoModel alunoModel = new AlunoModel();
        
        AlunoDAO adao = new AlunoDAO();
        
//        adao.insertAluno(new AlunoModel("Fulano", "CC"));
//        adao.insertAluno(new AlunoModel("Fulano", "CC"));
//        adao.insertAluno(new AlunoModel("Fulano", "CC"));
//        adao.insertAluno(new AlunoModel("Fulano", "CC"));
//        adao.insertAluno(new AlunoModel("Fulano", "CC"));

          adao.selectAluno(0);
          adao.selectAluno(1);
          adao.selectAluno(2);
          
          adao.deleteAluno(0);
          adao.deleteAluno(1);
          adao.deleteAluno(2);
          
        adao.insertAluno(new AlunoModel("Fulano", "CC"));
        adao.insertAluno(new AlunoModel("Fulano", "CC"));
        adao.insertAluno(new AlunoModel("Fulano", "CC"));
        
        listaDeAlunos(adao);
        
        
    }
    
    private static void listaDeAlunos(AlunoDAO adao) {
        try {
            List<AlunoModel> alunos = new LinkedList<>();
           
            alunos = adao.selectAlunos();
            
            if(alunos != null){
                if(alunos.size() > 0){
                    for(AlunoModel aluno : alunos){
                        System.out.println(
                                " Id: "             + aluno.getRa() +
                                " Nome: "           + aluno.getNome()+
                                " Cargo: "          + aluno.getCurso()
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
