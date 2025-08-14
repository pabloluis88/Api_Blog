package com.api.blog.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog.Model.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, java.util.UUID>{

    // Este método buscará posts criados em uma data e hora específicas
    List<PostEntity> findByDataCriacao(LocalDateTime dataCriacao);

    // Se você precisar de um intervalo de datas, pode usar Between
    List<PostEntity> findByDataCriacaoBetween(LocalDateTime start, LocalDateTime end);

    @SuppressWarnings("null")
    @Override
    Optional<PostEntity> findById(UUID id);


}
