package com.lsm.boot_project01.repository;

import com.lsm.boot_project01.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
