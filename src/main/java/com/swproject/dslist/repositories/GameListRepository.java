package com.swproject.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swproject.dslist.entities.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {
  
}
