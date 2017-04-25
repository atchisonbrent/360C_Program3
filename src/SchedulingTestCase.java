/**
 * Created by nathanielwendt on 4/2/17.
 */
public class SchedulingTestCase {
    SchedulingProblem problem;
    SchedulingResult solution;

    public SchedulingTestCase(SchedulingProblem input, SchedulingResult solution){
        this.problem = input;
        this.solution = solution;
    }

    public SchedulingProblem getProblem() {
        return problem;
    }

    public void setProblem(SchedulingProblem problem) {
        this.problem = problem;
    }

    public SchedulingResult getSolution() {
        return solution;
    }

    public void setSolution(SchedulingResult solution) {
        this.solution = solution;
    }


    public void check(SchedulingResult result){
        System.out.println("Checking test >> " + problem);

        if(!this.solution.equals(result)){
            String msg = "Scheduling test failed.\n";
            String expected = "   Expected: " + this.solution + "\n";
            String actual = "   Actual: " + result;
            System.out.println(msg + expected + actual);
        } else {
            System.out.println("Scheduling test passed.");
        }

        System.out.println();
    }
}
