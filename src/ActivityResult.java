import java.util.Set;

/**
 * Created by nathanielwendt on 4/2/17.
 */
public class ActivityResult {
    int maxFunLevel;
    Set<String> selectedActivities;

    public ActivityResult(){}

    public ActivityResult(int maxFunLevel, Set<String> selectedActivities){
        this.maxFunLevel = maxFunLevel;
        this.selectedActivities = selectedActivities;
    }

    public int getMaxFunLevel() {
        return maxFunLevel;
    }

    public void setMaxFunLevel(int maxFunLevel) {
        this.maxFunLevel = maxFunLevel;
    }

    public Set<String> getSelectedActivities() {
        return selectedActivities;
    }

    public void setSelectedActivities(Set<String> selectedActivities) {
        this.selectedActivities = selectedActivities;
    }

    public boolean maxFunnessEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityResult that = (ActivityResult) o;

        if (maxFunLevel != that.maxFunLevel) return false;
        return true;
    }

    public boolean activitiesEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityResult that = (ActivityResult) o;

        if (selectedActivities != null ? !selectedActivities.equals(that.selectedActivities) : that.selectedActivities != null) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ActivityResult{" +
                "maxFunLevel=" + maxFunLevel +
                ", selectedActivities=" + selectedActivities +
                '}';
    }
}

