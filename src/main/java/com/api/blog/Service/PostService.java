package com.api.blog.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.Model.PostEntity;
import com.api.blog.Repository.PostRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public PostEntity salvar(PostEntity postEntity) {
        return repository.save(postEntity);
    }
    
    @Transactional(rollbackOn = Exception.class) 
    public List<PostEntity> listarTodos() {
        return repository.findAll();
    }

    public List<PostEntity> buscarPorDataCriacao(LocalDateTime dataCriacao) {
        return repository.findByDataCriacao(dataCriacao);
    }

    public List<PostEntity> buscarPorPeriodo(LocalDateTime inicioDoDia, LocalDateTime fimDoDia){
        return repository.findByDataCriacaoBetween(inicioDoDia, fimDoDia);

    }

    public Optional<PostEntity> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public boolean deletarPorId(UUID id) {
        // 1. Verificamos se o post existe antes de tentar deletá-lo
        Optional<PostEntity> postOptional = repository.findById(id);

        if (postOptional.isPresent()) {
            // 2. Se o post for encontrado, o deletamos
            repository.deleteById(id);
            return true; // Indicamos que a exclusão foi bem-sucedida
        }

        return false; // Indicamos que o post não foi encontrado
    }

}
