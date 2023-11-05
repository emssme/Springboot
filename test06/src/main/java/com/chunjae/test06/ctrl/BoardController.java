package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.BoardService;
import com.chunjae.test06.entity.Board;
import com.chunjae.test06.excep.NoSuchDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board/")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("list.do")
    public String getBoardList(Model model) throws Exception {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

    @GetMapping("detail.do")
    public String getBoard(@RequestParam("seq") int seq, HttpServletRequest request, Model model) throws Exception {
        Board board = boardService.getBoard(seq);
        if(board==null){
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("board", board);
        return "board/getBoard";
    }

    @GetMapping("insert.do")
    public String boardInsertForm() throws Exception {
        return "board/boardInsert";
    }

    @PostMapping("insert.do")
    public String boardInsertPro(HttpServletRequest request, Board board, Model model) throws Exception{
        HttpSession session = request.getSession();
        board.setAuthor((String) session.getAttribute("sname"));
        int cnt = boardService.boardInsert(board);
        if (cnt == 0 ){
            throw new NoSuchDataException("No Insert Process Data");
        }
        return "redirect:/board/list.do";
    }

    @GetMapping("update.do")
    public String boardUpdateForm(@RequestParam("seq") int seq, HttpServletRequest request, Model model) throws Exception {
        Board board = boardService.getBoard(seq);
        model.addAttribute("board", board);
        return "board/boardUpdate";
    }

    @PostMapping("update.do")
    public String boardUpdatePro(Board board, HttpServletRequest request, Model model) throws Exception{
        int cnt = boardService.boardUpdate(board);
        if (cnt == 0 ){
            throw new NoSuchDataException("No Update Process Data");
        }
        return "redirect:/board/list.do";
    }

    @GetMapping("delete.do")
    public String boardDelete(@RequestParam("seq") int seq, Model model) throws Exception {
        int cnt = boardService.boardDelete(seq);
        if (cnt == 0 ){
            throw new NoSuchDataException("No Delete Process Data");
        }
        return "redirect:/board/list.do";
    }
}
