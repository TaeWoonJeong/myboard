package com.twgroup.myboard.controller;

import com.twgroup.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String index(Model model, @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
        model.addAttribute("boards",boardService.listBoard(pageable));
        return "board/index.html";
    }

    @GetMapping("/board/write")
    public String write() {
        return "board/write.html";
    }

    @GetMapping("/board/detail/{id}")
    public String detail(@PathVariable int id,Model model) {
        model.addAttribute("board",boardService.detailBoard(id));
        return "board/detail.html";
    }

    @GetMapping("/board/update/{id}")
    public String update(@PathVariable int id,Model model) {
        model.addAttribute("board",boardService.detailBoard(id));
        return "board/update.html";
    }
}
