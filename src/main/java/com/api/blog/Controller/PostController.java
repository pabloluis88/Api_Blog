package com.api.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    

}
