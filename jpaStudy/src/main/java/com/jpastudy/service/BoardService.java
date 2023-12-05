package com.jpastudy.service;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.BoardDTO;
import com.jpastudy.dto.PageDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    // 글 등록
    Long register(BoardDTO boardDTO);
    // 특정 게시글 조회
    BoardDTO readOne(Long bno);
    // 게시글 수정
    void modify(BoardDTO boardDTO);
    // 게시글 삭제
    void remove(Long bno);
    // 목록, 검색 기능
    PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO);

    // DTO 를 Entity 로 변환하기
    default Board dtoToEntity(BoardDTO boardDTO) {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .author(boardDTO.getAuthor())
                .build();
        if (boardDTO.getFileNames() != null) {
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");     // "_" 을 기준으로 파일명을 arr0, arr1 로 분리
                board.addImage(arr[0], arr[1]);
            });
        }
        return board;
    }

    // Entity 를 DTO 로 변환하기
    default BoardDTO entityToDTO(Board board) {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .regDate(String.valueOf(board.getRegDate()))
                .modDate(String.valueOf(board.getModDate()))
                .build();

        List<String> fileName = board.getImageSet().stream().sorted().map(boardImage ->
                boardImage.getUuid() + "_" + boardImage.getFileName()).collect(Collectors.toList());

        boardDTO.setFileNames(fileName);
        return boardDTO;
    }
    
}
