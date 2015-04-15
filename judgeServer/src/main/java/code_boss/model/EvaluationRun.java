package code_boss.model;

public class EvaluationRun {
    public int exitCode;
    public boolean timedOut;
    public boolean finished;
    public String output;

    public EvaluationRun() {
        exitCode = 19;
        timedOut = false;
        finished = false;
        output = "";
    }
}