package code_boss.compiling;

public class JavaCompiler extends CodeCompiler {
    public static final String MAIN = "Main";
    private static final String JAVA_EXT = ".java";
    private static final String JAVA_COMPILE = "javac";


    public JavaCompiler(CompilationResultListener listener) {
        super(listener);
    }

    @Override
    protected String getDefaultFileName() {
        return MAIN;
    }

    @Override
    protected String getFileExtension() {
        return JAVA_EXT;
    }

    @Override
    protected String getExecutionCommand() {
        return JAVA_COMPILE;
    }
}
