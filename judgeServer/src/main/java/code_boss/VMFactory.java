package code_boss;

import java.io.*;

public class VMFactory {

    private static final String CODE_DIR = "code";
    private static final String MAIN = "Main";
    private static final String JAVA_EXT = ".java";


    public void evaluateCode(String user, String problemId, String code) {
        StringBuilder sb = new StringBuilder();
        createDirIfNeed(CODE_DIR);
        sb.append(CODE_DIR);
        sb.append("/");
        createDirIfNeed(String.format("%s%s", sb.toString(), user));
        sb.append(user);
        sb.append("/");
        createDirIfNeed(String.format("%s%s", sb.toString(), problemId));
        sb.append(problemId);
        sb.append("/");
        sb.append(MAIN);
        sb.append(JAVA_EXT);

        compileTheCode(createFile(sb.toString(), code));
    }

    private String createFile(String solutionPath, String code) {
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
            output.write(code);
            output.close();

            return file.getPath();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return null;
    }

    public void createDirIfNeed(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if(!file.mkdir()) {
                System.out.println("Unable to create directory " + path);
            }
        }
    }

    private void compileTheCode(final String filePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder("javac", filePath);
                try {
                    Process compilingProcess = pb.start();
                    OutputStream compilationOutput = compilingProcess.getOutputStream();
                    compilingProcess.waitFor();

                    if(compilingProcess.exitValue() == 0) {
                        runCode(filePath);
                    } else {
                        // get Error and send it back
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void runCode(String filePath) {
        final String solutionPath = filePath.substring(0, filePath.indexOf(MAIN));
        final File solutionDir = new File(solutionPath);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder("java", MAIN);
                pb.directory(solutionDir);
                pb.redirectErrorStream(true);

                try {
                    Process compilingProcess = pb.start();
                    compilingProcess.waitFor();
                    System.out.println(getProgramOut(compilingProcess.getInputStream()));
                } catch (IOException e) {

                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String getProgramOut(InputStream programOutput) {
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
