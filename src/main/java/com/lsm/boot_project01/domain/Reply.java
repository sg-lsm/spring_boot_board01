package com.lsm.boot_project01.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board") // https://velog.io/@ckdgml1227/ToStringManyToOne
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
    private String replyText;
    private String commenter;
    public void changeText(String text){
        this.replyText = text;
    }
}
