package code_boss;

import java.io.*;

public class VMFactory {

    public void evaluateCode(String code) {
        compileTheCode(createFile(code));
    }

    private String createFile(String code) {
        try {
            File file = new File("code/Main.java");
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
        final String pathOfClassFile = filePath.substring(0, filePath.indexOf(".java"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder("java", pathOfClassFile);
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
