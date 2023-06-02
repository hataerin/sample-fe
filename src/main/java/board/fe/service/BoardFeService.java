package board.fe.service;

import board.fe.feign.BoardClient;
import board.fe.model.Board;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Service
public class BoardFeService {

    private final BoardClient boardClient;

    public BoardFeService(BoardClient boardClient) {
        this.boardClient = boardClient;
    }

    //게시글 전체 조회
    public List<Board> findAll(){
        return boardClient.findAll();
    };


    //게시글 상세 조회
    public Board findById(@PathVariable Long num){
        return boardClient.findById(num);
    };

    //게시글 등록
    public Board create(Board board){
        return boardClient.create(board);
    };

    //게시글 수정
    public Board update(Long num, Board board){
        return boardClient.update(num, board);
    };

    //게시글 삭제
    public void deleteById(Long num){
        boardClient.deleteById(num);
    };
}
