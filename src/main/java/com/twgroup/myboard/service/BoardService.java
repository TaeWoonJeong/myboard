package com.twgroup.myboard.service;

import com.twgroup.myboard.model.Board;
import com.twgroup.myboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public Integer writeBoard(Board board) {
        try{
            boardRepository.save(board);
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("글쓰기"+e.getMessage());
        }
        return -1;
    }

    @Transactional(readOnly=true)
    public Page<Board> listBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board detailBoard(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 아이디 못찾음");
                });
    }

    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Board requestBoard) {
        Board board=boardRepository.findById(id)
                .orElseThrow(()->{
                   return new IllegalArgumentException("글찾기 실패");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }
}
