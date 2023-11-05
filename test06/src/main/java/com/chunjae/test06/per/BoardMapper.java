package com.chunjae.test06.per;

import com.chunjae.test06.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardList();
    Board getBoard(int seq);
    int boardInsert(Board board);
    int boardUpdate(Board board);
    int boardDelete(int seq);
}
