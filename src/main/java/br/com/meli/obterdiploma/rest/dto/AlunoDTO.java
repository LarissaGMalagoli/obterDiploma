package br.com.meli.obterdiploma.rest.dto;

import br.com.meli.obterdiploma.dao.AlunoDAO;
import br.com.meli.obterdiploma.rest.entity.Aluno;
import br.com.meli.obterdiploma.rest.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AlunoDTO {

    private String nome;
    private List<Disciplina> disciplinas = new ArrayList<>();

    public AlunoDTO(String nome) {
        super();
        this.nome = nome;
        //this.comodos = casa.getComodos();
    }

    public AlunoDTO(Aluno aluno) {
        super();
        this.nome = aluno.getNome();
        this.disciplinas = aluno.getDisciplinas();
    }

    public String getNome() {
        return nome;
    }

    public List<Disciplina> getDisciplinas(){ return disciplinas;}

    public static AlunoDTO converte(Aluno aluno) {
        return new AlunoDTO(aluno);//.getNome(), casa.getEndereco());
    }
    public static Aluno converte(AlunoDTO aluno, AlunoDAO dao) {
        return new Aluno(dao.getList().size()+1,aluno.getNome());
    }
    public static List<AlunoDTO> converte(List<Aluno> casas) {
        return casas.stream().map(a -> new AlunoDTO(a)).collect(Collectors.toList());
    }
}