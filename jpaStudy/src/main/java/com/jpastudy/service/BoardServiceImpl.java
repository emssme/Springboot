package com.jpastudy.service;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.BoardDTO;
import com.jpastudy.dto.PageDTO;
import com.jpastudy.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{
    private final ModelMapper modelMapper;

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO,Board.class);        // BoardDTO를 Board 객체로 변환
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    @Override
    public void remover(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO) {
        Pageable pageable = pageDTO.getPageable();
        Page<Board> result = boardRepository.searchPage(pageable, pageDTO);
        pageDTO.build(result);
        pageDTO.entity2dto(result, BoardDTO.class);
        return pageDTO;
    }
}
