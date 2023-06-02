package board.fe.controller;

import board.fe.common.util.PagerInfo;
import board.fe.model.Board;
import board.fe.service.BoardFeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class BoardFeController {

    private final BoardFeService boardFeService;

    //게시글 전체 조회
    @GetMapping("/boards")
    public String findAll(PagerInfo pagerInfo, Model model) {
        List<Board> boardList = this.boardFeService.findAll();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "board/list";
    }

    //게시글 상세 조회
    @GetMapping("/boards/{num}")
    public String findById(@PathVariable Long num, Model model) {
        Board board = this.boardFeService.findById(num);

        model.addAttribute("board", board);

        return "board/view";
    }

    //게시글 작성 페이지
    @GetMapping("write")
    public String write() {
        return "board/form";
    }

    //게시글 등록
    @PostMapping(value = "/write/submit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(Board request, PagerInfo pagerInfo, Model model) {
        Board board = this.boardFeService.create(request);

        List<Board> boardList = this.boardFeService.findAll();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "redirect:/boards";
    }


    //게시글 수정 페이지
    @GetMapping("/boards/modifyForm")
    public String modify(@RequestParam Long num, Model model) {
        Board board = this.boardFeService.findById(num);

        model.addAttribute("board", board);
        return "board/form";
    }

    //게시글 수정
    @PostMapping(value = "/boards/modify/submit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)//에러고친이유 찾아보기, 안쓰고 하는 방법 찾아보기
    public String modify(Board boardRequest, Model model) {
        Board board = this.boardFeService.update(boardRequest.getNum(), boardRequest); //num불필요

        board = this.boardFeService.findById(boardRequest.getNum());

        model.addAttribute("board", board);

        return "redirect:/boards/" + board.getNum();
    }


    //게시글 삭제 페이지
    @GetMapping("/boards/delete")
    public String delete(@RequestParam Long num, Model model, PagerInfo pagerInfo) {
        this.boardFeService.deleteById(num);

        List<Board> boardList = this.boardFeService.findAll();

        model.addAttribute("boardList", boardList);
        model.addAttribute("pagerInfo", pagerInfo);

        return "redirect:/boards";
    }


}
