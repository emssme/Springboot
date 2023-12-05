package com.jpastudy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String author;
    private String regDate;
    private String modDate;

    // 첨부파일의 이름들
    private List<String> fileNames;     // Board 에서 Set<BoardImage> 타입으로 변환되어야 함
}
