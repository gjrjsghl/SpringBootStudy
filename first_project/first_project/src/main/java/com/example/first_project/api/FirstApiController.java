package com.example.first_project.api;

import com.example.first_project.com.example.repository.ArticleRepository;
import com.example.first_project.dto.ArticleForm;
import com.example.first_project.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FirstApiController {
    @Autowired
    private ArticleRepository articleRepository;


    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id : {}, article : {}",id,article.toString());

        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청! id : {}, article : {}",id,article.toString());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        target.patch(article);

        Article updated = articleRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<Article>> index() {
        List<Article> articleEntityList = articleRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(articleEntityList);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null) {
            log.info("삭제 대상 찾을 수 없음");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        articleRepository.delete(target);

        log.info("{} 삭제됨",target);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
