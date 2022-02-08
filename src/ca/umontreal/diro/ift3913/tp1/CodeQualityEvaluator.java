package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityAnalyser;

public class CodeQualityEvaluator {

    public static void main(String[] args) {
        ComplexityAnalyser analyser = new ComplexityAnalyser();
        analyser.analyse();
    }
}
