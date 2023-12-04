package com.jpastudy.controller;

import com.jpastudy.domain.Board;
import com.jpastudy.dto.BoardDTO;
import com.jpastudy.dto.PageDTO;
import com.jpastudy.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequestMapping("/board/")
public class BoardCtrl {
    @Autowired
    private BoardService boardService;

    @RequestMapping("boardList")
    public String boardList(Model model, HttpServletRequest request){
        PageDTO<Board, BoardDTO> pageDTO = new PageDTO<>();

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int pageNow = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        pageDTO.setType(type);
        pageDTO.setKeyword(keyword);
        pageDTO.setPageNow(pageNow);

        pageDTO = boardService.boardList(pageDTO);
        List<BoardDTO> boardList = pageDTO.getListDTO();
        model.addAttribute("boardList", boardList);

        model.addAttribute("pageDTO", pageDTO);

        return "boardList";
    }
    @GetMapping("register")
    public String registerForm(){
        return "register";
    }
    @PostMapping("register")
    public String register(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board post register---------------------------");
        if (bindingResult.hasErrors()){
            log.info("has Error----------------------------------");
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result", bno);
        return "redirect:boardList";
    }
}
