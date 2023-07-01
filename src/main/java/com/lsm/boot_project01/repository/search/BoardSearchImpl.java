package com.lsm.boot_project01.repository.search;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.domain.QBoard;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard qBoard = QBoard.board; //Q도메인 객체

        JPQLQuery<Board> query = from(qBoard); // select.. from board
        query.where(qBoard.title.contains("1")); //where title like..

        this.getQuerydsl().applyPagination(pageable, query); // Querydsl 실행 시에 Pageable 처리 (paging)

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;

    }
}
