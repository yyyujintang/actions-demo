/**
 * GoldCollector.java
 *
 * A Java interface: a pure contract with no instance fields and (mostly)
 * no implementation of its own. Any class -- whether or not it's related
 * by inheritance -- can "implement" this interface, promising to provide
 * whatever abstract methods it declares. Dragon and Goblin both do this
 * (see "implements GoldCollector" in their class headers), even though
 * they get rich in completely different ways: a Dragon hoards gold it
 * melts down from a defeated target's gear, while a Goblin just steals
 * gold out of a target's pocket.
 *
 * Concepts demonstrated in this file:
 *   - the "interface" keyword, used instead of "class"
 *   - an abstract method (no body, just a semicolon) -- every
 *     implementing class MUST supply this method, and each is free to
 *     decide what "collecting gold" actually means for it
 *   - a default method (Java 8+), which DOES have a body and is
 *     inherited for free by any implementing class that doesn't
 *     override it
 *   - an interface cannot be instantiated by itself -- there is no
 *     "new GoldCollector()" anywhere in this project, only variables of
 *     type GoldCollector that refer to Dragon or Goblin objects
 * 
 * David Kotz 2026, with substantial contributions by Claude.
 */
public interface GoldCollector {

  // Abstract method: no body. Every class that writes
  // "implements GoldCollector" is required to define this method.
  void collectGold(Creature target);

  // Default method: the interface supplies a ready-made
  // implementation. Implementing classes inherit it automatically but
  // may override it -- Dragon and Goblin both do, to name their own
  // technique.
  default String collectionMethod() {
    return "Foraging";
  }
}
