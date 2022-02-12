package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityAnalyser;
import ca.umontreal.diro.ift3913.tp1.analysis.LocAnalyser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CodeQualityEvaluator {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar CodeQualityEvaluator.jar PATH");
        } else if (args.length > 1) {
            System.err.println("Fatal error: wrong number of arguments. Expects exactly one argument.");
        } else {
            ClassIterator iterator = new ClassIterator(args[0]);
            iterator.addAnalyser(new LocAnalyser());
            iterator.addAnalyser(new ComplexityAnalyser());

            Map<String, String> outputFiles = iterator.run();

            outputFiles.forEach((fileName, content) -> {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    writer.write(content);
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error while attempting to write to file \"" + fileName + "\" :");
                    System.err.println(e.getMessage());
                }
            });
        }
    }
}
