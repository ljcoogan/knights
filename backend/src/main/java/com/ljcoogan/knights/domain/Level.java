package com.ljcoogan.knights.domain;

import static java.util.Objects.requireNonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Level implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Person> persons;

  private String description;
  private int personCount;
  private int knightCount;
  private int levelNumber;

  public Level(List<Person> persons, String description, int levelNumber)
      throws NullPointerException, IllegalArgumentException {
    requireNonNull(persons, "persons must not be null.");
    if (persons.size() < 2)
      throw new IllegalArgumentException("There must be at least two persons.");

    this.persons = new ArrayList<>(persons);

    this.personCount = persons.size();
    this.knightCount = (int) persons.stream().filter(Person::isKnight).count();
    if (this.knightCount == 0)
      throw new IllegalArgumentException("There must be at least one knight.");
    else if (this.knightCount == persons.size())
      throw new IllegalArgumentException("There must be at least one knave.");

    this.description = description;

    if (levelNumber < 1)
      throw new IllegalArgumentException("The level number must be at least 1.");
    this.levelNumber = levelNumber;
  }

  public boolean checkSolution(boolean[] solution) throws IllegalArgumentException {
    if (solution.length != this.personCount)
      throw new IllegalArgumentException("Solution must have " + this.personCount + " persons.");

    for (int i = 0; i < solution.length; i++)
      if (solution[i] != this.persons.get(i).isKnight())
        return false;
    return true;
  }
}
