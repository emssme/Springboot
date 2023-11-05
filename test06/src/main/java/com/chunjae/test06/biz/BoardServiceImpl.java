package com.chunjae.test06.biz;

import com.chunjae.test06.entity.Board;
import com.chunjae.test06.per.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoard(int seq) {
        return boardMapper.getBoard(seq);
    }

    @Override
    public int boardInsert(Board board) {
        return boardMapper.boardInsert(board);
    }

    @Override
    public int boardUpdate(Board board) {
        return boardMapper.boardUpdate(board);
    }

    @Override
    public int boardDelete(int seq) {
        return boardMapper.boardDelete(seq);
    }
}
