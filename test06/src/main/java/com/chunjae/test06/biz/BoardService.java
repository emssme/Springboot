package com.chunjae.test06.biz;

import com.chunjae.test06.entity.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getBoardList();
    public Board getBoard(int seq);
    public int boardInsert(Board board);
    public int boardUpdate(Board board);
    public int boardDelete(int seq);
}
