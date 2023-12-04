package com.jpastudy.service;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.BoardDTO;
import com.jpastudy.dto.PageDTO;
import com.jpastudy.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class BoardServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void registerTest(){
        logger.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("샘플 제목...")
                .content("샘플 내용...")
                .author("작성자1")
                .build();
        Long bno = boardService.register(boardDTO);
        logger.info("bno : " + bno);
    }

    @Test
    public void modifyTest(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(1L)
                .title("수정한 제목...")
                .content("수정한 내용...")
                .build();
        boardService.modify(boardDTO);
    }

    @Test
    public void testSearch(){
        PageDTO<Board, BoardDTO> pageDTO = new PageDTO<>();
        pageDTO.setType("title, content, author");
        pageDTO.setKeyword("7");
        Pageable pageable = pageDTO.getPageable();
        Page<Board> result = boardRepository.searchPage(pageable, pageDTO);
        pageDTO.build(result);
        pageDTO.entity2dto(result, BoardDTO.class);
        List<BoardDTO> boardList = pageDTO.getListDTO();
        boardList.forEach(b-> System.out.println("SEARCH RESULT: "+b));
    }
}
