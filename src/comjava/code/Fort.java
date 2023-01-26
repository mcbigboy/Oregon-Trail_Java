package comjava.code;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Fort {

    protected double oxen;               // 6480 REM A = AMOUNT SPENT ON ANIMALS
    protected double ammunition;         // 6490 REM B = AMOUNT SPENT ON AMMUNITION
    protected double clothing;           // 6520 REM C = AMOUNT SPENT ON CLOTHING
    protected double food;               // 6600 REM F = AMOUNT SPENT ON FOOD
    protected double miscSupplies;       // 6670 REM M1 = AMOUNT SPENT ON MISCELLANEOUS SUPPLIES
    protected double money;              // 6760 REM T = CASH LEFT OVER AFTER INITIAL PURCHASES
    private String numberOnly = "\u001B[31mNumbers only!\u001B[0m\n";
    private static Fort fort = null;

    public Scanner userInput = new Scanner(System.in);

    public Fort() {

        this.oxen = 0;
        this.ammunition = 0;
        this.clothing = 0;
        this.miscSupplies = 0;
        this.food = 0;
        this.money = 700;

    }

    public static synchronized Fort getInstance() {
        if (fort == null) {
            fort = new Fort();
        }
        return fort;
    }


    public void initialPurchases() {

        boolean spendDone = false;
        boolean done = false;
        double spent = 0;

        while (!spendDone) {

            buyOxen();
            buyFood(1);
            buyAmmunition(1);
            buyClothing(1);
            buyMiscSupplies(1);

            /*
                1130 LET T=700-A-F-B-C-M1
                1140 IF T >= 0 THEN 1170
                1150 PRINT "YOU OVERSPENT--YOU ONLY HAD $700 TO SPEND.  BUY AGAIN."
                1160 GOTO 830
             */

            spent = this.ammunition + this.miscSupplies + this.food + this.oxen + this.clothing;

            if (money - spent >= 0) {
                spendDone = true;
                this.money -= spent;
            } else {
                done = false;

                System.out.println("YOU OVERSPENT--YOU ONLY HAD $700 TO SPEND.  BUY AGAIN.");
            }

        }

        /*
            1170 LET B=50*B
            1180 PRINT "AFTER ALL YOUR PURCHASES, YOU NOW HAVE ";T;" DOLLARS LEFT"
            1190 PRINT
            1200 PRINT "MONDAY MARCH 29 1847"
            1210 PRINT
            1220 GOTO 1750
        */

        this.ammunition *= 50;

        System.out.println("AFTER ALL YOUR PURCHASES, YOU NOW HAVE " + this.money + " DOLLARS LEFT");
        System.out.println();
        System.out.println("MONDAY MARCH 29 1847");
        System.out.println();
    }

    public void shopFort() {

        /*

            2280 REM ***STOPPING AT FORT***
            2290 PRINT "ENTER WHAT YOU WISH TO SPEND ON THE FOLLOWING"
            2300 PRINT "FOOD";
            2310 GOSUB 2329
            2320 GOTO 2410
            2329 LET P = 0
            2330 INPUT P
            2340 IF P<0 THEN 2400
            2350 LET T=T-P
            2360 IF T >= 0 THEN 2400
            2370 PRINT "YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN"
            2375 PRINT "YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM"
            2380 LET T=T+P
            2390 LET P=0
            2400 RETURN
            2410 LET F=F+2/3*P
            2420 PRINT "AMMUNITION";
            2430 GOSUB 2330
            2440 LET B=INT(B+2/3*P*50)
            2450 PRINT "CLOTHING";
            2460 GOSUB 2330
            2470 LET C=C+2/3*P
            2480 PRINT "MISCELLANEOUS SUPPLIES";
            2490 GOSUB 2330
            2500 LET M1=M1+2/3*P
            2510 LET M=M-45
            2520 GOTO 2720
         */
        System.out.println("ENTER WHAT YOU WISH TO SPEND ON THE FOLLOWING");
        buyFood(2);
        buyAmmunition(2);
        buyClothing(2);
        buyMiscSupplies(2);
    }

    public void printSupplies() {

        /*
            2040 PRINT "FOOD","BULLETS","CLOTHING","MISC. SUPP.","CASH"
            2050 PRINT F,B,C,M1,T
         */

        StringBuilder sb = new StringBuilder();
        sb.append("Food: " + food + "\n");
        sb.append("Bullets: " + ammunition + "\n");
        sb.append("Clothing: " + clothing + "\n");
        sb.append("Misc. Supplies: " + miscSupplies + "\n");

        System.out.println(sb);

    }

    private void buyOxen() {

        /*
            850 PRINT "HOW MUCH DO YOU WANT TO SPEND ON YOUR OXEN TEAM";
            851 LET A = 0
            860 INPUT A
            870 IF A >= 200 THEN 900
            880 PRINT "NOT ENOUGH"
            890 GOTO 850
            900 IF A <= 300 THEN 930
            910 PRINT "TOO MUCH"
            920 GOTO 850
        */

        boolean done = false;
        double hold = 0.0;

        while (!done) {
            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON YOUR OXEN TEAM");

            hold = UserInput.getDouble();

            //hold = userInput.nextDouble();

            if (hold <= 199) {
                System.out.println("NOT ENOUGH");

            } else if (hold >= 299) {
                System.out.println("TOO MUCH");

            } else {
                this.oxen = hold;
                done = true;
            }

        }
    }

    private void buyFood(int method) {

        /*
            930 PRINT "HOW MUCH DO YOU WANT TO SPEND ON FOOD";
            931 LET F = 0
            940 INPUT F
            950 IF F >= 0 THEN 980
            960 PRINT "IMPOSSIBLE"
            970 GOTO 930
        */

        boolean done = false;
        boolean doItAgain;
        double amount = 0;

        do {
            doItAgain = false;

            try {
                amount = userInput.nextDouble();
                userInput.next();

                switch (method) {
                    case 1:
                        while (!done) {
                            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON FOOD");

                            if (amount >= 0) {
                                this.food = amount;
                                done = true;
                            } else {
                                System.out.println("IMPOSSIBLE");
                            }
                        }
                        break;

                    case 2:
                        System.out.println("Food");

                        if (this.money >= amount) {
                            this.money -= amount;
                            this.food += 2 / 3 * amount;
                        } else {
                            System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                            System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                        }
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println(numberOnly);
                userInput.next();
                doItAgain = true;
            }

        } while (doItAgain);

    }

    private void buyAmmunition(int method) {

        /*
            980 PRINT "HOW MUCH DO YOU WANT TO SPEND ON AMMUNITION";
            981 LET B = 0
            990 INPUT B
            1000 IF B >= 0 THEN 1030
            1010 PRINT "IMPOSSIBLE"
            1020 GOTO 980

        */


        boolean done = false;
        boolean doItAgain;
        double amount = 0;

        do {
            doItAgain = false;
            try {
                amount = userInput.nextDouble();
                userInput.next();

                switch (method) {


                    case 1:
                        while (!done) {
                            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON AMMUNITION");

                            if (amount >= 0) {
                                this.ammunition = amount;
                                done = true;
                            } else {
                                System.out.println("IMPOSSIBLE");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Ammunition");

                        if (this.money >= amount) {
                            this.money -= amount;
                            this.ammunition += 2.0 / 3.0 * amount * 50;
                        } else {
                            System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                            System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                        }
                        break;


                }
            } catch (InputMismatchException e) {
                System.out.println(numberOnly);
                userInput.next();

                doItAgain = true;
            }
        } while (doItAgain);

    }

    private void buyClothing(int method) {

        /*
            1030 PRINT "HOW MUCH DO YOU WANT TO SPEND ON CLOTHING";
            1031 LET C = 0
            1040 INPUT C
            1050 IF C >= 0 THEN 1080
            1060 PRINT "IMPOSSIBLE"
            1070 GOTO 1030
         */

        boolean done = false;
        boolean doItAgain;
        double amount = 0;

        do {

            doItAgain = false;

            try {

                amount = userInput.nextDouble();
                userInput.next();

                switch (method) {
                    case 1:
                        while (!done) {
                            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON CLOTHING");

                            if (amount >= 0) {
                                this.clothing = amount;
                                done = true;
                            } else {
                                System.out.println("IMPOSSIBLE");
                            }
                        }

                        break;

                    case 2:
                        System.out.println("Clothing");


                        if (this.money >= amount) {
                            this.money -= amount;
                            this.clothing += 2.0 / 3.0 * amount;
                        } else {
                            System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                            System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                        }
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println(numberOnly);
                userInput.next();
                doItAgain = true;
            }

        } while (doItAgain);

    }

    private void buyMiscSupplies(int method) {

        /*
            1080 PRINT "HOW MUCH DO YOU WANT TO SPEND ON MISCELLANEOUS SUPPLIES";
            1081 LET M1 = 0
            1090 INPUT M1
            1100 IF M1 >= 0 THEN 1130
            1110 PRINT "IMPOSSIBLE"
            1120 GOTO 1080
         */

        boolean done = false;
        boolean doItAgain;
        double amount = 0;

        do {

            doItAgain = false;

            try {

                amount = userInput.nextDouble();
                userInput.next();

                switch (method) {
                    case 1:
                        while (!done) {
                            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON MISCELLANEOUS SUPPLIES");

                            if (amount >= 0) {
                                this.miscSupplies = amount;
                                done = true;
                            } else {
                                System.out.println("IMPOSSIBLE");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Miscellaneous Supplies");


                        if (this.money >= amount) {
                            this.money -= amount;
                            this.miscSupplies += 2.0 / 3.0 * amount;
                        } else {
                            System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                            System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                        }

                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(numberOnly);


                userInput.next();
                doItAgain = true;
            }

        } while (doItAgain);

    }

    private double getDouble() {

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
