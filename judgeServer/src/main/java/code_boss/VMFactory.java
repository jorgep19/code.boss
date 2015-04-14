package code_boss;

import java.io.*;

public class VMFactory {

    public void evaluateCode(String code) {
        createFile(code);
    }

    private void createFile(String code) {
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
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
