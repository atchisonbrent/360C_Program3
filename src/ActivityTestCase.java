/**
 * Created by nathanielwendt on 4/2/17.
 */
public class ActivityTestCase {
    ActivityProblem problem;
    ActivityResult solution;

    public ActivityTestCase(ActivityProblem input, ActivityResult solution){
        this.problem = input;
        this.solution = solution;
    }

    public ActivityProblem getProblem() {
        return problem;
    }

    public void setProblem(ActivityProblem problem) {
        this.problem = problem;
    }

    public ActivityResult getSolution() {
        return solution;
    }

    public void setSolution(ActivityResult solution) {
        this.solution = solution;
    }


    public void check(ActivityResult result){
        boolean funnessFail = false;
        boolean activitiesFail = false;
        System.out.println("Checking test >> " + problem);
        if(!this.solution.maxFunnessEquals(result)){
            funnessFail = true;
            System.out.println("Max funLevels test failed.");
        } else {
            System.out.println("Max funLevels test passed.");
        }

        if(!this.solution.activitiesEquals(result)) {
            activitiesFail = true;
            System.out.println("Activities test failed.");
        } else {
            System.out.println("Activities test passed.");
        }

        if(activitiesFail || funnessFail){
            String expected = "   Expected: " + this.solution + "\n";
            String actual = "   Actual: " + result;
            System.out.println(expected + actual);
        }

        System.out.println();
    }
}
