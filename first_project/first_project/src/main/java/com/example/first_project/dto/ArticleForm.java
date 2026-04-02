package com.example.first_project.dto;

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String content, String title) {
        this.content = content;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
