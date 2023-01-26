package comjava.code;

public class GameData {

    private static GameData gameData = null;

    protected boolean debug;
    protected int totalMilesWholeTrip;    // 6660 REM M = TOTAL MILEAGE WHOLE TRIP
    protected boolean isIllness;          // S4 = FLAG FOR ILLNESS
    protected boolean isInjury;           // K8 = FLAG FOR INJURY
    protected boolean isBlizzard;
    protected boolean isDying;
    protected int howDie;
    protected int gameDate;
    protected int milesLastTurn;
    protected int shootingExpertiseLevel; // 6580 REM D9 = CHOICE OF SHOOTING EXPERTISE LEVEL
    protected int intMath;
    protected double oxen;
    protected double ammunition;         // 6490 REM B = AMOUNT SPENT ON AMMUNITION
    protected double clothing;           // 6520 REM C = AMOUNT SPENT ON CLOTHING
    protected double food;               // 6600 REM F = AMOUNT SPENT ON FOOD
    protected double miscSupplies;       // 6670 REM M1 = AMOUNT SPENT ON MISCELLANEOUS SUPPLIES
    protected double money;

    protected final String ANSI_RESET   = "\u001B[0m";
    protected final String ANSI_BLACK   = "\u001B[30m";
    protected final String ANSI_GREEN   = "\u001B[32m";
    protected final String ANSI_RED     = "\u001B[31m";
    protected final String ANSI_YELLOW  = "\u001B[33m";
    protected final String ANSI_BLUE    = "\u001B[34m";
    protected final String ANSI_PURPLE  = "\u001B[35m";
    protected final String ANSI_CYAN    = "\u001B[36m";
    protected final String ANSI_WHITE   = "\u001B[37m";

    public GameData(){
        this.totalMilesWholeTrip    = 0;
        this.isIllness              = false;
        this.isInjury               = false;
        this.isBlizzard             = false;
        this.isDying                = false;
        this.howDie                 = 0;
        this.gameDate               = 0;
        this.milesLastTurn          = 0;
        this.shootingExpertiseLevel = 0;
        this.debug                  = false;
        this.intMath                = 0;
        this.oxen                   = 0.0;
        this.ammunition             = 0.0;
        this.clothing               = 0.0;
        this.food                   = 0.0;
        this.miscSupplies           = 0.0;
        this.money                  = 700.0;

    }

    public static synchronized GameData getInstance(){
        if(gameData == null){
            gameData = new GameData();
        }
        return gameData;
    }
}
