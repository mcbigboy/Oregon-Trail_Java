package comjava.code;

public class GameOption {

    private double doubleValue;
    private int intValue;
    private String description;

    public GameOption(double doubleValue, int intValue, String description) {
        this.doubleValue = doubleValue;
        this.intValue    = intValue;
        this.description = description;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
