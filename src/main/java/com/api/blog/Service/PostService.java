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

public Optional<PostEntity> atualizarPost(UUID id, PostEntity postAtualizado) {
        // 1. Buscamos o post no banco de dados.
        //    O retorno é um Optional, que previne NullPointerException.
        Optional<PostEntity> postOptional = repository.findById(id);
        
        // 2. Se o post existir, atualizamos seus dados.
        if (postOptional.isPresent()) {
            PostEntity postExistente = postOptional.get();
            postExistente.setTitulo(postAtualizado.getTitulo());
            postExistente.setConteudo(postAtualizado.getConteudo());
            postExistente.setAutor(postAtualizado.getAutor());

            // 3. Salvamos a entidade atualizada no banco de dados.
            //    O método save() é usado tanto para criar quanto para atualizar.
            repository.save(postExistente);
            
            return Optional.of(postExistente); // Retornamos o post atualizado em um Optional
        }
        
        // 4. Se o post não for encontrado, retornamos um Optional vazio.
        return Optional.empty();
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
