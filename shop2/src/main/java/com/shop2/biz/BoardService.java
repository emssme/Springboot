package com.shop2.biz;

import com.shop2.dto.BoardDTO;
import com.shop2.dto.PageRequestDTO;
import com.shop2.dto.PageResponseDTO;

public interface BoardService {
    public Long register(BoardDTO boardDTO);
    public BoardDTO selectOne(Long seq);
    public void modify(BoardDTO boardDTO);
    public void remove(Long seq);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
