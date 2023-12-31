package com.jpastudy.repository;

import com.jpastudy.domain.Board;
import com.jpastudy.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {   // ( Entity 명, @Id 타입 )
    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.bno =:bno")
    Optional<Board> findByIdWithImages(@Param("bno") Long bno);     //@Param("bno") 빼면 TEST에서 오류남

    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}
