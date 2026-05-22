package com.example.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
