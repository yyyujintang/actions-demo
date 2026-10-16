import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DragonTest checks what a Dragon adds to Creature: fire breath, rest,
 * its own hoard and counter, and its toString() override.
 *
 * @author CS50 teaching team 9/26/26
 */
public class DragonTest {
  private Dragon smaug;
  private Goblin grix;

  @BeforeEach
  void setUp() {
    // a fresh dragon and goblin for every test
    smaug = new Dragon("Smaug", 90, 20);
    grix = new Goblin("Grix", 40, 3);
  }

  @Test
  void breatheFireDamagesTarget() {
    smaug.breatheFire(grix);
    assertEquals(20, grix.getHealth());     // 40 - 20
  }

  @Test
  void breatheFireCostsTenEnergy() {
    smaug.breatheFire(grix);
    assertEquals(40, smaug.energy);         // 50 - 10
  }

  @Test
  void twoBlastsDefeatAGoblin() {
    smaug.breatheFire(grix);
    assertTrue(grix.isAlive());
    smaug.breatheFire(grix);
    assertFalse(grix.isAlive());
    assertEquals(0, grix.getHealth());      // clamped, not -20
  }

  @Test
  void restHealsFifteen() {
    Dragon hurt = new Dragon("Fafnir", 50, 20);
    hurt.rest();
    assertEquals(65, hurt.getHealth());
  }

  @Test
  void restNeverExceedsMax() {
    smaug.rest();                           // 90 + 15 = 105
    assertEquals(Creature.MAX_HEALTH, smaug.getHealth());
  }

  @Test
  void toStringAddsLabelAndHoard() {
    assertEquals("Dragon Smaug [90/100 HP] (hoard: 0 gold)",
                 smaug.toString());
  }

  @Test
  void collectGoldAddsThirtyEachTime() {
    // hoard is private: observe it through toString()
    smaug.collectGold(grix);
    smaug.collectGold(grix);
    assertTrue(smaug.toString().endsWith("(hoard: 60 gold)"),
               () -> "was: " + smaug);
  }

  @Test
  void collectionMethodIsHoarding() {
    assertEquals("Hoarding", smaug.collectionMethod());
  }

  @Test
  void dragonCountIgnoresGoblins() {
    int dragons = Dragon.getDragonCount();
    int population = Creature.getPopulation();
    new Goblin("Nib", 35, 5);
    assertEquals(dragons, Dragon.getDragonCount());
    assertEquals(population + 1, Creature.getPopulation());
  }

  @Test
  void overrideRunsThroughCreatureReference() {
    Creature c = smaug;                     // declared type: Creature
    assertTrue(c.toString().startsWith("Dragon "));
  }

  @Test
  void breatheFirePrintsWhatHappened() {
    PrintStream original = System.out;
    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    System.setOut(new PrintStream(buffer));
    try {
      smaug.breatheFire(grix);
    } finally {
      System.setOut(original);              // always restore
    }
    assertEquals("Smaug breathes fire at Grix!",
                 buffer.toString().trim());
  }
}
