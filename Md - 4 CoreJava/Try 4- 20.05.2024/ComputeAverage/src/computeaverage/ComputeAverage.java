package computeaverage;

import java.util.Scanner;

public class ComputeAverage {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
//        System.out.println("Enter First number: ");
//        double num1 = input.nextDouble();
//        System.out.println("Enter Second number: ");
//        double num2 = input.nextDouble();
//        System.out.println("Enter Thirs number: ");
//        double num3 = input.nextDouble();
//
//        double avg = (num1 + num2 + num3) / 3;
//
//        System.out.println("The Average of " + num1 + ", " + num2 + " and " + num3 + " is " + avg);

        //Advance method
        int lowestNum = Integer.MAX_VALUE;
        int highestNum = Integer.MIN_VALUE;
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter an integer number: ");
            int number = input.nextInt();

            if (number < lowestNum) {
                lowestNum = number;
            }
            if (number > highestNum) {
                highestNum = number;
            }
        }
        System.out.println("The Lowest number is : " + lowestNum);
        System.out.println("The Highest number is : " + highestNum);

    }

}
