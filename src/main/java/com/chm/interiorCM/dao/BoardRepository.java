package com.chm.interiorCM.dao;

import com.chm.interiorCM.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByName(String name);

}
