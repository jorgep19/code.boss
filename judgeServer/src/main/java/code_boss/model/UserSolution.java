package code_boss.model;

public class UserSolution {
    private String userId;
    private String problemId;
    private String code;
    private String testinput;
    private String expectedOutput;
    private int timeout;

    public String getUserId() {
        return userId;
    }

    public String getProblemId() {
        return problemId;
    }

    public String getCode() {
        return code;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getTestInput() {
        return testinput;
    }
}
