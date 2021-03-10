package com.twgroup.myboard.test;

import com.twgroup.myboard.model.Board;
import com.twgroup.myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/test/boards")
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @GetMapping("/test/board")
    public List<Board> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Board> boardsPage=boardRepository.findAll(pageable);
        List<Board> boards=boardsPage.getContent();
        return boards;
    }

    @Transactional
    @PutMapping("/test/board/{id}")
    public Board update(@PathVariable int id,@RequestBody Board requestBoard) {
        Board board=boardRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //boardRepository.save(board);
        return board;
    }

    @GetMapping("/test/board/{id}")
    public Board get(@PathVariable int id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(new Supplier<
                        IllegalArgumentException>() {
                    @Override
                    public IllegalArgumentException get() {
                        return new IllegalArgumentException("해당 유저는 없습니다.");
                    }
                });

        return board;
    }

    @PostMapping("/test/board/post")
    public String post(Board board) {
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        System.out.println(board.getCount());
        System.out.println(board.getContent());
        boardRepository.save(board);
        return "글쓰기 완료";
    }

    @DeleteMapping("/test/board/{id}")
    public String delete(@PathVariable int id) {
        try {
            boardRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            return "삭제 실패 해당 아이디가 DB에 없습니다.";
        }
        return "삭제되었습니다."+id;
    }

}
