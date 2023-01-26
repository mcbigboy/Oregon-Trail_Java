package comjava.code;

import java.util.Random;
import java.util.Scanner;

public class GamePlay {

    
    private GameEvents gameEvents = new GameEvents();
    private GameData gameData = GameData.getInstance();
    private int gameControl;                                    // 6780 REM X = CHOICE OF ACTION FOR EACH TURN
    private int randNumChoosingEvents;                          //RANDOM NUMBER IN CHOOSING EVENTS
    private int controlCounter;
    private boolean goToMoutains;
    private int userChoice;
    private boolean doneTurn;
    private boolean loopToShop;
    private int storeRandom;


    public GamePlay() {

        this.gameControl            = -1;
        this.userChoice             = 0;
        this.doneTurn               = true;
        this.controlCounter         = 0;
        this.randNumChoosingEvents  = 0;
        this.goToMoutains           = false;
        this.loopToShop             = false;
        this.storeRandom            = 0;
        startGame();

    }

    public void startGame(){

        Scanner userInput = new Scanner(System.in);

        System.out.println("DO YOU NEED INSTRUCTIONS  (y/n)");
        if(userInput.nextLine().equalsIgnoreCase("y")){
            Instructions.displayInstructions();
        }

        gameEvents.shotLevel();
        gameEvents.initialPurchases();

        gameTurn();
    }
    

