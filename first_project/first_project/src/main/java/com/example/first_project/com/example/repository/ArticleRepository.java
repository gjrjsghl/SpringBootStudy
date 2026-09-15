package com.example.first_project.com.example.repository;

import com.example.first_project.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface  ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();
}
