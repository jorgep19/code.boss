package code_boss.model;

public class CompilationResult {
    private static final String DEFAULT_MESSAGE = "All gud :)";

    private boolean isSuccess;
    private boolean isServerError;
    private String message;

    public CompilationResult() {
        this.isSuccess = true;
        this.isServerError = false;
        this.message = DEFAULT_MESSAGE;
    }

    public CompilationResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.isServerError = false;
        this.message = message;
    }

    public void setServerError(boolean isServerError) {
        this.isServerError = isServerError;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isServerError() {
        return isServerError;
    }

    public String getMessage() {
        return message;
    }
}
