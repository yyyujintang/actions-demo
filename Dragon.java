/**
 * Dragon.java
 *
 * A subclass of Creature. Study this after Creature.java.
 *
 * Concepts demonstrated in this file:
 *   - "extends" to form a subclass
 *   - super(...) to invoke the parent-class constructor
 *   - a private instance variable that belongs only to Dragon
 *   - a static variable that belongs only to Dragon (separate from
 *     Creature's own static "population" counter)
 *   - direct use of the parent's PROTECTED fields/methods (health,
 *     energy, heal, takeDamage) -- allowed because Dragon IS-A Creature
 *   - super.toString() to reuse, not just replace, parent behavior
 *   - "implements GoldCollector" -- a class can extend one superclass
 *     AND implement one or more interfaces at the same time. Dragon
 *     satisfies the interface's contract with collectGold() and
 *     collectionMethod() below.
 *
 * David Kotz 2026, with substantial contributions by Claude.
 */
public class Dragon extends Creature implements GoldCollector {

  // Static variable specific to THIS class. Creature.population counts
  // every creature ever made; dragonCount only counts Dragons. They are
  // two completely separate counters even though Dragon "inherits" from
  // Creature.
  private static int dragonCount = 0;

  // Private to Dragon -- Goblin doesn't have this, and Creature doesn't
  // know it exists.
  private int fireBreathPower;

  // Also private to Dragon: how much gold this particular dragon has
  // hoarded so far. Goblin tracks its own gold completely separately
  // (see Goblin.pouch) -- there's no shared "gold" field anywhere in
  // Creature, because not every Creature deals in gold at all.
  private int hoard = 0;

  /**
   * Constructs a new Dragon with the specified name, starting health,
   * and fire breath power.
   *
   * @param name the name of the dragon
   * @param startingHealth the initial health of the dragon
   * @param fireBreathPower the power of the dragon's fire breath
   */
  public Dragon(String name, int startingHealth, int fireBreathPower) {
    // super(...) must be the FIRST statement in the constructor. It
    // runs Creature's constructor first (setting name/health/energy
    // and bumping Creature.population) before Dragon adds anything
    // of its own.
    super(name, startingHealth);
    this.fireBreathPower = fireBreathPower;
    dragonCount++;

    // this.name = name;  // <-- would NOT compile: name is private in
    //                          Creature, invisible even to subclasses.
  }

  public static int getDragonCount() {
    return dragonCount;
  }

  /**
   * Makes the dragon breathe fire at the specified target, dealing damage
   * and consuming energy.
   *
   * @param target the creature to breathe fire at
   */
  // Public method unique to Dragon.
  public void breatheFire(Creature target) {
    System.out.println(getName() + " breathes fire at "
                       + target.getName() + "!");
    target.takeDamage(fireBreathPower); // protected method inherited
    // by every Creature (or
    // Creature subclass) --
    // Dragon can call it on
    // ANY Creature, not just itself,
    // because it's called from
    // within Creature's own
    // "family" of classes.
    energy -= 10; // protected field, inherited directly from Creature
  }

  // Reuses the inherited protected heal() method instead of reinventing
  // health-clamping logic.
  public void rest() {
    heal(15);
    System.out.println(getName() + " rests and regains strength. " + this);
  }

  @Override
  public String toString() {
    // super.toString() calls Creature's version and Dragon just adds
    // a label -- reusing parent behavior instead of duplicating it.
    return "Dragon " + super.toString() + " (hoard: " + hoard + " gold)";
  }

  // ----- INTERFACE METHODS (required by GoldCollector) -------------------
  /**
   * Implements the GoldCollector interface method collectGold().
   * A dragon melts down the target's dropped gear and adds the gold
   * to its hoard.
   * 
   * @param target the creature whose dropped gear is melted down for gold
   */  
  // collectGold() has no body in the interface, so Dragon MUST provide
  // one. A Dragon doesn't steal -- it melts down whatever a defeated
  // target left behind and adds the proceeds straight to its own hoard.
  @Override
  public void collectGold(Creature target) {
    int minted = 30;
    hoard += minted;
    System.out.println(getName() + " melts down " + target.getName()
                       + "'s dropped gear and adds " + minted
                       + " gold to its hoard (hoard: " + hoard + ").");
  }

  // collectionMethod() DOES have a default body in the interface, so
  // this override is optional -- Dragon chooses to override it anyway,
  // to report something more specific than "Foraging".
  @Override
  public String collectionMethod() {
    return "Hoarding";
  }
}
