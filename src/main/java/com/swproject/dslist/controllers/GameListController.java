package com.swproject.dslist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swproject.dslist.dto.GameListDTO;
import com.swproject.dslist.dto.GameMinDTO;
import com.swproject.dslist.dto.ReplacementDTO;
import com.swproject.dslist.services.GameListService;
import com.swproject.dslist.services.GameService;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

  @Autowired
  private GameListService gameListService;

  @Autowired
  private GameService gameService;

  @GetMapping
  public List<GameListDTO> findAll() {
    return gameListService.findAll();
  }

  @GetMapping(value = "/{listId}/games")
  public List<GameMinDTO> findByList(@PathVariable Long listId) {
    return gameService.findByList(listId);
  }

// Query para verificar no SGBD se o método move está funcionando corretamente.
// SELECT TB_BELONGING  .*, TB_GAME.TITLE FROM TB_BELONGING 
// INNER JOIN TB_GAME ON TB_GAME.ID = TB_BELONGING .GAME_ID
// WHERE LIST_ID = 2
// ORDER BY POSITION
  @PostMapping(value = "/{listId}/replacement")
  public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
    gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
  }

}
