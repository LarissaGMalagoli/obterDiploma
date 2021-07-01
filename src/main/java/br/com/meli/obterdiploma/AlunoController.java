package br.com.meli.obterdiploma;



import br.com.meli.obterdiploma.dao.AlunoDAO;
import br.com.meli.obterdiploma.rest.dto.AlunoDTO;
import br.com.meli.obterdiploma.rest.dto.DisciplinaDTO;
import br.com.meli.obterdiploma.rest.entity.Aluno;
import br.com.meli.obterdiploma.rest.entity.Disciplina;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private AlunoDAO dao = new AlunoDAO();

    @GetMapping
    @RequestMapping("/teste")
    public AlunoDTO endPointTeste() {
        Disciplina disciplina = new Disciplina("ingles", 2.3);
        List<Disciplina> l1 = new ArrayList<>();
        //l1.add(comodo);
        Aluno aluno = new Aluno(1, "ana");
        dao.addDisciplina(1, disciplina);
        return AlunoDTO.converte(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastra(@RequestBody AlunoDTO alunoDTO, UriComponentsBuilder uriBuilder) {
        Aluno aluno = AlunoDTO.converte(alunoDTO, dao);
        dao.adicionar(aluno);
        URI uri = uriBuilder.path("/aluno/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<AlunoDTO> cadastraDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO disciplina) {
        dao.addDisciplina(id, disciplina.converte(disciplina));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public AlunoDTO obterAluno(@PathVariable long id) {
        Aluno aluno = dao.get(id);
        AlunoDTO alunoDTO = AlunoDTO.converte(aluno);
        return alunoDTO;
    }

    @GetMapping
    public List<AlunoDTO> obterAlunos() {
        List<Aluno> alunos = dao.getList();
        List<AlunoDTO> dtos = AlunoDTO.converte(alunos);
        return dtos;
    }

    @GetMapping("/{id}/notas")
    public String calculaMedia(@PathVariable long id) {
        List<Aluno> alunos = dao.getList();
        Aluno aluno = alunos.get(((int)id)-1);

        double media = 0;
        int totalDisciplinas = 0;
        AlunoDTO alunoDTO = AlunoDTO.converte(aluno);
        List<Disciplina> disciplinas = alunoDTO.getDisciplinas();
        for (Disciplina d : disciplinas){
            media+= d.getNota();
            totalDisciplinas++;
        }
        //}
        media=media/totalDisciplinas;
        if(media>9){
            return "Parabéns! Aluno: " + aluno.getNome() + " Média: " + media;
        }
        else{
            return "Média Abaixo de 9. Aluno: " + aluno.getNome() + " Média: " + media;
        }
    }

}
