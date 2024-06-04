package randomintarray;

import java.util.Scanner;

public class RandomIntArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer index (0 to 99): ");
        int index = scanner.nextInt();
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000) + 1;

        }

        try {
            System.out.println("Your input " + index + " index's is : " + array[index]);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Out of Bounds");
        }

    }

}
