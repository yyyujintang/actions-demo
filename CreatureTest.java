import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * CreatureTest checks the base class: health is always kept within
 * 0..MAX_HEALTH, isAlive() and toString() report it, and the static
 * population counter counts every creature, of any subclass.
 *
 * @author CS50 teaching team 9/26/26
 */
public class CreatureTest {

  @ParameterizedTest(name = "starting health {0} -> {1}")
  @CsvSource({
    "150, 100",    // above MAX_HEALTH
    "100, 100",    // exactly MAX_HEALTH
    " 40,  40",    // typical
    "  0,   0",    // exactly 0
    " -5,   0",    // below 0
  })
  void startingHealthIsClamped(int starting, int expected) {
    Creature c = new Creature("Gus", starting);
    assertEquals(expected, c.getHealth());
  }

  @Test
  void creatureWithHealthIsAlive() {
    assertTrue(new Creature("Gus", 1).isAlive());
  }

  @Test
  void creatureWithNoHealthIsNotAlive() {
    assertFalse(new Creature("Gus", 0).isAlive());
  }

  @Test
  void takeDamageNeverGoesBelowZero() {
    Creature c = new Creature("Gus", 30);
    c.takeDamage(50);    // protected, but visible in the same package
    assertEquals(0, c.getHealth());
    assertFalse(c.isAlive());
  }

  @Test
  void healNeverExceedsMax() {
    Creature c = new Creature("Gus", 90);
    c.heal(50);
    assertEquals(Creature.MAX_HEALTH, c.getHealth());
  }

  @Test
  void toStringShowsNameAndHealth() {
    assertEquals("Gus [40/100 HP]", new Creature("Gus", 40).toString());
  }

  @Test
  void populationCountsEverySubclass() {
    // population is static: it keeps its value from earlier tests,
    // so compare before and after instead of expecting a fixed number
    int before = Creature.getPopulation();
    new Creature("Gus", 40);
    new Dragon("Smaug", 90, 20);
    new Goblin("Grix", 40, 3);
    assertEquals(before + 3, Creature.getPopulation());
  }
}
