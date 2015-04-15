package code_boss.compiling;

import code_boss.model.CompilationResult;

public interface CompilationResultListener {
    void notifyResult(CompilationResult result);
}
