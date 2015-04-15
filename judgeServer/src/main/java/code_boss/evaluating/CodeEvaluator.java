package code_boss.evaluating;

import code_boss.model.CodeEvaluation;
import code_boss.model.EvaluationRun;
import code_boss.model.UserSolution;

import java.io.*;

public abstract class CodeEvaluator {

    private ICodeEvaluationListener listener;

    public CodeEvaluator(ICodeEvaluationListener listener) {
        this.listener = listener;
    }

    public void runCode(final String compiledFilePath, final UserSolution solution) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder(getExecutionCommand(), getExecutionArgs());
                pb.directory(getExecutionDirectory(compiledFilePath));
                pb.redirectErrorStream(true);

                EvaluationRun run = runWithinTimeout(pb, solution);

                listener.notifyEvaluation(new CodeEvaluation(solution, run));
            }
        }).start();
    }

    protected abstract String getExecutionCommand();

    protected abstract String getExecutionArgs();

    protected abstract File getExecutionDirectory(String compiledFilePath);

    private EvaluationRun runWithinTimeout(ProcessBuilder pb, UserSolution solution) {
        try {
            EvaluationRun run = new EvaluationRun();
            long end = System.currentTimeMillis() + solution.getTimeout();
            Process solutionProcess = pb.start();

            // give input to the solution
            OutputStream os = solutionProcess.getOutputStream();
            PrintStream printStream = new PrintStream(os);
            printStream.print(solution.getTestInput());
            printStream.close();

            // run solition while tracking timeout
            while (!run.finished && !run.timedOut) {
                try {
                    if (System.currentTimeMillis() >= end) {
                        run.timedOut = true;
                    }

                    run.exitCode = solutionProcess.exitValue();
                    run.finished = true;
                } catch (IllegalThreadStateException e) {
                    run.finished = false;
                }
            }

            run.output = getProgramOutput(solutionProcess.getInputStream());

            return run;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getProgramOutput(InputStream programOutput) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(programOutput, "UTF-8"));

            String line;
            do {
                line = bufferedReader.readLine();
                sb.append(line != null ? line : "");
                sb.append("\n");
            } while (line != null);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
