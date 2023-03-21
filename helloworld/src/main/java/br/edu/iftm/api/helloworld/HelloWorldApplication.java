package br.edu.iftm.api.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@RequestMapping("/api/")
public class HelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
    @GetMapping
    public String helloWorld(){
        return "Olá, Mundo!";
    }

    @GetMapping("mars")
    public String helloMars(){
        return "Olá, Marte!";
    }

    @GetMapping("/moon")
    public String helloMoon(){
        return "Olá, Lua!";
    }

    @GetMapping("aluno")
    public ArrayList<Aluno> getAluno(){
        var listaAlunos = new ArrayList<Aluno>();
        listaAlunos.add(new Aluno("Braulio","21"));
        listaAlunos.add(new Aluno("Rodrigo","30"));
        listaAlunos.add(new Aluno("José Pereira","81"));
        listaAlunos.add(new Aluno("Tiago","33"));
        return listaAlunos;
    }

    @PostMapping("aluno")
    public Aluno saveAluno(@RequestBody Aluno aluno){
        Logger logger = Logger.getLogger(HelloWorldApplication.class.getName());
        logger.info("Aluno recebido: \n" + aluno.toString());
        return aluno;
    }
}
