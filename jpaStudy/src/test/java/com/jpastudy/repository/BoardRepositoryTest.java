package com.jpastudy.repository;

import com.jpastudy.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Insert
    @Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {     // 1~100까지의 게시글 반복 입력
            Board board = Board.builder()
                    .title("제목 - " + i )
                    .content(i + "번째 내용")
                    .author("작성자" + i)
                    .build();

            Board result = boardRepository.save(board);
            logger.info("bno : " + result.getBno());
        });
    }

    // Select
    @Test
    public void testSelect() {
        Long bno = 50L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();     // 값이 존재하지 않는 다면 예외처리
        logger.info(String.valueOf(board));
    }

    // Update
    @Test
    public void testUpdate() {
        // 게시글 불러오기
        Long bno = 50L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        // 수정
        board.change("제목 - 50 update", "50번째 내용 update");
        // 다시 저장
        boardRepository.save(board);
    }

    // 삭제
    @Test
    public void testDelete() {
        Long bno = 100L;
        boardRepository.deleteById(bno);
    }

    // Pagination
    // PageRequest.of(페이지번호, 사이즈) : 페이지 번호는 0부터
    // PageRequest.of(페이지번호, 사이즈, sort) : 정렬 조건 추가
    // PageRequest.of(페이지번호, 사이즈, sort, Direction, 속성...) : 정렬 방향과 여러 속성 지정
    @Test
    public void testPaging() {
        // 1page order by bno desc
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        logger.info("total count : " + result.getTotalElements());
        logger.info("total page : " + result.getTotalPages());
        logger.info("total number : " + result.getNumber());
        logger.info("page size : " + result.getSize());

        List<Board> boardList = result.getContent();
        boardList.forEach(board -> logger.info(String.valueOf(board)));
    }

    // 첨부파일 테스트
    @Test
    public void testInsertWithImages(){
        Board board = Board.builder()
                .title("Image Test")
                .content("Image Test")
                .author("Image Test")
                .build();

        for (int i = 0; i < 3; i++) {
            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }
        boardRepository.save(board);
    }

    // search1
    @Test
    public void testSearch1(){
        // 2page order by desc
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }

    // 검색
    @Test
    public void testSearchAll(){
        String [] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }
}
