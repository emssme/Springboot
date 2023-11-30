package com.shop2.per;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchOne(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
