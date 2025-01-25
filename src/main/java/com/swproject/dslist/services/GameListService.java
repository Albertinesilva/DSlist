package com.swproject.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swproject.dslist.dto.GameListDTO;
import com.swproject.dslist.entities.GameList;
import com.swproject.dslist.projections.GameMinProjection;
import com.swproject.dslist.repositories.GameListRepository;
import com.swproject.dslist.repositories.GameRepository;

@Service
public class GameListService {

  @Autowired
  private GameListRepository gameListRepository;

  @Autowired
  private GameRepository gameRepository;

  @Transactional(readOnly = true)
  public List<GameListDTO> findAll() {
    List<GameList> result = gameListRepository.findAll();
    return result.stream().map(x -> new GameListDTO(x)).toList();
  }

  @Transactional
  public void move(Long listId, int sourceIndex, int destinationIndex) {
    List<GameMinProjection> list = gameRepository.searchByList(listId);
    GameMinProjection obj = list.remove(sourceIndex);
    list.add(destinationIndex, obj);
    int min = Math.min(sourceIndex, destinationIndex);
    int max = Math.max(sourceIndex, destinationIndex);

    // Outra forma de fazer o min e max sem usar o Math
    // int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
    // int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;

    for (int i = min; i <= max; i++) {
      gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
    }
  }

}
