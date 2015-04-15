package code_boss.model;

public class CodeEvaluation {
    private final long id;
    private final String content;

    public CodeEvaluation(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}