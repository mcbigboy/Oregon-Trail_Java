package comjava.code;

public class Instructions {

    public static void displayInstructions(){
        // 230 REM ***INSTRUCTIONS***
        // Line 240 - 700 same text no need to show

        StringBuilder message = new StringBuilder();
        message.append("THIS PROGRAM SIMULATES A TRIP OVER THE OREGON TRAIL FROM");
        message.append("INDEPENDENCE, MISSOURI TO OREGON CITY, OREGON IN 1847.");
        message.append("YOUR FAMILY OF FIVE WILL COVER THE 2040 MILE OREGON TRAIL");
        message.append("IN 5-6 MONTHS --- IF YOU MAKE IT ALIVE.");
        message.append("\n\n");
        message.append("YOU HAD SAVED $900 TO SPEND FOR THE TRIP, AND YOU'VE JUST");
        message.append(" PAID $200 FOR A WAGON. ");
        message.append("YOU WILL NEED TO SPEND THE REST OF YOUR MONEY ON THE");
        message.append(" FOLLOWING ITEMS:");
        message.append("\n\n");
        message.append("     OXEN - YOU CAN SPEND $200-$300 ON YOUR TEAM\n");
        message.append("            THE MORE YOU SPEND, THE FASTER YOU'LL GO\n");
        message.append("            BECAUSE YOU'LL HAVE BETTER ANIMALS");
        message.append("\n\n");
        message.append("     FOOD - THE MORE YOU HAVE, THE LESS CHANCE THERE\n");
        message.append("            IS OF GETTING SICK");
        message.append("\n\n");
        message.append("     AMMUNITION - $1 BUYS A BELT OF 50 BULLETS\n");
        message.append("            YOU WILL NEED BULLETS FOR ATTACKS BY ANIMALS\n");
        message.append("            AND BANDITS, AND FOR HUNTING FOOD");
        message.append("\n\n");
        message.append("     CLOTHING - THIS IS ESPECIALLY IMPORTANT FOR THE COLD\n");
        message.append("               WEATHER YOU WILL ENCOUNTER WHEN CROSSING\n");
        message.append("               THE MOUNTAINS");
        message.append("\n\n");
        message.append("     MISCELLANEOUS SUPPLIES - THIS INCLUDES MEDICINE AND\n");
        message.append("               OTHER THINGS YOU WILL NEED FOR SICKNESS\n");
        message.append("               AND EMERGENCY REPAIRS");
        message.append("\n\n");
        message.append("YOU CAN SPEND ALL YOUR MONEY BEFORE YOU START YOUR TRIP -");
        message.append(" OR YOU CAN SAVE SOME OF YOUR CASH TO SPEND AT FORTS ALONG ");
        message.append("THE WAY WHEN YOU RUN LOW. H0W EVER, ITEMS COST MORE AT ");
        message.append("THE FORTS. YOU CAN ALSO GO HUNTING ALONG THE WAY TO GET");
        message.append("MORE FOOD.");
        message.append("\n\n");
        message.append("WHENEVER YOU HAVE TO USE YOUR TRUSTY RIFLE ALONG THE WAY,");
        message.append("YOU WILL BE TOLD TO TYPE IN A WORD (ONE THAT SOUNDS LIKE A ");
        message.append("GUN SHOT). THE FASTER YOU TYPE IN THAT WORD AND HIT THE ");
        message.append("**RETURN** KEY, THE BETTER LUCK YOU'LL HAVE WITH YOUR GUN.");
        message.append("\n\n");
        message.append("AT EACH TURN, ALL ITEMS ARE SHOWN IN DOLLAR AMOUNTS ");
        message.append("EXCEPT BULLETS ");
        message.append("WHEN ASKED TO ENTER MONEY AMOUNTS, DON'T USE A **$**.");
        message.append("\n\n");
        message.append("GOOD LUCK!!!");
        message.append("\n");

        System.out.println(message);

    }

    public static void displayInto(){
        // Lines 710 - 750

        StringBuilder message = new StringBuilder();


        message.append("\n");
        message.append("HOW GOOD A SHOT ARE YOU WITH YOUR RIFLE? ");
        message.append("(1) ACE MARKSMAN,  (2) GOOD SHOT,  (3) FAIR TO MIDDLIN " );
        message.append("(4) NEED MORE PRACTICE,  (5) SHAKY KNEES\n");
        message.append("ENTER ONE OF THE ABOVE -- THE BETTER YOU CLAIM YOU ARE, THE ");
        message.append("FASTER YOU'LL HAVE TO BE WITH YOUR GUN TO BE SUCCESSFUL.");

        System.out.println(message);

    }
}
