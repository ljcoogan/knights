package com.ljcoogan.knights.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ljcoogan.knights.domain.Level;
import com.ljcoogan.knights.domain.Person;
import com.ljcoogan.knights.repository.LevelRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * This file is where I define all of the levels in the game.
 * 
 * I plan to have more than ten levels in the future, but I am focused on getting other core
 * functionality right first.
 */

@Configuration
@Slf4j
class LoadDatabase {

  private final String[] NAMES =
      {"Abby", "Betty", "Carly", "Daisy", "Elly", "Franny", "Gabby", "Halley"};

  @Bean
  CommandLineRunner initDatabase(LevelRepository repo) {
    return args -> {
      final List<Person> persons1 = List.of(new Person(NAMES[0], "I am a knight", true),
          new Person(NAMES[1], String.format("%s is a knight.", NAMES[0]), true),
          new Person(NAMES[2], String.format("I am a knight.", NAMES[1]), false));
      final String description1 =
          "Welcome! Knights will always tell the truth. Knaves will always lie. Tick the box of the people you think are knights.";
      repo.save(new Level(persons1, description1, 1));

      final List<Person> persons2 = List.of(new Person(NAMES[0], "I am a knight", true),
          new Person(NAMES[1], String.format("%s and I are knights.", NAMES[0]), false),
          new Person(NAMES[2], String.format("%s and %s are knights.", NAMES[0], NAMES[1]), false));
      final String description2 = "Good job! Try this one on for size.";
      repo.save(new Level(persons2, description2, 2));

      final List<Person> persons3 = List.of(
          new Person(NAMES[0], String.format("%s and I are knights.", NAMES[2]), true),
          new Person(NAMES[1], String.format("%s and I are knights.", NAMES[2]), false),
          new Person(NAMES[2], String.format("%s is a knave.", NAMES[3]), true),
          new Person(NAMES[3], String.format("%s and %s are knights.", NAMES[1], NAMES[2]), false));
      final String description3 = "There are four people now! Try this!";
      repo.save(new Level(persons3, description3, 3));

      final List<Person> persons4 =
          List.of(new Person(NAMES[0], String.format("%s is a knight.", NAMES[1]), false),
              new Person(NAMES[1], String.format("Either %s or I am a knight.", NAMES[0]), false),
              new Person(NAMES[2], String.format("Either %s or %s is a knight", NAMES[0], NAMES[3]),
                  true),
              new Person(NAMES[3], String.format("Either %s or %s is a knave.", NAMES[0], NAMES[2]),
                  true));
      final String description4 = "'Either' means one, but not both. Give this one a go.";
      repo.save(new Level(persons4, description4, 4));

      final List<Person> persons5 = List.of(
          new Person(NAMES[0],
              String.format("%s, %s, and %s are all knaves.", NAMES[1], NAMES[2], NAMES[3]), false),
          new Person(NAMES[1],
              String.format("%s, %s, and %s are all knaves.", NAMES[2], NAMES[3], NAMES[4]), false),
          new Person(NAMES[2], String.format("Either %s or I am the knight.", NAMES[4]), false),
          new Person(NAMES[3],
              String.format("%s, %s, and %s are all knaves.", NAMES[0], NAMES[1], NAMES[2]), true),
          new Person(NAMES[4], String.format("Either %s or I am the knight.", NAMES[2]), false));
      final String description5 =
          "This one might look harder, but remember, there's only one knight.";
      repo.save(new Level(persons5, description5, 5));

      final List<Person> persons6 = List.of(
          new Person(NAMES[0], String.format("Both %s and %s are knaves.", NAMES[1], NAMES[2]),
              false),
          new Person(NAMES[1],
              String.format("At least one of %s or %s is a knight.", NAMES[0], NAMES[2]), true),
          new Person(NAMES[2], String.format("I am a knight or %s is a knave.", NAMES[1]), false),
          new Person(NAMES[3],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[0], NAMES[1],
                  NAMES[2]),
              true),
          new Person(NAMES[4], String.format("Either %s or %s is a knight.", NAMES[0], NAMES[3]),
              false));
      final String description6 = "This one might look harder, and that's because it is.";
      repo.save(new Level(persons6, description6, 6));

      final List<Person> persons7 = List.of(
          new Person(NAMES[0],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[1], NAMES[2],
                  NAMES[3]),
              true),
          new Person(NAMES[1], String.format("%s and I are knights.", NAMES[0]), false),
          new Person(NAMES[2],
              String.format("Either %s and I are knights, or %s and %s are knights,", NAMES[0],
                  NAMES[4], NAMES[5]),
              true),
          new Person(NAMES[3],
              String.format("Either %s and %s are knaves, or %s and %s are knaves.", NAMES[0],
                  NAMES[1], NAMES[2], NAMES[4]),
              false),
          new Person(NAMES[4],
              String.format("Only one of %s, %s, and %s is a knight", NAMES[0], NAMES[1], NAMES[2]),
              false),
          new Person(NAMES[5],
              String.format("Only one of %s, %s, and %s is a knight", NAMES[1], NAMES[3], NAMES[4]),
              false));
      final String description7 = "We're up to six now. Good luck!";
      repo.save(new Level(persons7, description7, 7));

