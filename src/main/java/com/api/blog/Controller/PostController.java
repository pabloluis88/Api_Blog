package com.api.blog.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable UUID id) {
        boolean deletado = service.deletarPorId(id);

        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostEntity> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok) // Se o Optional tiver valor, retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se for vazio, retorna 404 Not Found
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostEntity> atualizarPost(@PathVariable UUID id, @RequestBody PostEntity postAtualizado) {
        // Chama o Service para realizar a atualização
        return service.atualizarPost(id, postAtualizado)
                .map(ResponseEntity::ok) // Se o Optional tiver valor, retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se for vazio, retorna 404 Not Found
    }

    

}
