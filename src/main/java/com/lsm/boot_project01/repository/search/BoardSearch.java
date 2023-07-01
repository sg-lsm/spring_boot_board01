package com.lsm.boot_project01.repository.search;

import com.lsm.boot_project01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1 (Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
