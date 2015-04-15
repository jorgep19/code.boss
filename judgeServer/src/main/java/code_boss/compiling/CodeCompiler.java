package code_boss.compiling;

import code_boss.model.CompilationResult;
import code_boss.model.UserSolution;

import java.io.*;

public abstract class CodeCompiler {
    private static final String CODE_DIR = "code";

    private ICompilationResultListener listener;

    public CodeCompiler(ICompilationResultListener listener) {
        this.listener = listener;
    }

    public void CompileSolution(UserSolution solution) {
        String solutionFilePath = createSolutionFile(solution);

        // respond with server error if solution file couldn't be open
        if(solutionFilePath == null) {
            CompilationResult result = new CompilationResult(false, "Unable to create solution file");
            result.setServerError(true);
            listener.notifyResult(result);
            return;
        }

        // if file create then compile it
        compileSolution(solutionFilePath);
    }

    protected abstract String getDefaultFileName();

    protected abstract String getFileExtension();

    protected abstract String getExecutionCommand();

    private String createSolutionFile(UserSolution solution) {
        StringBuilder sb = new StringBuilder();
        createDirIfNeed(CODE_DIR);
        sb.append(CODE_DIR);
        sb.append("/");
        createDirIfNeed(String.format("%s%s", sb.toString(), solution.getUserId()));
        sb.append(solution.getUserId());
        sb.append("/");
        createDirIfNeed(String.format("%s%s", sb.toString(), solution.getProblemId()));
        sb.append(solution.getProblemId());
        sb.append("/");
        sb.append(getDefaultFileName());
        sb.append(getFileExtension());

        return createFile(solution, sb.toString());
    }

    public void createDirIfNeed(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if(!file.mkdir()) {
                System.out.println("Unable to create directory " + path);
            }
        }
    }

    private String createFile(UserSolution solution, String solutionPath) {
        try {
            File file = new File(solutionPath);
            if(!file.exists()) {
                if(file.createNewFile()) {
                    System.out.println("Created file");
                } else {
                    System.out.println("Couldn't create file");
                }
            }

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(solution.getCode());
            output.close();

            return file.getPath();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return null;
    }

    private void compileSolution(final String filePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder(getExecutionCommand(), filePath);
                pb.redirectErrorStream(true);
                try {
                    Process compilingProcess = pb.start();
                    compilingProcess.waitFor();

                    CompilationResult result;

                    if(compilingProcess.exitValue() == 0) {
                        result = new CompilationResult();
                    } else {
                        result = new CompilationResult(false, getOutput(compilingProcess.getInputStream()));
                    }

                    listener.notifyResult(result);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getOutput(InputStream programOutput) {
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
