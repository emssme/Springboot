package com.shop.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    @Id //기본키
    private Long seq;
    @NotBlank(message = "제목을 반드시 입력하시길 바랍니다.") //not null(유효성 검증)
    private String title;
    @NotBlank(message = "저자를 반드시 입력하시길 바랍니다.") //not null(유효성 검증)
    private String creator;
    @NotNull
    @Size(min=2, message = "출판사는 두 글자 이상 입력하여야 합니다.")//최소 2글자
    private String publisher;

    private String pubDate; //제약조건 없음
}
