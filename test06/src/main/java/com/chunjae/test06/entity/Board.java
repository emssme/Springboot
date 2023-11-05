package com.chunjae.test06.entity;

import lombok.Data;

@Data
public class Board {
    private int seq;
    private String title;
    private String content;
    private String author;
    private String regdate;
    private int visited;
    private int rec;
}
