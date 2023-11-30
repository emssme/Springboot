package com.shop2;

import com.shop2.domain.Board;
import com.shop2.per.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// 페이징 처리 4대 클래스
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest1 {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title - "+i)
                    .content("content ----- "+i)
                    .writer("user"+i)
                    .build();

            Board result = boardRepository.save(board);
            log.info("SEQ : " + result.getBno();
        });
    }

    // 100번째 글 선택
    @Test
    public void testSelectOne(){
        Long seq = 100L;
        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        log.info(board);
    }

    // 100번째 글 수정
    @Test
    public void testUpdate() {
        Long seq = 100L;
        Optional<Board> result = boardRepository.findById(seq);
        Board board = result.orElseThrow();
        board.change("update title 100", "update content 100" );
        boardRepository.save(board);
    }

    // 첫번째 글 삭제
    @Test
    public void testDelete(){
        Long seq = 1L;
        boardRepository.deleteById(seq);
    }

    //페이징
    @Test
    public void testSelectAll(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("seq").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("total count: " + result.getTotalElements());
        log.info("total pages: "+result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: "+result.getSize());

        List<Board> boardList = result.getContent();
        boardList.forEach(board -> log.info(board));
    }
}
