package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityAnalyser;
import ca.umontreal.diro.ift3913.tp1.analysis.LocAnalyser;

import java.util.Map;

public class CodeQualityEvaluator {
    public static void main(String[] args) {
        if (args.length == 0) {
            // TODO print help
        } else if (args.length > 1) {
            System.err.println("Fatal error: wrong number of arguments. Expects exactly one argument.");
        } else {
            ClassIterator iterator = new ClassIterator(args[0]);
            iterator.addAnalyser(new LocAnalyser());
            iterator.addAnalyser(new ComplexityAnalyser());

            Map<String, String> outputFiles = iterator.run();

            // TODO output
        }
    }
}
