package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("board")
public class BoardController {
    private BoardService boardService;

    @GetMapping({"","/list"})
    public String list(Model model,@RequestParam(value="page",defaultValue="1") Integer pageNum){
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        return "board/list";
    }

    @GetMapping("/post")
    public String write(){
        return "board/write";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no,Model model){
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto",boardDTO);
        return "board/detail";
    }
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model){
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto",boardDTO);
        return "board/update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO){
        boardService.savePost(boardDTO);
        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no){
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    @GetMapping("/board/search")
    public String Search(@RequestParam(value = "keyword") String keyword, Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList",boardDtoList);

        return "board/list";
    }
}