    public void gameTurn() {
        Random rnd = new Random();
        boolean done = false;


       
        while (!done) {

            /*
                1740 REM ***BEGINNING EACH TURN***
                code lines 1750 - 1820
                are not need but will add to make it more like the
             */

            /*
                1750 IF F >= 0 THEN 1770
                1760 LET F=0
             */

            if (gameData.food < 0) {
                gameData.food = 0;
            }

            /*
                1770 IF B >= 0 THEN 1790
                1780 LET B=0
             */

            if (gameData.ammunition < 0) {
                gameData.ammunition = 0;
            }

            /*
                1790 IF C >= 0 THEN 1810
                1800 LET C=0
             */

            if (gameData.clothing < 0) {
                gameData.clothing = 0;
            }

            /*
                1810 IF M1 >= 0 THEN 1830
                1820 LET M1=0
             */

            if (gameData.miscSupplies < 0) {
                gameData.miscSupplies = 0;
            }

            if (gameData.food < 13) {
                System.out.println("YOU'D BETTER DO SOME HUNTING OR BUY FOOD AND SOON!!!!");
            }

            /*
                1850 LET F=INT(F)
                1860 LET B=INT(B)
                1870 LET C=INT(C)
                1880 LET M1=INT(M1)
                1890 LET T=INT(T)
                1900 LET M=INT(M)

                those lines of make sure the variables are int, we don't need to do that.

                LET M2=M
                is only use one time in and will not work because the value does not change.
             */

            /*
                1920 IF S4=1 THEN 1950
                1930 IF K8=1 THEN 1950
                1940 GOTO 1990
                1950 LET T=T-20
                1960 IF T<0 THEN 5080
                1970 PRINT "DOCTOR'S BILL IS $20"
                1980 LET K8=0
                1981 LET S4=0
             */

            if (gameData.isIllness|| gameData.isInjury) {
                gameData.money -= 20;
                if (gameData.money < 0) {
                    gameEvents.callDying(2);
                    done = true;
                    return;
                }
                System.out.println();
                System.out.println("DOCTOR'S BILL IS $20");
                System.out.println();
                gameData.isIllness= false;
                gameData.isInjury = false;
            }

            /*
                1990 IF M9=1 THEN 2020
                2000 PRINT "TOTAL MILEAGE IS ";M
                2010 GOTO 2040
                2020 PRINT "TOTAL MILEAGE IS 950"
                2030 LET M9=0
             */

            if (gameEvents.clearSouthPassMileage) {
                System.out.println("TOTAL MILEAGE IS 950");
                gameEvents.clearSouthPassMileage = false;
            } else {
                System.out.println("TOTAL MILEAGE IS " + gameData.totalMilesWholeTrip);
            }

            gameEvents.printSupplies();

            /*
                2060 IF X1=-1 THEN 2170
                2070 LET X1=X1*(-1)
             */
            do {

                loopToShop = false;
                if (gameControl == -1) {
                    // call Hunt or Continue
                    this.userChoice = gameEvents.goShopHuntContinue(1);
                } else {
                    // call Stop Fort or Hunt or Continue

                    this.userChoice = gameEvents.goShopHuntContinue(2);
                }

            /*
                2260 LET X1=X1*(-1)
                2270 ON X GO TO 2290,2540,2720
             */

                gameControl *= -1;

                switch (this.userChoice) {
                    // go hurting
                    case 2:
                        loopToShop = gameEvents.goHunting();
                        gameControl = -1;
                        break;

                    // go shopping
                    case 3:
                        gameEvents.shopFort();
                        break;
                }
            } while (loopToShop);

            /*
                2720 IF F >= 13 THEN 2750
                2730 GOTO 5060
             */

            if (gameData.food < 13) {
                gameEvents.callDying(1);
                done = true;
                return;
            }

            gameEvents.goEat();

            /*
                2860 LET M=M+200+(A-220)/5+10*RND()
             */
            storeRandom = rnd.nextInt(10);
            gameData.totalMilesWholeTrip += 200 + (gameData.oxen - 220) / (5 + storeRandom);

            if(gameData.debug){


                System.out.println(gameData.ANSI_RED + "Random Num: " + storeRandom + " Oxen: " + gameData.oxen);
                gameData.intMath = (int) (200 + (gameData.oxen - 220) / (5 + storeRandom));
                System.out.println("200 + (oxen - 220) / (5 + storeRandom) " + gameData.intMath + gameData.ANSI_RESET);
                System.out.println(gameData.totalMilesWholeTrip);


            }

            int checkRiders = ((gameData.totalMilesWholeTrip / 100 - 4) * 2 + 72) /
                    ((gameData.totalMilesWholeTrip / 100 - 4) * 2 + 12) - 1;

            if(gameData.debug){

                System.out.println(gameData.ANSI_RED + "Then -1 " + (((gameData.totalMilesWholeTrip / 100 - 4) * 2 + 72) /
                        ((gameData.totalMilesWholeTrip / 100 - 4) * 2 + 12) - 1) + gameData.ANSI_RESET);

            }
            storeRandom = rnd.nextInt(10);
            if (gameData.debug){

                System.out.println("!(not) Random Num: " + storeRandom + " > " + " checkRiders: " + checkRiders);

            }
            if (!(storeRandom > checkRiders)) {

                if (gameEvents.callRaiders()) {
                    gameEvents.callDying(1);
                    done = true;
                    return;
                }

            }

            /*
                REM ***SELECTION OF EVENTS***
                3550 LET D1=0
                3560 RESTORE
                3570 LET R1=100*RND()
                3580 LET D1=D1+1
                3590 IF D1=16 THEN 4670
                3595 LET D = 0
                3600 READ D
                3610 IF R1>D THEN 3580
                3620 DATA 6, 11, 13, 15, 17, 22, 32, 35, 37, 42, 44, 54, 64, 69, 95
                3630 IF D1>10 THEN 3650
                3640 ON D1 GO TO 3660, 3700, 3740, 3790, 3820, 3850, 3880, 3960, 4130, 4190
                3650 ON D1-10 GO TO 4220, 4290, 4340, 4560, 4610, 4670
                3660 PRINT "WAGON BREAKS DOWN--LOSE TIME AND SUPPLIES FIXING IT"
                3670 LET M=M-15-5*RND()
                3680 LET M1=M1-8
                3690 GOTO 4710
                3700 PRINT "OX INJURES LEG--SLOWS YOU DOWN REST OF TRIP"
                3710 LET M=M-25
                3720 LET A=A-20
                3730 GOTO 4710
                3740 PRINT "BAD LUCK--YOUR DAUGHTER BROKE HER ARM"
                3750 PRINT "YOU HAD TO STOP AND USE SUPPLIES TO MAKE A SLING"
                3760 LET M=M-5-4*RND()
                3770 LET M1=M1-2-3*RND()
                3780 GOTO 4710
                3790 PRINT "OX WANDERS OFF--SPEND TIME LOOKING FOR IT"
                3800 LET M=M-17
                3810 GOTO 4710
                3820 PRINT "YOUR SON GETS LOST---SPEND HALF THE DAY LOOKING FOR HIM"
                3830 LET M=M-10
                3840 GOTO 4710
                3850 PRINT "UNSAFE WATER--LOSE TIME LOOKING FOR CLEAN SPRING"
                3860 LET M=M-10*RND()-2
                3870 GOTO 4710
                3880 IF M>950 THEN 4490
                3890 PRINT "HEAVY RAINS---TIME AND SUPPLIES LOST"
                3910 LET F=F-10
                3920 LET B=B-500
                3930 LET M1=M1-15
                3940 LET M=M-10*RND()-5
                3950 GOTO 4710
                3960 PRINT "BANDITS ATTACK"
                3970 GOSUB 6140
                3980 LET B=B-20*B1
                3990 IF B >= 0 THEN 4030
                4000 PRINT "YOU RAN OUT OF BULLETS---THEY GET LOTS OF CASH"
                4010 LET T=T/3
                4020 GOTO 4040
                4030 IF B1 <= 1 THEN 4100
                4040 PRINT "YOU GOT SHOT IN THE LEG AND THEY TOOK ONE OF YOUR OXEN"
                4050 LET K8=1
                4060 PRINT "BETTER HAVE A DOC LOOK AT YOUR WOUND"
                4070 LET M1=M1-5
                4080 LET A=A-20
                4090 GOTO 4710
                4100 PRINT "QUICKEST DRAW OUTSIDE OF DODGE CITY!!!"
                4110 PRINT "YOU GOT 'EM!"
                4120 GOTO 4710
                4130 PRINT "THERE WAS A FIRE IN YOUR WAGON--FOOD AND SUPPLIES DAMAGE!"
                4140 LET F=F-40
                4150 LET B=B-400
                4160 LET M1=M1-RND()*8-3
                4170 LET M=M-15
                4180 GOTO 4710
                4190 PRINT "LOSE YOUR WAY IN HEAVY FOG---TIME IS LOST"
                4200 LET M=M-10-5*RND()
                4210 GOTO 4710
                4220 PRINT "YOU KILLED A POISONOUS SNAKE AFTER IT BIT YOU"
                4230 LET B=B-10
                4240 LET M1=M1-5
                4250 IF M1 >= 0 THEN 4280
                4260 PRINT "YOU DIE OF SNAKEBITE SINCE YOU HAVE NO MEDICINE"
                4270 GOTO 5170
                4280 GOTO 4710
                4290 PRINT "WAGON GETS SWAMPED FORDING RIVER--LOSE FOOD AND CLOTHES"
                4300 LET F=F-30
                4310 LET C=C-20
                4320 LET M=M-20-20*RND()
                4330 GOTO 4710
                4340 PRINT "WILD ANIMALS ATTACK!"
                4350 GOSUB 6140
                4360 IF B>39 THEN 4410
                4370 PRINT "YOU WERE TOO LOW ON BULLETS--"
                4380 PRINT "THE WOLVES OVERPOWERED YOU"
                4390 LET K8=1
                4400 GOTO 5120
                4410 IF B1>2 THEN 4440
                4420 PRINT "NICE SHOOTIN' PARDNER---THEY DIDN'T GET MUCH"
                4430 GOTO 4450
                4440 PRINT "SLOW ON THE DRAW---THEY GOT AT YOUR FOOD AND CLOTHES"
                4450 LET B=B-20*B1
                4460 LET C=C-B1*4
                4470 LET F=F-B1*8
                4480 GOTO 4710
                4490 PRINT "COLD WEATHER---BRRRRRRR!---YOU ";
                4500 IF C>22+4*RND() THEN 4530
                4510 PRINT "DON'T ";
                4520 LET C1=1
                4530 PRINT "HAVE ENOUGH CLOTHING TO KEEP YOU WARM"
                4540 IF C1=0 THEN 4710
                4550 GOTO 6300
                4560 PRINT "HAIL STORM---SUPPLIES DAMAGED"
                4570 LET M=M-5-RND()*10
                4580 LET B=B-200
                4590 LET M1=M1-4-RND()*3
                4600 GOTO 4710
                4610 IF E=1 THEN 6300
                4620 IF E=3 THEN 4650
                4630 IF RND()>.25 THEN 6300
                4640 GOTO 4710
                4650 IF RND()<.5 THEN 6300
                4660 GOTO 4710
                4670 PRINT "HELPFUL INDIANS SHOW YOU WERE TO FIND MORE FOOD"
                4680 LET F=F+14
                4690 GOTO 4710
             */

            // TODO update values and get next turn

            this.randNumChoosingEvents = rnd.nextInt(100) + 1;

            if(gameData.debug){

                System.out.println(gameData.ANSI_RED + "Random Num: " + randNumChoosingEvents + gameData.ANSI_RESET);

            }

            if (isBetween(randNumChoosingEvents, 1, 6)) {
                // Data 6 line 3660
                gameEvents.callWagonBreaksDown();
            } else if (isBetween(randNumChoosingEvents, 7, 11)) {
                // Data 11 line 3700
                gameEvents.callOxInjures();
            } else if (isBetween(randNumChoosingEvents, 12, 13)) {
                // Data 13 line 3740
                gameEvents.callBadLuck();
            } else if (isBetween(randNumChoosingEvents, 14, 15)) {
                // Data 15 line 3790
                gameEvents.callOxWandersOff();
            } else if (isBetween(randNumChoosingEvents, 16, 17)) {
                // Data 17 line 3820
                gameEvents.callSonGetsLost();
            } else if (isBetween(randNumChoosingEvents, 18, 22)) {
                // Data 22 line 3850
                gameEvents.callUnsafeWater();
            } else if (isBetween(randNumChoosingEvents, 23, 32)) {
                // Data 32 line 3880
                if (gameData.totalMilesWholeTrip > 950) {
                    gameEvents.callColdWeather();
                } else {
                    gameEvents.callHeavyRains();
                }
            } else if (isBetween(randNumChoosingEvents, 33, 35)) {
                // Data 35 line 3960
                gameEvents.callBanditsAttack();
            } else if (isBetween(randNumChoosingEvents, 36, 37)) {
                // Data 37 line 4130
                gameEvents.callFireInWagon();
            } else if (isBetween(randNumChoosingEvents, 38, 42)) {
                // Data 42 line 4190
                gameEvents.callLostFog();
            } else if (isBetween(randNumChoosingEvents, 43, 44)) {
                // Data 44 line 4220
                gameEvents.callSnakeBit();
            } else if (isBetween(randNumChoosingEvents, 45, 54)) {
                // Data 54 line 4290
                gameEvents.callWagonSwawped();
            } else if (isBetween(randNumChoosingEvents, 55, 64)) {
                // Data 64 line 4340
                gameEvents.callWildAnimals();
            } else if (isBetween(randNumChoosingEvents, 65, 69)) {
                // Data 69 line 4560
                gameEvents.callHailStorm();
            } else if (isBetween(randNumChoosingEvents, 70, 95)) {
                // Data 95 line 4610
                if (gameEvents.getHowToEat() == 1) {
                    this.gameData.isIllness= true;
                } else if (gameEvents.getHowToEat() == 3) {
                    if (rnd.nextFloat() < .5) {
                        this.gameData.isIllness= true;
                    }
                } else if (rnd.nextFloat() > .25) {
                    this.gameData.isIllness= true;
                }

            } else {
                // Data 100 line 4670
                gameEvents.callHelpfulIndins();
            }


            if (gameData.isInjury) {
                gameData.isIllness= false;
                gameEvents.callIsIllness();
            }

            if (gameData.isDying) {
                gameEvents.callDying(this.gameData.howDie);
                done = true;
            }

            if (gameData.totalMilesWholeTrip >= 950) {
                gameEvents.callMountains();
            }

            // 1230 IF M >= 2040 THEN 5430

            if (gameData.totalMilesWholeTrip >= 2040) {
                done = true;
            } else {

                if(gameEvents.callSetDate()){
                    gameEvents.callDying(1);
                    done = true;

                } else {
                    gameData.gameDate++;
                    gameData.milesLastTurn = gameData.totalMilesWholeTrip;
                }
            }

        }

        gameEvents.callFinalTurn();

    }

    private boolean isBetween(int x, int lower, int upper) {
        return lower >= x && x <= upper;
    }
}
