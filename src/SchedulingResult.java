import java.util.Arrays;

/**
 * Created by nathanielwendt on 4/2/17.
 */
public class SchedulingResult {

    //True for Maui
    //False for Oahu
    boolean[] schedule;

    public SchedulingResult(){}

    public SchedulingResult(boolean[] schedule){
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "SchedulingResult{" +
                "schedule=" + Arrays.toString(schedule) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulingResult that = (SchedulingResult) o;

        if (!Arrays.equals(schedule, that.schedule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return schedule != null ? Arrays.hashCode(schedule) : 0;
    }

    public boolean[] getSchedule() {
        return schedule;
    }

    public void setSchedule(boolean[] schedule) {
        this.schedule = schedule;
    }
}
