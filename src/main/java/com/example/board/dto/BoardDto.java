package com.example.board.dto;

import com.example.board.domain.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id,String title,String content,String writer,LocalDateTime createdDate,LocalDateTime modifiedDate){
        this.id=id;
        this.writer=writer;
        this.title=title;
        this.content=content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
