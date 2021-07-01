package br.com.meli.obterdiploma.dao;

import br.com.meli.obterdiploma.rest.entity.Aluno;
import br.com.meli.obterdiploma.rest.entity.Disciplina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class AlunoDAO {
    //private static List<Comodo> comodo1 = new ArrayList<Comodo>();
    //private static List<Comodo> comodo2 = new ArrayList<Comodo>();


    public AlunoDAO() {
    }

    private List<Aluno> alunos = new ArrayList<>(Arrays.asList(
            new Aluno(1, "joao")
    ));


    public List<Aluno> getList(){
        return alunos;
    }

    public void adicionar(Aluno aluno) {
        alunos.add(aluno);
    }

    public Aluno get(long id) {
        Optional<Aluno> alunoOpt = alunos.stream().filter(a -> a.getId() == id).findFirst();
        if(alunoOpt.isPresent()) {
            return alunoOpt.get();
        }
        return null;
    }

    public void addDisciplina(long id, Disciplina disciplina){
        for (Aluno a: alunos) {
            if(a.getId()==id){
                a.setDisciplina(disciplina);
            }
        }

    }

}

