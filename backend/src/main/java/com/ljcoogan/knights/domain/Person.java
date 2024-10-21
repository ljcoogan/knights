package com.ljcoogan.knights.domain;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
public class Person implements Serializable {
  private String name;
  private String dialogue;
  private boolean isKnight;

  public Person(String name, String dialogue, boolean isKnight) {
    this.name = name;
    this.dialogue = dialogue;
    this.isKnight = isKnight;
  }
}
