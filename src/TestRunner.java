import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by nathanielwendt on 4/2/17.
 */
public class TestRunner {
    public static void main(String[] args){
        Program3 prog3 = new Program3();

        System.out.println("----- Running Activity Tests ------");

        List<ActivityTestCase> activityTests = new ArrayList<ActivityTestCase>();

        // Sample activity test case using the example in the assignment handout

        // Initialize the problem
        ActivityProblem actProblem0 = new ActivityProblem(
                new String[] {"Skydiving", "Dance Lessons", "Snorkeling"},
                new int[] {60,40,30},
                new int[] {4,3,3},
                6);

        // Initialize the solution
        ActivityResult actSolution0 = new ActivityResult(
                70,
                new HashSet<String>(Arrays.asList(new String[] {"Dance Lessons", "Snorkeling"})));

        // Create and add test case based on specified problem and solution
        activityTests.add(new ActivityTestCase(actProblem0, actSolution0));

        // Compare your Program3 activity selector against the solutions specified above
        for(ActivityTestCase activityTest : activityTests){
            ActivityResult res = prog3.selectActivity(activityTest.getProblem());
            activityTest.check(res);
        }

        System.out.println("----- Running Scheduling Tests ------");

        List<SchedulingTestCase> schedulingTests = new ArrayList<SchedulingTestCase>();

        // Sample scheduling test case using the example in the assignment handout

        // Initialize the problem
        SchedulingProblem schProblem0 = new SchedulingProblem(
                new int[] {10,100,10},
                new int[] {400,20,400},
                50);

        // Initialize the solution
        SchedulingResult schSolution0 = new SchedulingResult(
                new boolean[] {true,true,true});

        // Create and add test case based on specified problem and solution
        schedulingTests.add(new SchedulingTestCase(schProblem0, schSolution0));

        // Compare your Program3 schedule selector against the solutions specified above
        for(SchedulingTestCase schedulingTest: schedulingTests){
            SchedulingResult res = prog3.selectScheduling(schedulingTest.getProblem());
            schedulingTest.check(res);
        }
    }
}
