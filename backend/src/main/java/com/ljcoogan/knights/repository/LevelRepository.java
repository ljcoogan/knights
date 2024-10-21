package com.ljcoogan.knights.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ljcoogan.knights.domain.Level;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
