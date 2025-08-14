package com.api.blog.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.Model.PostEntity;
import com.api.blog.Service.PostService;


@RestController
@RequestMapping("/postagens")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping
    public ResponseEntity<PostEntity> criar(@RequestBody PostEntity postEntity) {
        PostEntity postagem = service.salvar(postEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(postagem);
    }

    @GetMapping
    public ResponseEntity<List<PostEntity>> listar() {
        List<PostEntity> posts = service.listarTodos();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/buscarPorPeriodo")
    public ResponseEntity<List<PostEntity>> buscarPorPeriodo(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        // Converte as datas de início e fim para o LocalDateTime
        LocalDateTime inicioDoDia = inicio.atStartOfDay();
        LocalDateTime fimDoDia = fim.atTime(LocalTime.MAX); // Define a hora como 23:59:59.999999999

        List<PostEntity> posts = service.buscarPorPeriodo(inicioDoDia, fimDoDia);

        return ResponseEntity.ok(posts);
    }
    

}
