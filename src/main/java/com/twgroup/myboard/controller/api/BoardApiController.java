package com.twgroup.myboard.controller.api;

import com.twgroup.myboard.dto.ResponseDto;
import com.twgroup.myboard.model.Board;
import com.twgroup.myboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/board/api")
    public ResponseDto<Integer> save(@RequestBody Board board) {
        //System.out.println("호출됨");
        int result=boardService.writeBoard(board);
        //System.out.println(result);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/board/api/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updateBoard(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/board/api/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deleteBoard(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