      final List<Person> persons8 = List.of(
          new Person(NAMES[0],
              String.format("At least one of %s or %s is a knight.", NAMES[1], NAMES[2]), true),
          new Person(NAMES[1], String.format("Both %s and %s are knaves.", NAMES[0], NAMES[3]),
              false),
          new Person(NAMES[2], String.format("I am a knight or %s is a knave.", NAMES[4]), true),
          new Person(NAMES[3],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[0], NAMES[1],
                  NAMES[2]),
              false),
          new Person(NAMES[4], String.format("Either %s or %s is a knight.", NAMES[2], NAMES[5]),
              true),
          new Person(NAMES[5], String.format("I am a knave, and %s is a knight.", NAMES[4]),
              false));
      final String description8 = "You're doing great - only three more levels!";
      repo.save(new Level(persons8, description8, 8));

      final List<Person> persons9 = List.of(
          new Person(NAMES[0],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[1], NAMES[2],
                  NAMES[3]),
              true),
          new Person(NAMES[1], String.format("Both %s and %s are knaves.", NAMES[0], NAMES[4]),
              false),
          new Person(NAMES[2],
              String.format("At least one of %s or %s is a knight.", NAMES[3], NAMES[5]), true),
          new Person(NAMES[3], String.format("I am a knight, and %s is a knave.", NAMES[1]), false),
          new Person(NAMES[4], String.format("Either %s or %s is a knight.", NAMES[2], NAMES[6]),
              true),
          new Person(NAMES[5],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[0], NAMES[1],
                  NAMES[2]),
              false),
          new Person(NAMES[6], String.format("I am a knave, and %s is a knight.", NAMES[4]),
              false));
      final String description9 = "Seven? I think you can do it.";
      repo.save(new Level(persons9, description9, 9));

      final List<Person> persons10 = List.of(
          new Person(NAMES[0],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[1], NAMES[2],
                  NAMES[3]),
              true),
          new Person(NAMES[1], String.format("Both %s and %s are knaves.", NAMES[0], NAMES[4]),
              false),
          new Person(NAMES[2],
              String.format("At least one of %s or %s is a knight.", NAMES[3], NAMES[5]), true),
          new Person(NAMES[3], String.format("I am a knight, and %s is a knave.", NAMES[1]), false),
          new Person(NAMES[4], String.format("Either %s or %s is a knight.", NAMES[2], NAMES[6]),
              true),
          new Person(NAMES[5],
              String.format("Only one of %s, %s, and %s is a knight.", NAMES[0], NAMES[1],
                  NAMES[2]),
              false),
          new Person(NAMES[6], String.format("I am a knave, and %s is a knight.", NAMES[4]), false),
          new Person(NAMES[7],
              String.format("At least one of %s or %s is a knight.", NAMES[5], NAMES[6]), true));
      final String description10 = "The ultimate challenge. Good luck!";
      repo.save(new Level(persons10, description10, 10));
    };
  }
}
