package map;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();
    public static boolean chanceOn(int numberOfChance,int max ){
        int number = random.nextInt(max);
        return number < numberOfChance;
    }
    public static int random(int max){
        return (int) random.nextInt(max);
    }
}
