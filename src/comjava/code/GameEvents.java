package comjava.code;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameEvents {

    private GameData gameData = GameData.getInstance();
    private boolean isHostility;        // 6730 REM S5 = ""HOSTILITY OF RIDERS"" FACTOR
    private int shotResponseTime;       // 6500 REM B1 = ACTUAL RESPONSE TIME FOR INPUTTING "BANG"


    private boolean clearSouthPass;
    protected boolean clearSouthPassMileage;
    private boolean clearBlueMountains;

    private int howToEat;
    private Random rnd;
    private Scanner userInput;
    private String input;
    private String[] gameDates;


    public GameEvents() {

        this.userInput = new Scanner(System.in);
        this.rnd = new Random();
        this.isHostility = false;
        this.input = "";
        this.shotResponseTime = 0;

        this.howToEat = 0;
        this.clearSouthPass = false;
        this.clearBlueMountains = false;

        this.clearSouthPassMileage = false;
        this.gameDates = new String[19];

        createGameDates();
    }

    public void shotLevel() {

        int shot = 0;

        Instructions.displayInto();

        shot = getInt();

        if (shot > 5) {
            gameData.shootingExpertiseLevel = 0;
        } else {
            gameData.shootingExpertiseLevel = shot;
        }

    }

    public int getHowToEat() {
        return howToEat;
    }

    public void callDying(int how) {
        /*
        REM ***DYING***
            5060 PRINT "YOU RAN OUT OF FOOD AND STARVED TO DEATH"
            5070 GOTO 5170
            5080 LET T=0
            5090 PRINT "YOU CAN'T AFFORD A DOCTOR"
            5100 GOTO 5120
            5110 PRINT "YOU RAN OUT OF MEDICAL SUPPLIES"
            5120 PRINT "YOU DIED OF ";
            5130 IF K8=1 THEN 5160
            5140 PRINT "PNEUMONIA"
            5150 GOTO 5170
            5160 PRINT "INJURIES"
            5170 PRINT

         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Dying" + gameData.ANSI_RESET);

        }

        String death = "";
        if (how == 1) {
            System.out.println("YOU RAN OUT OF FOOD AND STARVED TO DEATH");
        } else {

            if (how == 2) {
                System.out.println("YOU CAN'T AFFORD A DOCTOR");
                death = "PNEUMONIA";
            }

            if (how == 3) {
                System.out.println("YOU RAN OUT OF MEDICAL SUPPLIES");
                death = "INJURIES";
            }

            System.out.println("YOU DIED OF " + death);
        }

        // There is no code to handle the questions answers but the last one

        /*
         5180 PRINT "DUE TO YOUR UNFORTUNATE SITUATION, THERE ARE A FEW"
            5190 PRINT "FORMALITIES WE MUST GO THROUGH"
            5200 PRINT
            5210 PRINT "WOULD YOU LIKE A MINISTER?"
            5220 INPUT C$
            5230 PRINT "WOULD YOU LIKE A FANCY FUNERAL?"
            5240 INPUT C$
            5250 PRINT "WOULD YOU LIKE US TO INFORM YOUR NEXT OF KIN?"
            5260 INPUT C$
            5270 IF C$="y" THEN 5310
            5280 PRINT "BUT YOUR AUNT SADIE IN ST. LOUIS IS REALLY WORRIED ABOUT YOU"
            8290 PRINT
            8300 GOTO 5330
            5310 PRINT "THAT WILL BE $4.50 FOR THE TELEGRAPH CHARGE."
            5320 PRINT
            5330 PRINT "WE THANK YOU FOR THIS INFORMATION AND WE ARE SORRY YOU"
            5340 PRINT "DIDN'T MAKE IT TO THE GREAT TERRITORY OF OREGON"
            5350 PRINT "BETTER LUCK NEXT TIME"
            5360 PRINT
            5370 PRINT
            5380 PRINT TAB(30);"SINCERELY"
            5390 PRINT
            5400 PRINT TAB(17);"THE OREGON CITY CHAMBER OF COMMERCE"
            5410 GOTO 9999
         */
        System.out.println("DUE TO YOUR UNFORTUNATE SITUATION, THERE ARE A FEW");
        System.out.println("FORMALITIES WE MUST GO THROUGH");
        System.out.println("\n");
        System.out.println("WOULD YOU LIKE A MINISTER?");
        this.input = userInput.nextLine();
        System.out.println("WOULD YOU LIKE A FANCY FUNERAL?");
        this.input = userInput.nextLine();
        System.out.println("WOULD YOU LIKE US TO INFORM YOUR NEXT OF KIN?");
        this.input = userInput.nextLine();

        if (this.input.equalsIgnoreCase("y")) {
            System.out.println("THAT WILL BE $4.50 FOR THE TELEGRAPH CHARGE.");
        } else {
            System.out.println("BUT YOUR AUNT SADIE IN ST. LOUIS IS REALLY WORRIED ABOUT YOU");
        }

        StringBuilder message = new StringBuilder();
        message.append("WE THANK YOU FOR THIS INFORMATION AND WE ARE SORRY YOU");
        message.append("DIDN'T MAKE IT TO THE GREAT TERRITORY OF OREGON");
        message.append("BETTER LUCK NEXT TIME");
        message.append("\n\n");
        message.append("\t\t\t\tSINCERELY");
        message.append("\t\t\tTHE OREGON CITY CHAMBER OF COMMERCE");
    }

    public int goShopHuntContinue(int options) {

        /*
            2080 PRINT "DO YOU WANT TO (1) STOP AT THE NEXT FORT, (2) HUNT, ";
            2090 PRINT "OR (3) CONTINUE"
            2091 LET X = 0
            2100 INPUT X
            2110 IF X>2 THEN 2150
            2120 IF X<1 THEN 2150
            2130 LET X=INT(X)
            2140 GOTO 2270
            2150 LET X=3
            2160 GOTO 2270

            2170 PRINT "DO YOU WANT TO (1) HUNT, OR (2) CONTINUE"
            2181 LET X = 0
            2180 INPUT X
            2190 IF X=1 THEN 2210
            2200 LET X=2
            2210 LET X=X+1
            2220 IF X=3 THEN 2260
         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Go Shop Hunt Continue" + gameData.ANSI_RESET);

        }

        int choice = 0;
        StringBuilder output = new StringBuilder("DO YOU WANT TO: \n");
        output.append("(1) Continue\n");
        boolean done = false;


        switch (options) {
            // Hunt or Continue
            case 1:
                output.append("(2) Hurt\n");

                break;
            // Shop or Hunt or Continue

            case 2:
                output.append("(2) Hurt\n");
                output.append("(3) Shop at next fort\n");
                break;
        }

        while (!done) {
            System.out.println(output);

            choice = getInt();

            if (choice >= 1 && choice <= 3) {
                done = true;
            } else {
                System.out.println("Bad input, try again!");
            }
        }

        return choice;


    }

    public boolean goHunting() {

        /*
            2230 IF B>39 THEN 2260
            2240 PRINT "TOUGH YOU NEED MORE BULLETS TO GO HUNTING"
            2250 GOTO 2170
         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Go Hunting" + gameData.ANSI_RESET);

        }

        if (!(gameData.ammunition > 39)) {
            System.out.println("TOUGH---YOU NEED M0RE BULLETS TO GO HUNTING");
            return true;
        }

        /*
            2570 LET M=M-45
         */

        gameData.totalMilesWholeTrip -= 45;


       /*
            2580 GOSUB 6140
        */

        goShooting();

        /*
            2590 IF B1 <= 1 THEN 2660
            2600 IF 100*RND()<13*B1 THEN 2710
            2610 LET F=F+48-2*B1
            2620 PRINT "NICE SHOT--RIGHT ON TARGET--GOOD EATIN' TONIGHT!!"
            2630 LET B=B-10-3*B1
            2640 GOTO 2720

            REM **BELLS IN LINE 2660**
            2660 PRINT "RIGHT BETWEEN THE EYES---YOU GOT A BIG ONE!!!!"
            2670 PRINT "FULL BELLIES TONIGHT!"
            2680 LET F=F+52+RND()*6
            2690 LET B=B-10-RND()*4
            2700 GOTO 2720
            2710 PRINT "YOU MISSED---AND YOUR DINNER GOT AWAY....."
         */

        if (!(this.shotResponseTime <= 1)) {
            if (!(rnd.nextInt(100) + 1 < 13 * this.shotResponseTime)) {
                gameData.food += 48 - 2 * this.shotResponseTime;
                System.out.println("NICE SHOT--RIGHT ON TARGET--GOOD EATING' TONIGHT!!");
                gameData.ammunition -= 10 - 3 * this.shotResponseTime;
            } else {
                System.out.println("YOU MISSED---AND YOUR DINNER GOT AWAY.....");
            }

        } else {
            System.out.println("RIGHT BETWEEN THE EYES---YOU GOT A BIG ONE!!!!");
            System.out.println("FULL BELLIES TONIGHT!");
            gameData.food += 52 + rnd.nextInt(6);
            gameData.ammunition -= 10 - rnd.nextInt(4);
        }

        return false;
    }

    public void goEat() {

        /*

            REM ***EATING***
            2750 PRINT "DO YOU WANT TO EAT (1) POORLY   (2) MODERATELY"
            2760 PRINT "OR (3) WELL";
            2770 LET E = 0
            2771 INPUT E
            2780 IF E>3 THEN 2750
            2790 IF E<1 THEN 2750
            2800 LET E=INT(E)
            2810 LET F=F-8-5*E
            2820 IF F >= 0 THEN 2860
            2830 LET F=F+8+5*E
            2840 PRINT "YOU CAN'T EAT THAT WELL"
            2850 GOTO 2750

            2870 LET L1=0
            2871 LET C1=0
         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Go Eat" + gameData.ANSI_RESET);

        }

        boolean done = false;
        int input = 0;

        StringBuilder message = new StringBuilder("DO YOU WANT TO EAT: \n")
                .append("(1) poorly\n")
                .append("(2) moderately\n")
                .append("(3) well\n");


        while (!done) {

            System.out.println(message);


            input = getInt();

            if (input >= 1 && input <= 3) {
                int check = 8 - 5 * input;
                System.out.println("Check: " + check + " Food: " + gameData.food);

                printSupplies();

                if (gameData.food < check) {
                    System.out.println("YOU CAN'T EAT THAT WELL");
                } else {
                    gameData.food -= check;
                    done = true;
                }


            } else {
                System.out.println(gameData.ANSI_RED + "You didn't enter 1-3, try again." + gameData.ANSI_RESET);
                System.out.println();
            }

        }

        gameData.isBlizzard = false;
        gameData.isIllness = false;

        this.howToEat = input;
    }

    public boolean callRaiders() {

        /*
            REM ***RIDERS ATTACK***
            2890 IF RND()*10>((M/100-4)^2+72)/((M/100-4)^2+12)-1 THEN 3550
            2900 PRINT "RIDERS AHEAD.   THEY ";
            2910 LET S5=0
            2920 IF RND()<.8 THEN 2950
            2930 PRINT "DON'T ";
            2940 LET S5=1
            2950 PRINT "LOOK HOSTILE"
            2960 PRINT "TACTICS"
            2970 PRINT "(1) RUN  (2) ATTACK  (3) CONTINUE  (4) CIRCLE WAGONS"
            2980 IF RND()>.2 THEN 3000
            2990 LET S5=1-S5
            3000 LET T1 = 0
            3001 INPUT T1
            3010 IF T1<1 THEN 2970
            3020 IF T1>4 THEN 2970
            3030 LET T1=INT(T1)
            3040 IF S5=1 THEN 3330
            3050 IF T1>1 THEN 3110
            3060 LET M=M+20
            3070 LET M1=M1-15
            3080 LET B=B-150
            3090 LET A=A-40
            3100 GOTO 3470
            3110 IF T1>2 THEN 3240
            3120 GOSUB 6140
            3130 LET B=B-B1*40-80
            3140 IF B1>1 THEN 3170
            3150 PRINT "NICE SHOOTING---YOU DROVE THEM OFF"
            3160 GOTO 3470
            3170 IF B1 <= 4 THEN 3220
            3180 PRINT "LOUSY SHOT---YOU GOT KNIFED"
            3190 LET K8=1
            3200 PRINT "YOU HAVE TO SEE OL' DOC BLANCHARD"
            3210 GOTO 3470
            3220 PRINT "KINDA SLOW WITH YOUR COLT .45"
            3230 GOTO 3470
            3240 IF T1>3 THEN 3290
            3250 IF RND()>.8 THEN 3450
            3260 LET B=B-150
            3270 LET M1=M1-15
            3280 GOTO 3470
            3290 GOSUB 6140
            3300 LET B=B-B1*30-80
            3310 LET M=M-25
            3320 GOTO 3140
            3330 IF T1>1 THEN 3370
            3340 LET M=M+15
            3350 LET A=A-10
            3360 GOTO 3470
            3370 IF T1>2 THEN 3410
            3380 LET M=M-5
            3390 LET B=B-100
            3400 GOTO 3470
            3410 IF T1>3 THEN 3430
            3420 GOTO 3470
            3430 LET M=M-20
            3440 GOTO 3470
            3450 PRINT "THEY DID NOT ATTACK"
            3460 GOTO 3550
            3470 IF S5=0 THEN 3500
            3480 PRINT "RIDERS WERE FRIENDLY, BUT CHECK FOR POSSIBLE LOSSES"
            3490 GOTO 3550
            3500 PRINT "RIDERS WERE HOSTILE--CHECK FOR LOSSES"
            3510 IF B >= 0 THEN 3550
            3520 PRINT "YOU RAN OUT OF BULLETS AND GOT MASSACRED BY THE RIDERS"
            3530 GOTO 5170
         */
        String holdText = "THEY ";
        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Raiders" + gameData.ANSI_RESET);

        }

        boolean done = false;
        int input = 0;

        System.out.println("RIDERS AHEAD.");
        this.isHostility = true;
        if (!(rnd.nextFloat() < .8)) {
            holdText += "DON'T ";
            //System.out.println("DON'T ");
            this.isHostility = false;
        }

        StringBuilder message = new StringBuilder(holdText + "LOOK HOSTILE\n")
                .append("TACTICS:\n")
                .append("(1) Run\n")
                .append("(2) Attack\n")
                .append("(3) Continue\n")
                .append("(4) Circle Wagons\n");


        while (!done) {
            System.out.println(message);
            try {
                input = userInput.nextInt();
                userInput.nextLine();
                if (input >= 1 && input <= 4) {
                    done = true;
                } else {
                    System.out.println(gameData.ANSI_RED + "You must enter 1-4, try again." + gameData.ANSI_RESET);
                    System.out.println();
                }

            } catch (InputMismatchException e) {
                System.out.println(gameData.ANSI_RED + "Numbers only!" + gameData.ANSI_RESET);
                userInput.next();
                System.out.println();
            }

        }

        if (!(rnd.nextFloat() > .2)) {
            this.isHostility = !this.isHostility;
        }

        // if hostily of riders == 1 do something

        if (gameData.debug) {

            System.out.println(gameData.ANSI_RED + "Is Hostility: " + this.isHostility + gameData.ANSI_RESET);

        }


        if (isHostility) {
            switch (input) {
                case 1:
                    gameData.totalMilesWholeTrip += 20;
                    gameData.miscSupplies -= 15;
                    gameData.ammunition -= 150;
                    gameData.oxen -= 40;
                    break;

                case 2:
                case 4:
                    goShooting();
                    if (input == 2) {
                        gameData.ammunition -= this.shotResponseTime * 40 - 80;
                    } else {
                        gameData.ammunition -= this.shotResponseTime * 30 - 80;
                    }

                    if (this.shotResponseTime > 1) {
                        if (this.shotResponseTime <= 4) {
                            System.out.println("KINDA SLOW WITH YOUR COLT .45");
                        } else {
                            System.out.println("LOUSY SHOT---YOU GOT KNIFED");
                            System.out.println("YOU HAVE TO SEE OL' DOC BLANCHARD");
                            gameData.isInjury = true;
                        }
                    } else {
                        System.out.println("NICE SHOOTING---YOU DROVE THEM OFF");
                    }

                    break;

                case 3:
                    if (rnd.nextFloat() > .8) {
                        System.out.println("THEY DID NOT ATTACK");
                    } else {
                        gameData.ammunition -= 150;
                        gameData.miscSupplies -= 15;
                    }
                    break;

            }
            System.out.println("RIDERS WERE HOSTILE--CHECK FOR LOSSES");
            if (gameData.ammunition < 0) {
                System.out.println("YOU RAN OUT OF BULLETS AND GOT MASSACRED BY THE RIDERS");
                return true;
            }

        } else {
            switch (input) {
                case 1:
                    this.gameData.totalMilesWholeTrip += 15;
                    gameData.oxen -= 10;
                    break;

                case 2:
                    this.gameData.totalMilesWholeTrip -= 5;
                    gameData.ammunition -= 100;
                    break;

                case 4:
                    this.gameData.totalMilesWholeTrip -= 20;
            }
        }

        return false;
    }

    private void goShooting() {

        /*
            REM ***SHOOTING SUB-ROUTINE***
            6131 REM THE METHOD OF TIMING THE SHOOTING
            6132 REM WILL VARY FROM SYSTEM TO SYSTEM.
            6133 REM USERS WILL PROBABLY PREFER TO USE
            6134 REM IF TIMING ON THE USER'S SYSTEM IS HIGHLY SUSCEPTIBLE
            6135 REM TO SYSTEM RESPONSE TIME, THE FORMULA IN LINE 6240 CAN
            6136 REM BE TAILORED TO ACCOMODATE THIS BY EITHER INCREASING
            6137 REM OR DECREASING THE 'SHOOTING' TIME RECORDED BY THE SYSTEM.
            6140 REM MOVED TO TOP OF PROGRAM DIM S$(5)
            6150 LET S$(1)="bang"
            6160 LET S$(2)="blam"
            6170 LET S$(3)="pow"
            6180 LET S$(4)="wham"
            6190 LET S6=INT(RND()*4+1)
            6200 PRINT "TYPE "; S$(S6)
            6210 LET B3 = CLK(0)
            6220 INPUT C$
            6230 LET B1 = CLK(0)
            6240 LET B1=((B1-B3)*2)-(D9-1)
            6250 PRINT
            6252 REM PRINT "B1 = " ; B1
            6255 IF B1>0 THEN 6260
            6257 LET B1=0
            6260 IF C$=S$(S6) THEN 6280
            6270 LET B1=9
            6280 RETURN
         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Go Shooting" + gameData.ANSI_RESET);

        }

        String[] shotWords = {"bang", "blam", "pow", "wham"};
        long shotStartTime = 0;
        long shotEndTime = 0;
        int getTime = 0;

        StringBuilder shot = new StringBuilder()
                .append("TYPE: ")
                .append(shotWords[0])
                .append(", ")
                .append(shotWords[1])
                .append(", ")
                .append(shotWords[2])
                .append(", ")
                .append("and ")
                .append(shotWords[3])
                .append("\n");

        System.out.println(shot);

        shotStartTime = System.currentTimeMillis();


        input = userInput.nextLine();

        shotEndTime = System.currentTimeMillis();
        getTime = (int) (shotEndTime - shotStartTime) / 1000;

        this.shotResponseTime = ((getTime * 2) - (gameData.shootingExpertiseLevel - 1));

        if (!(shotResponseTime > 0)) {
            shotResponseTime = 0;
        }

        if (!input.equalsIgnoreCase(shotWords[rnd.nextInt(4)])) {
            shotResponseTime = 9;
        }
    }

    public void callWagonBreaksDown() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Wagon Break Down" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("WAGON BREAKS DOWN--LOSE TIME AND SUPPLIES FIXING IT");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= 15 - (rnd.nextInt(5) + 1);
        gameData.miscSupplies -= 8;
    }

    public void callOxInjures() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Oxen Injures" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("OX INJURES LEG--SLOWS YOU DOWN REST OF TRIP");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= 25;
        gameData.oxen -= 20;
    }

    public void callBadLuck() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call BadLuck" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("BAD LUCK--YOUR DAUGHTER BROKE HER ARM");
        System.out.println("YOU HAD TO STOP AND USE SUPPLIES TO MAKE A SLING");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= 5 - (rnd.nextInt(4) + 1);
        gameData.miscSupplies -= 2 - (rnd.nextInt(3) + 1);
    }

    public void callOxWandersOff() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Oxen Wanders Off" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("OX WANDERS OFF--SPEND TIME LOOKING FOR IT");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= 17;
    }

    public void callSonGetsLost() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Son Gets Lost" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("YOUR SON GETS LOST---SPEND HALF THE DAY LOOKING FOR HIM");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= 10;
    }

    public void callUnsafeWater() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call UnsafeWater" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("UNSAFE WATER--LOSE TIME LOOKING FOR CLEAN SPRING");
        System.out.println();
        this.gameData.totalMilesWholeTrip -= rnd.nextInt(10) - 2;
    }

    public void callHeavyRains() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Go Heavy Rains" + gameData.ANSI_RESET);

        }

        System.out.println("HEAVY RAINS---TIME AND SUPPLIES LOST");
        gameData.food -= 10;
        gameData.ammunition -= 500;
        gameData.miscSupplies -= 15;
        this.gameData.totalMilesWholeTrip -= rnd.nextInt(10) - 5;

    }

    public void callColdWeather() {

        /*
            4490 PRINT "COLD WEATHER---BRRRRRRR!---YOU ";
            4500 IF C>22+4*RND() THEN 4530
            4510 PRINT "DON'T ";
            4520 LET C1=1
            4530 PRINT "HAVE ENOUGH CLOTHING TO KEEP YOU WARM"
            4540 IF C1=0 THEN 4710
            4550 GOTO 6300
         */

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Cold Weather" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("COLD WEATHER---BRRRRRRR!---YOU ");
        System.out.println();

        if (!(gameData.clothing > 22 + rnd.nextInt(4))) {
            System.out.println("DON'T ");
            gameData.isIllness = true;
        }

        System.out.println("HAVE ENOUGH CLOTHING TO KEEP YOU WARM");
    }

    public void callBanditsAttack() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Bandits Attack" + gameData.ANSI_RESET);

        }

        goShooting();
        gameData.ammunition -= 20 * shotResponseTime;
        if (gameData.ammunition >= 0) {
            if (shotResponseTime <= 1) {
                System.out.println();
                System.out.println("QUICKEST DRAW OUTSIDE OF DODGE CITY!!!");
                System.out.println("YOU GOT 'EM!");
                System.out.println();
            } else {
                System.out.println();
                System.out.println("YOU GOT SHOT IN THE LEG AND THEY TOOK ONE OF YOUR OXEN");
                System.out.println("BETTER HAVE A DOC LOOK AT YOUR WOUND");
                System.out.println();
                gameData.miscSupplies -= 5;
                gameData.ammunition -= 20;
                gameData.isInjury = true;
            }
        } else {
            System.out.println("YOU RAN OUT OF BULLETS---THEY GET LOTS OF CASH");
            gameData.money /= 3;
        }
    }

    public void callFireInWagon() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Fire In Wagon" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("THERE WAS A FIRE IN YOUR WAGON--FOOD AND SUPPLIES DAMAGE!");
        System.out.println();
        gameData.food -= 40;
        gameData.ammunition -= 500;
        gameData.miscSupplies -= rnd.nextInt(8) - 3;
        gameData.totalMilesWholeTrip -= 15;
    }

    public void callLostFog() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Lost Fog" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("LOSE YOUR WAY IN HEAVY FOG---TIME IS LOST");
        System.out.println();
        gameData.totalMilesWholeTrip -= 10 - rnd.nextInt(5);
    }

    public void callSnakeBit() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Snake Bit" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("YOU KILLED A POISONOUS SNAKE AFTER IT BIT YOU");
        System.out.println();
        gameData.ammunition -= 10;
        gameData.miscSupplies -= 5;
        if (!(gameData.miscSupplies >= 0)) {
            System.out.println("YOU DIE OF SNAKEBITE SINCE YOU HAVE NO MEDICINE");
            System.out.println();
            gameData.isDying = true;
            gameData.howDie = 3;
        }
    }

    public void callWagonSwawped() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Wagon Swapped" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("WAGON GETS SWAMPED FORDING RIVER--LOSE FOOD AND CLOTHES");
        System.out.println();
        gameData.food -= 30;
        gameData.clothing -= 20;
        gameData.totalMilesWholeTrip -= 20 - rnd.nextInt(20);
    }

    public void callWildAnimals() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Wild Animals" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("WILD ANIMALS ATTACK!");

        goShooting();

        if (gameData.ammunition > 39) {
            if (shotResponseTime > 2) {
                System.out.println("SLOW ON THE DRAW---THEY GOT AT YOUR FOOD AND CLOTHES");
                gameData.ammunition -= 20 * shotResponseTime;
                gameData.clothing -= shotResponseTime * 4;
                gameData.food -= shotResponseTime * 8;
            } else {
                System.out.println("NICE SHOOTIN' PARDNER---THEY DIDN'T GET MUCH");
            }
        } else {
            System.out.println("YOU WERE TOO LOW ON BULLETS--");
            System.out.println("THE WOLVES OVERPOWERED YOU");
            gameData.isInjury = true;
        }

        System.out.println();
    }

    public void callHailStorm() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Hail Storm" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("HAIL STORM---SUPPLIES DAMAGED");
        System.out.println();
        gameData.totalMilesWholeTrip -= rnd.nextInt(5) * 10;
        gameData.ammunition -= 200;
        gameData.miscSupplies -= rnd.nextInt(4) * 3;
    }

    public void callHelpfulIndins() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Helpful indins" + gameData.ANSI_RESET);

        }

        System.out.println();
        System.out.println("HELPFUL INDIANS SHOW YOU WERE TO FIND MORE FOOD");
        System.out.println();
        gameData.food += 14;
    }

    public void callIsIllness() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Is Illness" + gameData.ANSI_RESET);

        }

        System.out.println();
        if (rnd.nextInt(100) < 10 + 35 * (howToEat - 1)) {
            System.out.println("MILD ILLNESS---MEDICINE USED");
            this.gameData.totalMilesWholeTrip -= 5;
            gameData.miscSupplies -= 2;
        } else if (rnd.nextInt(100) < 100 - (40 / 4 ^ (howToEat - 1))) {
            System.out.println("BAD ILLNESS---MEDICINE USED");
            this.gameData.totalMilesWholeTrip -= 5;
            gameData.miscSupplies -= 5;
        } else {
            System.out.println("SERIOUS ILLNESS");
            System.out.println("YOU MUST STOP FOR MEDICAL ATTENTION");
            gameData.miscSupplies -= 10;
            gameData.isIllness = true;
        }

        if (gameData.miscSupplies < 0) {
            this.gameData.isDying = true;
            this.gameData.howDie = 3;
        } else {
            // TODO
            gameData.isBlizzard = true;
        }

        System.out.println();

    }

    public void callMountains() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Mountains" + gameData.ANSI_RESET);
            System.out.println("Miles" + gameData.ANSI_PURPLE + gameData.totalMilesWholeTrip + gameData.ANSI_RESET);

        }

        int cal = (this.gameData.totalMilesWholeTrip / 100 - 15) * 2;

        System.out.println();
        if (!(rnd.nextInt(10) > 9 - ((cal + 72) / (cal + 12)))) {

            System.out.println("RUGGED MOUNTAINS");

            if (!(rnd.nextFloat() > .1)) {
                System.out.println("YOU GOT LOST---LOSE VALUABLE TIME TRYING TO FIND TRAIL!");
                gameData.totalMilesWholeTrip -= 60;
            } else {
                if (!(rnd.nextFloat() > .11)) {
                    System.out.println("WAGON DAMAGED!-LOSE TIME AND SUPPLIES");
                    gameData.miscSupplies -= 5;
                    gameData.ammunition -= 200;
                    gameData.totalMilesWholeTrip -= 20 - rnd.nextInt(30);
                } else {
                    System.out.println("THE GOING GETS SLOW");
                    gameData.totalMilesWholeTrip -= 45 - rnd.nextFloat() / .02;
                }
            }
        }
        boolean runBlizzard = false;
        boolean checkMileage950 = false;
        boolean checkMilage1700 = false;

        if (clearSouthPass) {
            checkMilage1700 = true;
        } else {
            clearSouthPass = true;

            if (rnd.nextFloat() < .8) {
                runBlizzard = true;
            } else {
                System.out.println("YOU MADE IT SAFELY THROUGH SOUTH PASS--NO SNOW");
                checkMilage1700 = true;
            }
        }

        if (checkMilage1700) {
            if (gameData.totalMilesWholeTrip < 1700) {
                checkMileage950 = true;
            } else {
                if (clearBlueMountains) {
                    checkMileage950 = true;
                } else {
                    clearBlueMountains = true;
                    if (rnd.nextFloat() < .7) {
                        runBlizzard = true;
                    } else {
                        checkMileage950 = true;
                    }
                }
            }
        }

        if (runBlizzard) {
            System.out.println("BLIZZARD IN MOUNTAIN PASS--TIME AND SUPPLIES LOST");
            gameData.isBlizzard = true;
            gameData.food -= 25;
            gameData.miscSupplies -= 10;
            gameData.ammunition -= 300;
            gameData.totalMilesWholeTrip -= 30 - rnd.nextInt(40);
            if (gameData.clothing < 18 * rnd.nextInt(2)) {
                gameData.isIllness = true;
                checkMileage950 = false;
            }
        }

        if (checkMileage950) {
            if (!(gameData.totalMilesWholeTrip > 950)) {
                clearSouthPassMileage = true;
            }
        }
        System.out.println();

        if (gameData.debug) {
            System.out.println("Miles" + gameData.ANSI_PURPLE + gameData.totalMilesWholeTrip + gameData.ANSI_RESET);
        }
    }

    public void createGameDates() {

        /*
            1280 IF D3>10 THEN 1300
            1290 ON D3 GO TO 1310, 1330, 1350, 1370, 1390, 1410, 1430, 1450, 1470, 1490
            1300 ON D3-10 GO TO 1510, 1530, 1550, 1570, 1590, 1610, 1630, 1650, 1670, 1690
            1310 PRINT "APRIL 12 ";
            1320 GOTO 1720
            1330 PRINT "APRIL 26 ";
            1340 GOTO 1720
            1350 PRINT "MAY 10 ";
            1360 GOTO 1720
            1370 PRINT "MAY 24 ";
            1380 GOTO 1720
            1390 PRINT "JUNE 7 ";
            1400 GOTO 1720
            1410 PRINT "JUNE 21 ";
            1420 GOTO 1720
            1430 PRINT "JULY 5 ";
            1440 GOTO 1720
            1450 PRINT "JULY 19 ";
            1460 GOTO 1720
            1470 PRINT "AUGUST 2 ";
            1480 GOTO 1720
            1490 PRINT "AUGUST 16 ";
            1500 GOTO 1720
            1510 PRINT "AUGUST 31 ";
            1520 GOTO 1720
            1530 PRINT "SEPTEMBER 13 ";
            1540 GOTO 1720
            1550 PRINT "SEPTEMBER 27 ";
            1560 GOTO 1720
            1570 PRINT "OCTOBER 11 ";
            1580 GOTO 1720
            1590 PRINT "OCTOBER 25 ";
            1600 GOTO 1720
            1610 PRINT "NOVEMBER 8 ";
            1620 GOTO 1720
            1630 PRINT "NOVEMBER 22 ";
            1640 GOTO 1720
            1650 PRINT "DECEMBER 6 ";
            1660 GOTO 1720
            1670 PRINT "DECEMBER 20 ";
            1680 GOTO 1720
         */
        gameDates[0] = "April 12";
        gameDates[1] = "April 26";
        gameDates[2] = "May 10";
        gameDates[3] = "May 24";
        gameDates[4] = "June 7";
        gameDates[5] = "June 21";
        gameDates[6] = "July 5";
        gameDates[7] = "July 19";
        gameDates[8] = "August 2";
        gameDates[9] = "August 16";
        gameDates[10] = "August 31";
        gameDates[11] = "September 13";
        gameDates[12] = "September 27";
        gameDates[13] = "October 11";
        gameDates[14] = "October 25";
        gameDates[15] = "November 8";
        gameDates[16] = "November 22";
        gameDates[17] = "December 6";
        gameDates[18] = "December 20";


    }

    public void initialPurchases() {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Initial Purchases" + gameData.ANSI_RESET);
        }

        boolean spendDone = false;

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

            spent = gameData.ammunition + gameData.miscSupplies + gameData.food + gameData.oxen + gameData.clothing;

            if (gameData.money - spent >= 0) {
                spendDone = true;
                gameData.money -= spent;
            } else {
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

        gameData.ammunition *= 50;

        System.out.println("AFTER ALL YOUR PURCHASES, YOU NOW HAVE " + gameData.money + " DOLLARS LEFT");
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

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Shop Fort" + gameData.ANSI_RESET);
        }
        System.out.println("ENTER WHAT YOU WISH TO SPEND ON THE FOLLOWING");
        buyFood(2);
        buyAmmunition(2);
        buyClothing(2);
        buyMiscSupplies(2);
    }

    public void printSupplies() {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Print Supplies" + gameData.ANSI_RESET);
        }

        /*
            2040 PRINT "FOOD","BULLETS","CLOTHING","MISC. SUPP.","CASH"
            2050 PRINT F,B,C,M1,T
         */

        StringBuilder sb = new StringBuilder();
        sb.append("Food: " + gameData.food + "\n");
        sb.append("Bullets: " + gameData.ammunition + "\n");
        sb.append("Clothing: " + gameData.clothing + "\n");
        sb.append("Misc. Supplies: " + gameData.miscSupplies + "\n");

        System.out.println(sb);

    }

    public void buyOxen() {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Buy Oxen" + gameData.ANSI_RESET);
        }

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
        double amount = 0.0;

        while (!done) {
            System.out.println("HOW MUCH DO YOU WANT TO SPEND ON YOUR OXEN TEAM");

            amount = getDouble();

            if (amount <= 199) {
                System.out.println("NOT ENOUGH");

            } else if (amount >= 299) {
                System.out.println("TOO MUCH");

            } else {
                gameData.oxen = amount;
                done = true;
            }

        }
    }

    public void buyFood(int method) {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Buy Food" + gameData.ANSI_RESET);
        }

        /*
            930 PRINT "HOW MUCH DO YOU WANT TO SPEND ON FOOD";
            931 LET F = 0
            940 INPUT F
            950 IF F >= 0 THEN 980
            960 PRINT "IMPOSSIBLE"
            970 GOTO 930
        */

        boolean doItAgain;
        double amount;

        switch (method) {
            case 1:
                do {

                    doItAgain = false;

                    System.out.println("HOW MUCH DO YOU WANT TO SPEND ON FOOD");

                    amount = getDouble();

                    if (amount >= 0) {
                        gameData.food = amount;

                    } else {
                        System.out.println("IMPOSSIBLE");
                        doItAgain = true;
                    }

                } while (doItAgain);

                break;

            case 2:
                System.out.println("Food");

                amount = getDouble();

                if (gameData.money >= amount) {
                    gameData.money -= amount;
                    gameData.food += 2 / 3 * amount;
                } else {
                    System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                    System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                }
                break;

        }


    }

    public void buyAmmunition(int method) {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Buy Ammunition" + gameData.ANSI_RESET);
        }

        /*
            980 PRINT "HOW MUCH DO YOU WANT TO SPEND ON AMMUNITION";
            981 LET B = 0
            990 INPUT B
            1000 IF B >= 0 THEN 1030
            1010 PRINT "IMPOSSIBLE"
            1020 GOTO 980

        */

        boolean doItAgain;
        double amount = 0;

        switch (method) {

            case 1:

                do {
                    doItAgain = false;

                    System.out.println("HOW MUCH DO YOU WANT TO SPEND ON AMMUNITION");

                    amount = getDouble();

                    if (amount >= 0) {
                        gameData.ammunition = amount;

                    } else {
                        System.out.println("IMPOSSIBLE");
                        doItAgain = true;
                    }

                } while (doItAgain);
                break;
            case 2:
                System.out.println("Ammunition");

                amount = getDouble();

                if (gameData.money >= amount) {
                    gameData.money -= amount;
                    gameData.ammunition += 2.0 / 3.0 * amount * 50;
                } else {
                    System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                    System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                }
                break;

        }

    }

    public void buyClothing(int method) {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Buy Clothing" + gameData.ANSI_RESET);
        }

        /*
            1030 PRINT "HOW MUCH DO YOU WANT TO SPEND ON CLOTHING";
            1031 LET C = 0
            1040 INPUT C
            1050 IF C >= 0 THEN 1080
            1060 PRINT "IMPOSSIBLE"
            1070 GOTO 1030
         */

        boolean doItAgain;
        double amount = 0;


        switch (method) {
            case 1:
                do {

                    doItAgain = false;

                    System.out.println("HOW MUCH DO YOU WANT TO SPEND ON CLOTHING");

                    amount = getDouble();

                    if (amount >= 0) {
                        gameData.clothing = amount;

                    } else {
                        System.out.println("IMPOSSIBLE");
                        doItAgain = true;
                    }
                } while (doItAgain);

                break;

            case 2:
                System.out.println("Clothing");

                amount = getDouble();

                if (gameData.money >= amount) {
                    gameData.money -= amount;
                    gameData.clothing += 2.0 / 3.0 * amount;
                } else {
                    System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                    System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                }
                break;

        }

    }

    public void buyMiscSupplies(int method) {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Misc. Supplies" + gameData.ANSI_RESET);
        }

        /*
            1080 PRINT "HOW MUCH DO YOU WANT TO SPEND ON MISCELLANEOUS SUPPLIES";
            1081 LET M1 = 0
            1090 INPUT M1
            1100 IF M1 >= 0 THEN 1130
            1110 PRINT "IMPOSSIBLE"
            1120 GOTO 1080
         */

        boolean doItAgain;
        double amount = 0;


        switch (method) {
            case 1:
                do {

                    doItAgain = false;

                    System.out.println("HOW MUCH DO YOU WANT TO SPEND ON MISCELLANEOUS SUPPLIES");

                    amount = getDouble();

                    if (amount >= 0) {
                        gameData.miscSupplies = amount;

                    } else {
                        System.out.println("IMPOSSIBLE");
                        doItAgain = true;
                    }

                } while (doItAgain);

                break;
            case 2:
                System.out.println("Miscellaneous Supplies");

                amount = getDouble();

                if (gameData.money >= amount) {
                    gameData.money -= amount;
                    gameData.miscSupplies += 2.0 / 3.0 * amount;
                } else {
                    System.out.println("YOU DON'T HAVE THAT MUCH--KEEP YOUR SPENDING DOWN");
                    System.out.println("YOU MISS YOUR CHANCE TO SPEND ON THAT ITEM");
                }

                break;
        }


    }

    public boolean callSetDate() {

        if (gameData.debug) {

            System.out.println(gameData.ANSI_CYAN + "Call Set Date" + gameData.ANSI_RESET);

        }


        System.out.println();

        if (gameData.gameDate >= 19) {
            System.out.println("YOU HAVE BEEN ON THE TRAIL TOO LONG ------");
            System.out.println("YOUR FAMILY DIES IN THE FIRST BLIZZARD OF WINTER");
            return true;
        }

        System.out.println("Monday " + gameDates[gameData.gameDate]);

        System.out.println();
        return false;

    }

    public void callFinalTurn() {

        /*
            REM ***FINAL TURN***
            5430 LET F9=(2040-M2)/(M-M2)
            5440 LET F=F+(1-F9)*(8*5*E)
            5450 PRINT

            REM **BELLS IN LINES 5470, 5480**
            5470 PRINT "YOU FINALLY ARRIVED AT OREGON CITY"
            5480 PRINT "AFTER 2040 LONG MILES---HOORAY !!!!!"
            5490 PRINT "A REAL PIONEER!"
            5500 PRINT
            5510 LET F9=INT(F9*14)
            5520 LET D3=D3*14+F9
            5530 LET F9=F9+1
            5540 IF F9<8 THEN 5560
            5550 LET F9=F9-7
            5560 ON F9 GO TO 5570,5590,5630,5650,5670,5690
            5570 PRINT "MONDAY ";
            5580 GOTO 5700
            5590 PRINT "TUESDAY ";
            5600 GOTO 5700
            5610 PRINT "WEDNESDAY ";
            5620 GOTO 5700
            5630 PRINT "THURSDAY ";
            5640 GOTO 5700
            5650 PRINT "FRIDAY ";
            5660 GOTO 5700
            5670 PRINT "SATURDAY ";
            5680 GOTO 5700
            5690 PRINT "SUNDAY ";
            5700 IF D3>124 THEN 5740
            5710 LET D3=D3-93
            5720 PRINT "JULY ";D3;" 1847"
            5730 GOTO 5920
            5740 IF D3>155 THEN 5780
            5750 LET D3=D3-124
            5760 PRINT "AUGUST ";D3;" 1847"
            5770 GOTO 5920
            5780 IF D3>155 THEN 5820
            5790 LET D3=D3-155
            5800 PRINT "SEPTEMBER ";D3;" 1847"
            5810 GOTO 5920
            5820 IF D3>216 THEN 5860
            5830 LET D3=D3-155
            5840 PRINT "OCTOBER ";D3;" 1847"
            5850 GOTO 5920
            5860 IF D3>246 THEN 5900
            5870 LET D3=D3-246
            5880 PRINT "NOVEMBER ";D3;" 1847"
            5890 GOTO 5920
            5900 LET D3=D3-246
            5910 PRINT "DECEMBER ";D3;" 1847"
            5920 PRINT
            5930 PRINT "FOOD","BULLETS","CLOTHING","MISC. SUPP.","CASH"
            5940 IF B>0 THEN 5960
            5950 LET B=0
            5960 IF C>0 THEN 5980
            5970 LET C=0
            5980 IF M1>0 THEN 6000
            5990 LET M1=0
            6000 IF T>0 THEN 6020
            6010 LET T=0
            6020 IF F>0 THEN 6040
            6030 LET F=0
            6040 PRINT INT(F),INT(B),INT(C),INT(M1),INT(T)
            6050 PRINT
            6060 PRINT TAB(11); "PRESIDENT JAMES K. POLK SENDS YOU HIS"
            6070 PRINT TAB(17); "HEARTIEST CONGRATULATIONS"
            6080 PRINT
            6090 PRINT TAB(11);"AND WISHES YOU A PROSPEROUS LIFE AHEAD"
            6100 PRINT
            6110 PRINT TAB(22);"AT YOUR NEW HOME"
         */

        if (gameData.debug) {
            System.out.println();
            System.out.println(gameData.ANSI_CYAN);
            System.out.println("Call Final Turn");
            System.out.println(gameData.ANSI_RESET);
            System.out.println();
        }

        float cal2WeeksOnTurn = (float) (2040 - gameData.milesLastTurn) / (this.gameData.totalMilesWholeTrip - gameData.milesLastTurn);
        System.out.println("YOU FINALLY ARRIVED AT OREGON CITY");
        System.out.println("AFTER 2040 LONG MILES---HOORAY !!!!!");
        System.out.println("A REAL PIONEER!");

        int cal2WeeksOnTurnInt = (int) cal2WeeksOnTurn * 14;
        this.gameData.gameDate *= 14 + cal2WeeksOnTurnInt * 14;
        cal2WeeksOnTurnInt++;
        if (cal2WeeksOnTurnInt > 8) {
            cal2WeeksOnTurnInt -= 7;
        }

        String[] dayWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println(dayWeek[cal2WeeksOnTurnInt]);

        if (!(gameData.gameDate > 124)) {
            gameData.gameDate -= 93;
            System.out.println("JULY " + gameData.gameDate + " 1847");
        } else if (!(gameData.gameDate > 155)) {
            gameData.gameDate -= 124;
            System.out.println("AUGUST " + gameData.gameDate + " 1847");
        } else if (!(gameData.gameDate > 185)) {
            gameData.gameDate -= 155;
            System.out.println("SEPTEMBER " + gameData.gameDate + " 1847");
        } else if (!(gameData.gameDate > 216)) {
            gameData.gameDate -= 185;
            System.out.println("OCTOBER " + gameData.gameDate + " 1847");
        } else if (!(gameData.gameDate > 246)) {
            gameData.gameDate -= 216;
            System.out.println("NOVEMBER " + gameData.gameDate + " 1847");
        } else {
            gameData.gameDate -= 246;
            System.out.println("DECEMBER " + gameData.gameDate + " 1847");
        }

        printSupplies();

        System.out.println("\t\t\t\t\tPRESIDENT JAMES K. POLK SENDS YOU HIS");
        System.out.println("\t\t\t\t\t\t\tHEARTIEST CONGRATULATIONS");
        System.out.println();
        System.out.println("\t\t\t\t\tAND WISHES YOU A PROSPEROUS LIFE AHEAD");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tAT YOUR NEW HOME");
    }

    public int getInt() {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Get Int" + gameData.ANSI_RESET);
        }
        int hold = 0;
        boolean doItAgain = false;
        String holdInput = "";

        do {
            doItAgain = false;
            holdInput = userInput.nextLine();

            try {
                hold = Integer.parseInt(holdInput);
            } catch (NumberFormatException e) {
                System.out.println("Numbers only please! Try again:");
                doItAgain = true;

            }

        } while (doItAgain);

        return hold;
    }

    public double getDouble() {

        if (gameData.debug) {
            System.out.println(gameData.ANSI_RED + "Get Double" + gameData.ANSI_RESET);
        }
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

            }


        } while (doItAgain);

        return hold;

    }
}
