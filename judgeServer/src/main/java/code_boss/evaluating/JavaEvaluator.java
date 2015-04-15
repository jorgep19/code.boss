package code_boss.evaluating;

import code_boss.compiling.JavaCompiler;

import java.io.File;

public class JavaEvaluator extends CodeEvaluator {
    private static final String JAVA_EXEC = "java";
    private static final String JAVA_EXEC_ARGS = "Main";

    @Override
    protected String getExecutionCommand() {
        return JAVA_EXEC;
    }

    @Override
    protected String getExecutionArgs() {
        return JAVA_EXEC_ARGS;
    }

    @Override
    protected File getExecutionDirectory(String compiledFilePath) {
        String solutionPath = compiledFilePath.substring(0, compiledFilePath.indexOf(JavaCompiler.MAIN));
        return new File(solutionPath);
    }
}
