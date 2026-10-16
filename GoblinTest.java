import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * GoblinTest checks what a Goblin adds to Creature: sneak attacks
 * whose damage depends on stealth, and pickpocketing into its pouch.
 *
 * @author CS50 teaching team 9/26/26
 */
public class GoblinTest {
  private Creature target;

  @BeforeEach
  void setUp() {
    target = new Creature("Villager", 60);
  }

  @ParameterizedTest(name = "stealth {0} -> {1} damage")
  @CsvSource({
    "0,  8",
    "3, 11",
    "5, 13",
  })
  void sneakAttackDamageDependsOnStealth(int stealth, int damage) {
    Goblin g = new Goblin("Grix", 40, stealth);
    g.sneakAttack(target);
    assertEquals(60 - damage, target.getHealth());
  }

  @Test
  void sneakAttackCostsFiveEnergy() {
    Goblin nib = new Goblin("Nib", 35, 5);
    nib.sneakAttack(target);
    assertEquals(45, nib.energy);           // 50 - 5
  }

  @Test
  void collectGoldStealsTwelveEachTime() {
    Goblin nib = new Goblin("Nib", 35, 5);
    nib.collectGold(target);
    nib.collectGold(target);
    assertTrue(nib.toString().endsWith("(pouch: 24 gold)"),
               () -> "was: " + nib);
  }

  @Test
  void toStringAddsLabelAndPouch() {
    Goblin nib = new Goblin("Nib", 35, 5);
    assertEquals("Goblin Nib [35/100 HP] (pouch: 0 gold)", nib.toString());
  }

  @Test
  void collectionMethodIsPickpocketing() {
    assertEquals("Pickpocketing", new Goblin("Nib", 35, 5).collectionMethod());
  }
}
