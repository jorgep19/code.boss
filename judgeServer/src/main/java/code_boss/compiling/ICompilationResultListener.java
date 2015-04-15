package code_boss.compiling;

import code_boss.model.CompilationResult;

public interface ICompilationResultListener {
    void notifyResult(CompilationResult result);
}
