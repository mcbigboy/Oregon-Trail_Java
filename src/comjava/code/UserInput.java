package comjava.code;

import java.util.Scanner;

public class UserInput {

    private static Scanner userInput = new Scanner(System.in);

    public static double getDouble(){
        double hold = 0.0;
        boolean doItAgain = false;
        String holdInput = "";
        do {
            doItAgain = false;
            holdInput = userInput.nextLine();
            try {
                hold = Double.parseDouble(holdInput);
            } catch (NumberFormatException e) {
                System.out.println("Numbers only please! Try again:");
                doItAgain = true;
                //userInput.nextLine();
            }


        } while (doItAgain);

        return hold;

    }
}
