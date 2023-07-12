package board.fe.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private Integer num;
    private String title;
    private String contents;
    private Integer writeName;
    private Integer modifyName;

    private LocalDateTime writeDate;
    private LocalDateTime modifyDate;


}
