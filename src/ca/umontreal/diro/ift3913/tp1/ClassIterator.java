package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.Analyser;
import ca.umontreal.diro.ift3913.tp1.output.CsvOutputVisitor;
import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassIterator {
    private final Path path;
    private final List<Analyser> analysers = new ArrayList<>();

    public ClassIterator(String path) {
        this.path = Paths.get(path);
    }

    public void addAnalyser(Analyser analyser) {
        analysers.add(analyser);
    }

    public Map<String, String> run() {
        File location = path.toFile();
        Map<String, String> outputFiles = new HashMap<>();
        OutputVisitor classVisitor = new CsvOutputVisitor(
                "chemin",
                "class",
                "classe_LOC",
                "classe_CLOC",
                "classe_DC",
                "WMC",
                "classe_BC");
        OutputVisitor packageVisitor = new CsvOutputVisitor(
                "chemin",
                "paquet",
                "paquet_LOC",
                "paquet_CLOC",
                "paquet_DC",
                "WCP",
                "paquet_BC");

        if (location.isDirectory()) {

        } else if (location.isFile()) {

        } else {
            System.err.println("Fatal error: no such file or directory");
        }

        return outputFiles;
    }
}
