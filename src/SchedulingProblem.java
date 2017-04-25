import java.util.Arrays;

/**
 * Created by nathanielwendt on 4/2/17.
 */
public class SchedulingProblem {
    int[] mauiCosts;
    int[] oahuCosts;
    int transferCost;

    public SchedulingProblem(){}

    public SchedulingProblem(int[] mauiCosts, int[] oahuCosts, int transferCost) {
        this.mauiCosts = mauiCosts;
        this.oahuCosts = oahuCosts;
        this.transferCost = transferCost;
    }

    public int[] getMauiCosts() {
        return mauiCosts;
    }

    public void setMauiCosts(int[] mauiCosts) {
        this.mauiCosts = mauiCosts;
    }

    public int[] getOahuCosts() {
        return oahuCosts;
    }

    public void setOahuCosts(int[] oahuCosts) {
        this.oahuCosts = oahuCosts;
    }

    public int getTransferCost() {
        return transferCost;
    }

    public void setTransferCost(int transferCost) {
        this.transferCost = transferCost;
    }

    @Override
    public String toString() {
        return "SchedulingProblem{" +
                "mauiCosts=" + Arrays.toString(mauiCosts) +
                ", oahuCosts=" + Arrays.toString(oahuCosts) +
                ", transferCost=" + transferCost +
                '}';
    }
}
