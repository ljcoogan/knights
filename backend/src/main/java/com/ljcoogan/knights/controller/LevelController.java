package com.ljcoogan.knights.controller;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ljcoogan.knights.domain.Level;
import com.ljcoogan.knights.domain.Player;
import com.ljcoogan.knights.repository.LevelRepository;
import com.ljcoogan.knights.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/level")
public class LevelController {

  private final LevelRepository levelRepo;
  private final PlayerRepository playerRepo;

  public LevelController(LevelRepository levelRepo, PlayerRepository playerRepo) {
    this.levelRepo = levelRepo;
    this.playerRepo = playerRepo;
  }

  @GetMapping()
  private ResponseEntity<Level> getCurrentLevel(Principal principal) {
    Player player = getPlayer(principal);
    var levelOptional = levelRepo.findById((long) player.getLevel());
    if (levelOptional.isPresent()) {
      return ResponseEntity.ok(levelOptional.get());
    } else {
      log.info("HERE SOMEHOW");
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping()
  private ResponseEntity<Boolean> checkSolution(@RequestBody boolean[] solution,
      Principal principal) {
    Player player = getPlayer(principal);
    var levelOptional = levelRepo.findById((long) player.getLevel());
    if (levelOptional.isPresent()) {
      Level level = levelOptional.get();
      if (level.checkSolution(solution)) {
        player.incrementLevel();
        playerRepo.save(player);
        return ResponseEntity.ok(true);
      } else {
        return ResponseEntity.ok(false);
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private Player getPlayer(Principal principal) {
    var playerOptional = playerRepo.findByName(principal.getName());
    if (playerOptional.isPresent()) {
      return playerOptional.get();
    } else {
      Player player = new Player(principal.getName());
      playerRepo.save(player);
      return player;
    }
  }
}
