package code_boss;

import code_boss.model.CodeEvaluation;
import code_boss.model.UserSolution;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EvaluationController {

    private static final String CODE = "public class Main {\n" +
            "    public static void main(String[] args) {\n" +
            "        System.out.println(\"Hello World!\");\n" +
            "    }\n" +
            "}\n";

    private static final String template = "Evaluating solution for problem: %s";
    private final AtomicLong counter = new AtomicLong();

///  request must have
//   userId:
//   problemId:
//   testInput:
//   expectedOutput:
//   timeout:

    @RequestMapping(value = "/eval", method = RequestMethod.GET)
    public CodeEvaluation eval(@RequestParam(value="name", defaultValue="World") String name){
        //Logging
        System.out.println(String.format("Received request eval problem with name=%s", name));

        new VMFactory().evaluateCode("jorgep", "19",CODE);

        return new CodeEvaluation(null, null);
    }

//    @RequestMapping(value = "/eval", method = RequestMethod.POST)
//    public CodeEvaluation checkCodeSubmission(@RequestBody UserSolution solution) {
//        //Logging
//        System.out.println(String.format("Received request to evaluate ", solution));
//
//        new VMFactory().evaluateCode("jorgep", "19",CODE);
//
//        return new CodeEvaluation(counter.incrementAndGet(),
//                String.format(template, name));
//    }
}