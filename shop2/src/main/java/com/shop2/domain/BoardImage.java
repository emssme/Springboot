package com.shop2.domain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage>{
    @Id
    private String uuid;
    private String fileName;
    private int ord;
    @ManyToMany
    private Board board;

    @Override
    public int compareTo(BoardImage other) {
        return this.ord = other.ord;
    }
    public void changeBoard(Board board) {
        this.board = board;
    }
}
