import java.util.ArrayList;
import java.io.*;

/**
 * Demo2.java
 *
 * A variant of Demo.java that explores Collection types.
 *
 * David Kotz 2026, adapted from Demo.java.
 */
public class Demo2 {

  public static void main(String[] args) {
    ArrayList<Dragon> dragons = new ArrayList<Dragon>();

    // read stdin; for each line, use that line as name of a Dragon
    try {
      InputStreamReader streamReader = new InputStreamReader(System.in);
      BufferedReader reader = new BufferedReader(streamReader);
      String line;
      while ((line = reader.readLine()) != null) {
        Dragon dragon = new Dragon(line, 90, 20);
        dragons.add(dragon);
      }
    } catch (IOException e) {
      System.err.println(e);    // print error message to stderr
      System.exit(1);           // exit non-zero to indicate error
    }

    System.out.println("Creature.getPopulation() -> "
                       + Creature.getPopulation());
    System.out.println("Dragon.getDragonCount()  -> "
                       + Dragon.getDragonCount());
    System.out.println("ArrayList size -> "
                       + dragons.size());
    System.out.println();

    // loop over the ArrayList
    for (Dragon d : dragons) {
      System.out.println("Dragon: " + d.getName());
    }
    System.out.println();
  }
}
