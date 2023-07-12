package board.fe.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Integer num;
    private String title;
    private String contents;
    private String writeName;

    private String modifyName;

//    private LocalDateTime writeDate;
//    private LocalDateTime modifyDate;

    @Builder
    public Board(Integer num,
                 String title,
                 String contents,
                 String writeName,
                 String modifyName) {
        this.num = num;
        this.title = title;
        this.contents = contents;
        this.writeName = writeName;
        this.modifyName = modifyName;
//        this.writeDate = writeDate;
//        this.modifyDate = modifyDate;
    }
}
