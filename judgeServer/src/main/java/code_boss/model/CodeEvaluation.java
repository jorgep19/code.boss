package code_boss.model;

public class CodeEvaluation {
    private static final String SERVER_ERROR_ERROR_MSG = "";
    private static final String TIMEOUT_MSG = "";
    private static final String SUCCESS_MSG = "";

    private String message;
    private int evaluationCode;
    private boolean wasSuccessfull;

    public CodeEvaluation(UserSolution solution, EvaluationRun run) {
        wasSuccessfull = false;

        // there was a server error trying to run
        if (run == null) {
            evaluationCode = 4;
            message = SERVER_ERROR_ERROR_MSG;
        // solution got got timeout
        } else if(run.timedOut) {
            evaluationCode = 3;
            message = TIMEOUT_MSG;
        // solution had a runtime error
        } else if(run.exitCode != 0) {
            evaluationCode = 2;
            // show runtime error if the exit code wasn't 0
            message = run.output;
        // solution didn't produce the expected output
        } else if(!solution.getExpectedOutput().equals(run.output)) {
            evaluationCode = 1;
            message = SERVER_ERROR_ERROR_MSG;
        // GOOD SOLUTION!!!! we got a winner!
        } else {
            evaluationCode = 0;
            message = SUCCESS_MSG;
            wasSuccessfull = true;
        }
    }
}