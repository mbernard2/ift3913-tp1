package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.Analyser;
import ca.umontreal.diro.ift3913.tp1.analysis.Results;
import ca.umontreal.diro.ift3913.tp1.output.CsvOutputVisitor;
import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassIterator {
    private final Path path;
    private final List<Analyser> analysers = new ArrayList<>();
    private final JavaParser parser = new JavaParser();

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

        try {
            if (location.isDirectory()) {

            } else if (location.isFile()) {
                Map<String, Results> fileResults = parseFile(location);
                classVisitor.setCurrentPath(location.getAbsolutePath());
                fileResults.forEach((className, results) -> {
                    classVisitor.setCurrentItemName(className);
                    results.accept(classVisitor);
                });
                writeToFiles(classVisitor, packageVisitor);
            } else {
                System.err.println("Fatal error: no such file or directory");
            }
        } catch (IOException e) {
            System.err.println("Fatal error: exception while reading file.");
            System.err.println(e.getMessage());
        }

        return outputFiles;
    }

    private Map<String, Results> parseFile(File file) throws IOException {
        Map<String, Results> fileResults = new HashMap<>();
        ParseResult<CompilationUnit> parseResult = parser.parse(file);
        CompilationUnit compilationUnit = parseResult.getResult().get();

        for (TypeDeclaration<?> decl : compilationUnit.getTypes()) {
            if (decl instanceof ClassOrInterfaceDeclaration
                    || decl instanceof EnumDeclaration) {
                for (Analyser analyser : analysers) {
                    analyser.setClassNode(decl);
                    Results res = analyser.analyse();
                    String name = decl.getFullyQualifiedName().orElse(decl.getName().asString());
                    fileResults.put(name, res);
                }
            }
        }

        return fileResults;
    }

    private void writeToFiles(OutputVisitor classVisitor, OutputVisitor packageVisitor) {
        // TODO
    }
}
