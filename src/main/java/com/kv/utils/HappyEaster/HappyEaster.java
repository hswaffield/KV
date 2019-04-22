package com.kv.utils.HappyEaster;

import java.util.Random;

/**
 * I thought it would be fun to add easter eggs, then I realized it's actually easter 2019 today.
 * <p>
 * So after a day thinking about distributed systems, and on call stuff for work, I realized
 * I must engage in a little holiday cheer and give into my impulse.
 * <p>
 * - Henry
 */
public class HappyEaster {

  private static final String fish = "><(((('>";
  private static final String elephant = "°j°m";
  private static final String pig = "༼☉ɷ⊙༽";
  private static final String wow = "˚∆˚";
  private static final String prof = "⌐(ಠ۾ಠ)¬";
  private static final String hands = "✌⊂(✰‿✰)つ✌";
  private static final String dude = "•|龴◡龴|•";

  private static final String bunny = "\n(\\_/)\n༼☉ ⊙༽";
  private static final String bunnyPig = "\n(\\_/)\n༼☉ɷ⊙༽\n";

  private static String[] animals = {fish, elephant, pig, wow, prof, hands, dude,
      bunny, bunnyPig, bunnyPig};

  public static String getEgg(boolean fancy) {
    Random random = new Random();
    int bound = fancy ? animals.length : 2;
    int choice = random.nextInt(bound);
    return animals[choice];
  }

  public static String getEgg() {
    return getEgg(true);
  }

  public static void main(String[] args) {
    System.out.println(fish);
    System.out.println(bunny);
    System.out.println(bunnyPig);
    System.out.println(pig);
    System.out.println(wow);
    System.out.println(hands);
    System.out.println(dude);
    System.out.println("Happy Easter");
  }
}
