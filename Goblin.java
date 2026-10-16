/**
 * Goblin.java
 *
 * A second, simpler subclass of Creature. Comparing Dragon and Goblin
 * side by side shows that different subclasses can extend the same
 * parent in different ways, while both still rely on Creature's
 * protected fields/methods and both must call super(...).
 *
 * Concepts demonstrated in this file:
 *   - a second use of "extends" + super(...)
 *   - a private instance variable (stealthLevel) unrelated to Dragon's
 *   - a static variable scoped to Goblin only (goblinCount)
 *   - overriding a public method (toString) differently than Dragon does
 *   - "implements GoldCollector" -- Goblin and Dragon share no code
 *     with each other, only a common parent (Creature), yet both can
 *     honor the same interface contract in their own way. That's the
 *     point of an interface: unrelated implementations, same contract --
 *     a Dragon hoards gold, a Goblin steals it, and outside code can
 *     treat both the same way through the GoldCollector type.
 * 
 * David Kotz 2026, with substantial contributions by Claude.
 */
public class Goblin extends Creature implements GoldCollector {

  private static int goblinCount = 0;

  // Private to Goblin -- affects how likely a sneak attack lands.
  private int stealthLevel;

  // Private to Goblin: its own stash of stolen gold. Completely
  // separate from Dragon.hoard -- the two classes don't share this
  // field, they only share the GoldCollector contract.
  private int pouch = 0;

  /** Constructs a new Goblin with the specified name, starting health,
   *  and stealth level.
   *
   * @param name the name of the goblin
   * @param startingHealth the initial health of the goblin
   * @param stealthLevel the stealth level of the goblin
   */
  public Goblin(String name, int startingHealth, int stealthLevel) {
    super(name, startingHealth); // must come first, sets up the
    // inherited Creature state
    this.stealthLevel = stealthLevel;
    goblinCount++;
  }

  public static int getGoblinCount() {
    return goblinCount;
  }

  /** Performs a sneak attack on the specified target, dealing damage
   *  and consuming energy.
   *
   * @param target the creature to sneak attack
   */
  public void sneakAttack(Creature target) {
    int damage = 8 + stealthLevel;
    System.out.println(getName() + " sneaks up on " + target.getName()
                       + " and strikes for " + damage + "!");
    target.takeDamage(damage); // protected method inherited from Creature
    energy -= 5;               // protected field inherited from Creature
  }

  @Override
  public String toString() {
    return "Goblin " + super.toString() + " (pouch: " + pouch + " gold)";
  }

  // ----- INTERFACE METHODS (required by GoldCollector) -------------------
  /** Implements the GoldCollector interface method collectGold().
   * A goblin steals gold directly from the target's pouch.
   * 
   * @param target the creature whose gold is stolen
   */
  // Unlike Dragon, a Goblin doesn't make its own gold -- it steals a
  // handful straight out of the target's pocket.
  @Override
  public void collectGold(Creature target) {
    int stolen = 12;
    pouch += stolen;
    System.out.println(getName() + " picks " + target.getName()
                       + "'s pocket and steals "
                       + stolen + " gold (pouch: " + pouch + ").");
  }

  @Override
  public String collectionMethod() {
    return "Pickpocketing";
  }
}
