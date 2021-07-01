package br.com.meli.obterdiploma.rest.entity;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private long id;
    private String nome;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public Aluno(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
        //System.out.println(comodos.get(0).getNome());
    }

}