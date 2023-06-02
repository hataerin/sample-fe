package board.fe.feign;

import board.fe.model.Board;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PropertySource("classpath:application.yml")
@FeignClient(name = "boardClient", url = "${backend.url}") //profile에 작성한 주소 변수 입력
public interface BoardClient {

    //게시글 전체 조회
    @GetMapping("")
    public List<Board> findAll();

    //게시글 상세 조회
    @GetMapping("/{num}")
    public Board findById(@PathVariable Long num);

    //게시글 등록
    @PostMapping
    public Board create(@RequestBody Board board);

    //게시글 수정
    @PutMapping("/{num}")
    public Board update(@PathVariable Long num, @RequestBody Board board);

    //게시글 삭제
    @DeleteMapping("/{num}")
    public void deleteById(@PathVariable Long num);
}
