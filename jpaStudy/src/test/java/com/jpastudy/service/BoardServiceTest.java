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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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

    @Test
    public void testRegisterWithImages(){
        logger.info(boardService.getClass().getName());
        BoardDTO boardDTO = BoardDTO.builder()
                .title("파일 업로드 테스트")
                .content("파일 업로드 테스트 내용")
                .author("작성자")
                .build();

        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID() + "_aaa.jpg",
                        UUID.randomUUID() + "_bbb.jpg",
                        UUID.randomUUID() + "_ccc.jpg"
                )
        );

        Long bno = boardService.register(boardDTO);
        logger.info("bno : " + bno);
    }

    // 게시글 조회
    @Test
    public void testReadAll(){
        Long bno = 106L;
        BoardDTO boardDTO = boardService.readOne(bno);
        logger.info(String.valueOf(boardDTO));
        for (String fileName : boardDTO.getFileNames()) {
            logger.info(fileName);
        }

    }

    // 게시글 수정
    @Test
    public void testModify(){
        // 변경에 필요한 데이터
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(106L)
                .title("수정_파일업로드 테스트")
                .content("수정+_파일업로드 테스트 내용")
                .build();

        // 첨부파일 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID() + "_zzz.jpg"));
        boardService.modify(boardDTO);
    }
    
    // 게시글 삭제
    @Test
    public void testRemoveAll(){
        Long bno = 106L;
        boardService.remove(bno);
    }
}
