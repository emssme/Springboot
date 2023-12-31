package com.jpastudy.repository.search;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);       // 검색 조건과 키워드
    Page<Board> searchPage(Pageable pageable, PageDTO pageDTO);
}
