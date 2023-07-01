package com.lsm.boot_project01.repository;

import com.lsm.boot_project01.domain.Board;
import com.lsm.boot_project01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}
