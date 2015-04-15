package code_boss.evaluating;

import code_boss.model.CodeEvaluation;
import code_boss.model.UserSolution;

public interface ICodeEvaluationListener {
    void notifyEvaluation(CodeEvaluation evaluation);
}
