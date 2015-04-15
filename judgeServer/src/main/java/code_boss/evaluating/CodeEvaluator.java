package code_boss.evaluating;

import java.io.*;

public abstract class CodeEvaluator {
    private void runCode(final String compiledFilePath, final String expectedOutput) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder(getExecutionCommand(), getExecutionArgs());
                pb.directory(getExecutionDirectory(compiledFilePath));
                pb.redirectErrorStream(true);

                try {
                    // TODO time stamp
                    Process solutionProcess = pb.start();
                    solutionProcess.waitFor();
                    // TODO time stamp

                    if(expectedOutput.equals(getProgramOutput(solutionProcess.getInputStream()))) {
                        // TODO notify good
                    } else {
                        // TODO notify bad
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected abstract String getExecutionCommand();

    protected abstract String getExecutionArgs();

    protected abstract File getExecutionDirectory(String compiledFilePath);

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
