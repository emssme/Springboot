package com.shop2.biz;

import com.shop2.domain.Board;
import com.shop2.dto.BoardDTO;
import com.shop2.dto.PageRequestDTO;
import com.shop2.dto.PageResponseDTO;
import com.shop2.per.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;

    private BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);       // boardDTO 를 Board의 class로 대치
        Long seq = boardRepository.save(board).getBno();
        return seq;
    }

    @Override
    public BoardDTO selectOne(Long seq) {
        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getSeq());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    @Override
    public void remove(Long seq) {
        boardRepository.deleteById(seq);
    }

    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] type = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("seq");
        //Page<Board> result = boardRepository.findAll(pageable);

        return null;
    }
}
