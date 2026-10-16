/**
 * Demo.java
 *
 * A runnable driver that exercises Creature, Dragon, and Goblin so you
 * can see all the language features in action:
 *
 *   - creating subclass objects (each call runs super() internally)
 *   - calling public methods from outside the class family
 *   - static variables/constants accessed through the CLASS name, not
 *     an object (Creature.getPopulation(), Creature.MAX_HEALTH)
 *   - polymorphism: a Creature[] array holding Dragon and Goblin objects,
 *     each printing its own overridden toString()
 *   - a second, independent kind of polymorphism: a GoldCollector[]
 *     array. GoldCollector is an interface, not a class, so this array
 *     can hold any object whose class implements that interface --
 *     Dragon and Goblin qualify even though neither extends the other.
 *
 * David Kotz 2026, with substantial contributions by Claude.
 */
public class Demo {

  public static void main(String[] args) {
    System.out.println("MAX_HEALTH constant (shared by all creatures): "
                       + Creature.MAX_HEALTH);
    System.out.println();

    Dragon smaug = new Dragon("Smaug", 90, 20);
    Goblin grix = new Goblin("Grix", 40, 3);
    Goblin nib = new Goblin("Nib", 35, 5);

    System.out.println("Creature.getPopulation() -> "
                       + Creature.getPopulation());
    System.out.println("Dragon.getDragonCount()  -> "
                       + Dragon.getDragonCount());
    System.out.println("Goblin.getGoblinCount()  -> "
                       + Goblin.getGoblinCount());
    System.out.println();

    System.out.println("--- Round 1 ---");
    smaug.breatheFire(grix);
    nib.sneakAttack(smaug);
    smaug.rest();
    System.out.println();

    System.out.println("--- Round 2 ---");
    smaug.breatheFire(nib);
    System.out.println();

    System.out.println("--- Status report (class polymorphism) ---");
    Creature[] arena = {smaug, grix, nib};
    for (Creature c : arena) {
      // Each element is really a Dragon or Goblin, but the array is
      // typed as Creature[]. The overridden toString() in each
      // subclass still runs -- that's polymorphism through a
      // shared superclass.
      String status = c.isAlive() ? "alive" : "defeated";
      System.out.println(c + " -- " + status);
    }
    System.out.println();

    System.out.println("--- Gold collectors (interface polymorphism) ---");
    // GoldCollector is an interface, not a superclass. Dragon and
    // Goblin have no inheritance relationship to each other, yet
    // both fit in this array because both implement GoldCollector.
    GoldCollector[] goldSeekers = {smaug, grix, nib};
    for (GoldCollector seeker : goldSeekers) {
      // Only collectGold() and collectionMethod() are visible
      // through this interface-typed reference -- not getName()
      // or getHealth(), even though the underlying objects have
      // those methods too.
      System.out.println("Technique ready: " + seeker.collectionMethod());
    }
    System.out.println();

    System.out.println("--- Round 3: everyone goes after gold ---");
    grix.collectGold(smaug);
    nib.collectGold(smaug);
    smaug.collectGold(grix);
    System.out.println("Status:\n  " + smaug + "\n  " + grix + "\n  " + nib);
  }
}
