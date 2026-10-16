import java.util.Queue;
import java.util.LinkedList;
import java.io.*;

/**
 * Demo3.java
 *
 * A variant of Demo2.java that uses a Queue instead of ArrayList.
 *
 * David Kotz 2026, adapted from Demo2.java.
 */
public class Demo3 {

  public static void main(String[] args) {
    // Note: Queue is *abstract* so one must instantiate a LinkedList,
    //       then use it as a Queue
    Queue<Dragon> dragons = new LinkedList<Dragon>();

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
    System.out.println("Queue size -> "
                       + dragons.size());
    System.out.println();

    // Loop over the Queue: we have to poll() to remove entries,
    // which returns 'null' when queue is empty.
    // Note we leverage polymorphism here, using Creature to receive
    // references to Dragons.
    Creature c;
    while ((c = dragons.poll()) != null) {
      System.out.println("Dragon: " + c.getName());
    }
    System.out.println();
  }
}
