package com.shop2.ctrl;

import com.shop2.biz.BoardService;
import com.shop2.dto.BoardDTO;
import com.shop2.dto.PageRequestDTO;
import com.shop2.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardCtrl {
    private final BoardService boardService;

    @GetMapping("list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", pageRequestDTO);
        return "board/list";
    }

    @GetMapping("register")
    public String register(Board board, Model model) {
        model.addAttribute("board", board);
        return "board/register";
    }

    @PostMapping("register")
    public String registerPro(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes rattr) {
        if (bindingResult.hasErrors()){
            rattr.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }
        Long seq = boardService.register(boardDTO);
        rattr.addAttribute("result", seq);
        return "redirect:/board/list";
    }

    @GetMapping("read")
    public String read(Long seq, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.selectOne(seq);
        model.addAttribute("dto", boardDTO);
        return "board/read";
    }

    @GetMapping("modify")
    public String modify(Long seq, PageRequestDTO pageRequestDTO, Model model){
        BoardDTO boardDTO = boardService.selectOne(seq);
        model.addAttribute("dto", boardDTO);
        return "board/modify";
    }

    @PostMapping("modify")
    public String modifyPro(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO , BindingResult bindingResult, RedirectAttributes rattr) {
        if (bindingResult.hasErrors()){
            String lnk = pageRequestDTO.getLink();
            rattr.addFlashAttribute("errors", bindingResult.getAllErrors());
            rattr.addAttribute("seq", boardDTO.getSeq());
            return "redirect:board/modify" + lnk;
        }
        boardService.modify(boardDTO);
        rattr.addFlashAttribute("result", "modified");
        rattr.addAttribute("seq", boardDTO.getSeq());
        return "redirect:board/list";
    }


}
