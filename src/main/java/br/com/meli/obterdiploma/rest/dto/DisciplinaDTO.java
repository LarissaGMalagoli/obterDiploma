package br.com.meli.obterdiploma.rest.dto;

import br.com.meli.obterdiploma.rest.entity.Disciplina;

import java.util.List;
import java.util.stream.Collectors;

public class DisciplinaDTO {
    private String nome;
    private double nota;

    public DisciplinaDTO(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public static List<DisciplinaDTO> converteDisciplinas(List<Disciplina> disciplinas) {
        return disciplinas.stream().map(a -> new DisciplinaDTO(a.getNome(), a.getNota())).collect(Collectors.toList());
    }

    public static Disciplina converte(DisciplinaDTO disciplina) {
        return new Disciplina(disciplina.getNome(), disciplina.getNota());
    }
}

