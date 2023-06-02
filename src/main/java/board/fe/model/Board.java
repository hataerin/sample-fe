package board.fe.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Board {
    private Long num;
    private String title;
    private String contents;
    private String writeName;

    private LocalDateTime writeDate;
    private LocalDateTime modifyDate;
}
