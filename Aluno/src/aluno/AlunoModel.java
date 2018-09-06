/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aluno;

/**
 *
 * @author internet
 */
public class AlunoModel {
    
    private int ra;
    private String nome;
    private String curso;

    public AlunoModel() {
    }

    public AlunoModel(int ra, String nome, String curso) {
        this.ra = ra;
        this.nome = nome;
        this.curso = curso;
    }

    public AlunoModel(String nome, String curso) {
        this.nome = nome;
        this.curso = curso;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void serCurso(String cargo) {
        this.curso = cargo;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int id) {
        this.ra = id;
    }
    
}
