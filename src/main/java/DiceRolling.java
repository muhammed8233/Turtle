import java.security.SecureRandom;

public class DiceRolling {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        final int ROLLS = 36_000_000;
        int[] frequency = new int[13]; // indexes 2-12 used

        for (int i = 0; i < ROLLS; i++) {
            int die1 = 1 + random.nextInt(6);
            int die2 = 1 + random.nextInt(6);
            frequency[die1 + die2]++;
        }

        System.out.printf("%-5s%12s%10s%n", "Sum", "Frequency", "Percent");
        for (int sum = 2; sum < frequency.length; sum++) {
            System.out.printf("%-5d%12d%9.2f%%%n", sum, frequency[sum],
                100.0 * frequency[sum] / ROLLS);
        }
    }
}
