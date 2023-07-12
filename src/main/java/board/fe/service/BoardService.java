package board.fe.service;

import board.fe.common.config.WebClientConfig;
import board.fe.model.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

    private final WebClientConfig webClient;


    //게시글 전체 조회
    public Flux<Board> findAll(){
        return this.webClient.getWebClient()
                .get()
                .uri("/boards")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Board.class);
    };


    //게시글 상세 조회
    public Mono<Board> findById(Integer boardId) {
        return this.webClient.getWebClient()
                .get()
                .uri("/boards/" + boardId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Board.class);
    }

    //게시글 등록
    public Mono<Void> createBoard(Mono<Board> boardMono){

        return this.webClient.getWebClient()
                .post()
                .uri("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .body(boardMono, Board.class)
                .retrieve()
                .bodyToMono(Void.class);
    };

    //게시글 수정
    public Mono<Void> updateBoard(Integer boardId, Mono<Board> boardMono){
        this.webClient.getWebClient()
                .put()
                .uri("/boards/"+ boardId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(boardMono, Board.class)
                .retrieve()
                .bodyToMono(Void.class).subscribe();

        return Mono.empty();
    };

    //게시글 삭제
    public Mono<Void> deleteById(Integer boardId){
        return this.webClient.getWebClient()
                .delete()
                .uri("/boards/" + boardId)
                .retrieve()
                .bodyToMono(Void.class);
    };
}
