import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * GoldCollectorTest checks the interface itself: its default method,
 * and that each implementing class answers through the interface type.
 *
 * @author CS50 teaching team 9/26/26
 */
public class GoldCollectorTest {

  @Test
  void defaultCollectionMethodIsForaging() {
    // a tiny implementation that overrides only the abstract method
    GoldCollector forager = target -> { };
    assertEquals("Foraging", forager.collectionMethod());
  }

  @Test
  void collectorsAnswerThroughTheInterface() {
    // declared type: the interface, as in Demo's goldSeekers array
    GoldCollector[] seekers = {
      new Dragon("Smaug", 90, 20),
      new Goblin("Grix", 40, 3),
    };
    assertEquals("Hoarding", seekers[0].collectionMethod());
    assertEquals("Pickpocketing", seekers[1].collectionMethod());
  }
}
