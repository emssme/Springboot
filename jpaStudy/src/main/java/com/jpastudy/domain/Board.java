package com.jpastudy.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity{
    @Id                                                     // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // AUTO_INCREMENT
    private Long bno;                                       // 게시글 번호

    @Column(length = 500, nullable = false)                 // 컬럼의 길이와 null 값 허용 여부
    private String title;                                   // 제목

    @Column(length = 1000, nullable = false)
    private String content;                                 // 내용

    @Column(length = 100, nullable = false)
    private String author;                                  // 작성자

    public void change(String title, String content) {      // 제목과 내용을 변경하는 메소드
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "board",                          // 일대 다 관계
            cascade = {CascadeType.ALL},                    // 부모 Entity 인 Board 의 변경이 자식 Entity 인 BoardImage 에 영향을 미치도록 설정
            fetch = FetchType.LAZY,                         // 연관된 Entity 를 지연 로딩하여 필요할때 가져오도록 설정
            orphanRemoval = true)                           // 부모 Entity 인 Board 가 지워지면 자동으로 지워지도록 설정
    @Builder.Default
    @BatchSize(size = 20)                                   // 한 번에 가져올 Entity 의 수를 20으로 제한
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName) {
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this).ord(imageSet.size()).build();
        imageSet.add(boardImage);
    }
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }
}
