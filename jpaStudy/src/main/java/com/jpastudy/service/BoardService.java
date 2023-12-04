package com.jpastudy.service;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.BoardDTO;
import com.jpastudy.dto.PageDTO;

public interface BoardService {
    // 글 등록
    Long register(BoardDTO boardDTO);
    // 특정 게시글 조회
    BoardDTO readOne(Long bno);
    // 게시글 수정
    void modify(BoardDTO boardDTO);
    // 게시글 삭제
    void remover(Long bno);
    // 목록, 검색 기능
    PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO);
}
