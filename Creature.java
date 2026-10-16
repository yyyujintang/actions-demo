/**
 * Creature.java
 *
 * Base class for every creature in the arena. This class is the one to
 * study first -- it introduces every feature the subclasses (Dragon,
 * Goblin) build on.
 *
 * Concepts demonstrated in this file:
 *   - a static final constant      (MAX_HEALTH)
 *   - a static variable            (population)
 *   - private instance variables   (name)
 *   - protected instance variables (health, energy)
 *   - private methods              (clampHealth)
 *   - protected methods            (heal, takeDamage)
 *   - public methods               (getName, getHealth, isAlive, ...)
 * 
 * David Kotz 2026, with substantial contributions by Claude.
 */
public class Creature {

  // ----- STATIC FINAL CONSTANT -----------------------------------------
  // "static" -> belongs to the class itself, not to any one object.
  // "final"  -> once set, it can never be reassigned.
  // Convention: ALL_CAPS name. Every Creature and every subclass shares
  // this exact same value; there is only one copy in memory no matter
  // how many creatures you create.
  public static final int MAX_HEALTH = 100;

  // ----- STATIC VARIABLE ------------------------------------------------
  // Also belongs to the class, not to individual objects, but unlike a
  // constant it CAN change. One counter is shared by every Creature
  // (and every subclass, since Dragon and Goblin don't declare their
  // own "population" field). The constructor below increments it, so
  // it always reflects how many creatures have been built in total.
  private static int population = 0;

  // ----- PRIVATE INSTANCE VARIABLE --------------------------------------
  // Visible only inside Creature.java. Not even Dragon or Goblin can
  // reach "name" directly -- they must use the public getName() method.
  // This is real encapsulation: Creature reserves the right to change
  // how names are stored later without breaking any subclass.
  private final String name;

  // ----- PROTECTED INSTANCE VARIABLES -----------------------------------
  // Hidden from unrelated classes, but visible to subclasses. Dragon and
  // Goblin are allowed to read/adjust these directly because they are
  // Creatures too and need tight, efficient access during combat.
  protected int health;
  protected int energy;

  /** Constructs a new Creature with the specified name and starting health.
   *
   * @param name the name of the creature
   * @param startingHealth the initial health of the creature
   */
  // Public constructor. This is what subclass constructors call via
  // super(...) -- see Dragon.java and Goblin.java.
  public Creature(String name, int startingHealth) {
    this.name = name;
    this.health = clampHealth(startingHealth);
    this.energy = 50;
    population++;   // every construction, of any subclass, counts here
  }

  // ----- PRIVATE METHOD --------------------------------------------------
  /** Constructs a new Creature with the specified name and starting health.
   *
   * @param name the name of the creature
   * @param startingHealth the initial health of the creature
   */
  // An internal implementation detail of Creature. Subclasses have no
  // reason to call this themselves (they go through heal/takeDamage
  // instead), so it is private and invisible outside this file.
  private int clampHealth(int value) {
    if (value > MAX_HEALTH) return MAX_HEALTH;
    if (value < 0) return 0;
    return value;
  }

  // ----- PROTECTED METHODS -------------------------------------------------
  // Subclasses need to change health during battle using the same
  // clamping rule, but random outside code should not be able to poke a
  // creature's health directly -- so these are protected, not public.
  
  /**
   * Protected methods for modifying health.
   * Subclasses can use these to adjust health during combat.
   * 
   * @param amount the amount of health to add or subtract
   */
  protected void heal(int amount) {
    health = clampHealth(health + amount);
  }

  /**
   * Protected method for taking damage.
   * Subclasses can use this to reduce health during combat.
   * 
   * @param amount the amount of damage to take
   */
  protected void takeDamage(int amount) {
    health = clampHealth(health - amount);
  }

  // ----- PUBLIC METHODS ----------------------------------------------------
  // The API any class, anywhere, is allowed to use.
  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public boolean isAlive() {
    return health > 0;
  }

  // A static method that reads the static variable. Called on the class
  // itself, e.g. Creature.getPopulation(), not on an object.
  public static int getPopulation() {
    return population;
  }

  @Override
  public String toString() {
    return String.format("%s [%d/%d HP]", name, health, MAX_HEALTH);
  }
}
