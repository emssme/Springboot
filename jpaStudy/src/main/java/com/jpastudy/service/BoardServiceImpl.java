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
        Board board = dtoToEntity(boardDTO);            // BoardDTO를 Board 객체로 변환
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        // board_image 까지 조인 처리되는 findByWithImages()를 이용
        Optional<Board> result = boardRepository.findByIdWithImages(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = entityToDTO(board);
        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());

        // 첨부파일의 처리
        board.clearImages();
        if (boardDTO.getFileNames() != null) {
            for (String fileName : boardDTO.getFileNames()) {
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            }
        }
        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
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
