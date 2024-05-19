package testsecondconvert;

import java.util.Scanner;

public class TestSecondConvert {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter any number for convert into Second");

        double inpSecond = input.nextDouble();

        int minutes = (int) (inpSecond / 60);
        double remindSconds = inpSecond % 60;

        System.out.println(minutes + " Minuts" + " and " + remindSconds + " Seconds");

    }

}
