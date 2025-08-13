package com.api.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.blog.Model.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, java.util.UUID>{

}
