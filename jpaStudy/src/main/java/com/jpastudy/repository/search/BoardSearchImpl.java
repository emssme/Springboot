package com.jpastudy.repository.search;

import com.jpastudy.domain.Board;
import com.jpastudy.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;            // Q도메인 객체
        JPQLQuery<Board> query = from(board);   // select ... from board
//        query.where(board.title.contains("1")); // title 에 1이 포함된 게시글 검색

        // paging
        this.getQuerydsl().applyPagination(pageable, query);

        // search
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        // 제목 혹은(or) 내용에 대한 처리
        booleanBuilder.or(board.title.contains("11"));          // title like...
        booleanBuilder.or(board.content.contains("11"));        // content like...
        query.where(booleanBuilder);
        // bno가 0보다 큰 조건 추가
        query.where(board.bno.gt(0L));

        List<Board> list = query.fetch();       // fetch : JPQLQuery 를 실행할 때 이용
        long count = query.fetchCount();        // count 쿼리 실행

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        // 검색 조건과 키워드가 있다면
        if ((types != null && types.length > 0) && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t" :
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c" :
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w" :
                        booleanBuilder.or(board.author.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        // bno > 0
        query.where(board.bno.gt(0L));

        // paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();
        // PageImpl 을 이용한 Page<T> 반환
        // List<T> : 실제 목록 데이터
        // Pageable : 페이지 관련 정보를 가진 객체
        // long : 전체 개수
        return new PageImpl<>(list, pageable, count);
    }
}
