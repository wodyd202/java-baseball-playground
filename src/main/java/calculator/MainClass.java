package calculator;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringCalculator stringCalculator = new StringCalculator();
        String input = scanner.nextLine();

        double result = stringCalculator.calculte(input);
        System.out.println(result);
    }
}
