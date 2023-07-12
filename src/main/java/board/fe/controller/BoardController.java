package board.fe.controller;

import board.fe.common.util.PagerInfo;
import board.fe.model.Board;
import board.fe.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public Mono<String> list(PagerInfo pagerInfo, Model model) {
        Flux<Board> boardFlux = this.boardService.findAll();;

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        return Mono.just("board/list");
    }

    @GetMapping("/view")
    public Mono<String> view(
            @RequestParam Integer num, Model model
    ){
        Mono<Board> board = this.boardService.findById(num);

        model.addAttribute("board",board);

        return Mono.just("board/view");
    }

    @GetMapping("/write")
    public Mono<String> write() {
        return Mono.just("board/form");
    }

    @GetMapping(value = "/modifyForm")
    public Mono<String> edit(@RequestParam Integer num, Model model){
        Mono<Board> boardMono = this.boardService.findById(num);

        model.addAttribute("board", boardMono);

        return Mono.just("board/form");
    }

    @PostMapping(value = "/write/submit",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> writeSubmit(Mono<Board> board,PagerInfo pagerInfo, Model model) {
        this.boardService.createBoard(board).subscribe(); // * Execute subscribe for run save method first.

        Flux<Board> boardFlux = this.boardService.findAll();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // * Redirect url
        return Mono.just("redirect:/boards/list");
    }

    @PostMapping(value = "/modify/submit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<String> modifySubmit(Mono<Board> boardMono, PagerInfo pagerInfo, Model model) {
        boardMono.flatMap(board -> Mono.fromRunnable(() -> {
            log.debug("Received Board Id : {}",board.getNum());
            log.debug("Received Board Title : {}",board.getTitle());
            log.debug("Received Board Contents : {}",board.getContents());
            log.debug("Received Board Modify Name : {}",board.getModifyName());

            this.boardService.updateBoard(board.getNum(), boardMono);
        })).subscribe();

        Flux<Board> boardFlux = this.boardService.findAll();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // * Redirect url
        return Mono.just("redirect:/boards/list");
    }

    @GetMapping("/delete")
    public Mono<String> delete(@RequestParam Integer num,PagerInfo pagerInfo,Model model){

        this.boardService.deleteById(num).subscribe();

        Flux<Board> boardFlux = this.boardService.findAll();

        model.addAttribute("boardList", boardFlux);
        model.addAttribute("pagerInfo", pagerInfo);

        // Redirect url
        return Mono.just("redirect:/boards/list");
    }

}
