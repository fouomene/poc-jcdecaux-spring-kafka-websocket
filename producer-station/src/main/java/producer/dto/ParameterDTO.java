package producer.dto;

public class ParameterDTO {

    private int sleepTime;
    private int numberOfCall;

    public ParameterDTO(int sleepTime, int numberOfCall) {
        this.sleepTime = sleepTime;
        this.numberOfCall = numberOfCall;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getNumberOfCall() {
        return numberOfCall;
    }

    public void setNumberOfCall(int numberOfCall) {
        this.numberOfCall = numberOfCall;
    }

    @Override
    public String toString() {
        return "ParameterDTO{" +
                "sleepTime=" + sleepTime +
                ", numberOfCall=" + numberOfCall +
                '}';
    }
}