package com.ljcoogan.knights.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Player {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private int level;

  public Player() {}

  public Player(String name) {
    this.name = name;
    this.level = 1;
  }

  public void incrementLevel() {
    this.level++;
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nLevel:%d\n", name, level);
  }
}
