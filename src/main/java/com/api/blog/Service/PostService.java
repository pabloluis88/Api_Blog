package com.api.blog.Service;

import java.time.LocalDateTime;
import java.util.List;

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
 

}
