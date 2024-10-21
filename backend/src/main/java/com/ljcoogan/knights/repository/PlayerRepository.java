package com.ljcoogan.knights.repository;

import org.springframework.data.repository.CrudRepository;
import com.ljcoogan.knights.domain.Player;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
  Optional<Player> findByName(String name);
}
