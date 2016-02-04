package im.jss.carAPI;

public class AverageDTO {
    private long objectId;
    private double average;
    private String name;

    public AverageDTO(long id, String name, double average) {
        this.objectId = id;
        this.name = name;
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public long getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }
}
