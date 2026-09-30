package com.example.first_project.api;

import com.example.first_project.com.example.repository.ArticleRepository;
import com.example.first_project.dto.ArticleForm;
import com.example.first_project.entity.Article;
import com.example.first_project.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("api/articles")
    public ResponseEntity<Article> Create(@RequestBody ArticleForm dto) {
        Article created  = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
//    @Autowired
//    private ArticleRepository articleRepository;

//
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        log.info("id : {}, article : {}",id,article.toString());
//
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target == null || id != article.getId()) {
//            log.info("잘못된 요청! id : {}, article : {}",id,article.toString());
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        target.patch(article);
//
//        Article updated = articleRepository.save(target);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    @GetMapping("/api/articles")
//    public ResponseEntity<List<Article>> index() {
//        List<Article> articleEntityList = articleRepository.findAll();
//        return ResponseEntity.status(HttpStatus.OK).body(articleEntityList);
//    }
//
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target == null) {
//            log.info("삭제 대상 찾을 수 없음");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        articleRepository.delete(target);
//
//        log.info("{} 삭제됨",target);
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }
}
