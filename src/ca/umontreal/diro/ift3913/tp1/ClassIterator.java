package ca.umontreal.diro.ift3913.tp1;

import ca.umontreal.diro.ift3913.tp1.analysis.Analyser;
import ca.umontreal.diro.ift3913.tp1.analysis.Results;
import ca.umontreal.diro.ift3913.tp1.output.CsvOutputVisitor;
import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.utils.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassIterator {
    private static class ClassData {
        private final String packageName;
        private final String className;
        private final String path;

        private ClassData(String path, String packageName, String className) {
            this.path = path;
            this.packageName = packageName;
            this.className = className;
        }
    }

    private static final String CSV_CLASS_FILENAME = "classes.csv";
    private static final String CSV_PACKAGE_FILENAME = "paquets.csv";

    private final Path path;
    private final Map<Analyser, Map<ClassData, Results>> analysersAndResults = new HashMap<>();
    private final JavaParser parser = new JavaParser();

    public ClassIterator(String path) {
        this.path = Paths.get(path);
    }

    public void addAnalyser(Analyser analyser) {
        analysersAndResults.put(analyser, new HashMap<>());
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
            analyseLocation(location);

            analysersAndResults.forEach((analyser, allResults) -> {
                // For each package, store folder path and sum of results
                Map<String, Pair<String, Results>> packageResults = new HashMap<>();

                allResults.forEach((classData, results) -> {
                    String packageName = classData.packageName;
                    if (packageName != null) {
                        if (!packageResults.containsKey(packageName)) {
                            // Note: using class folder as package path. (All classes within
                            // the same package are supposed to be in the same folder)
                            String folderPath = Paths.get(classData.path).getParent().toString();
                            packageResults.put(packageName, new Pair<>(folderPath, analyser.getDefaultResults()));
                        }
                        packageResults.get(packageName).b.add(results);
                    }

                    classVisitor.setCurrentPath(classData.path);
                    classVisitor.setCurrentItemName(classData.className);
                    results.accept(classVisitor);
                });

                packageResults.forEach((packageName, pathAndResults) -> {
                    packageVisitor.setCurrentPath(pathAndResults.a);
                    packageVisitor.setCurrentItemName(packageName);
                    pathAndResults.b.accept(packageVisitor);
                });
            });

            outputFiles.put(CSV_CLASS_FILENAME, classVisitor.getCsvString());
            outputFiles.put(CSV_PACKAGE_FILENAME, packageVisitor.getCsvString());
        } catch (IOException e) {
            System.err.println("Fatal error: exception while reading file.");
            System.err.println(e.getMessage());
        }

        return outputFiles;
    }

    private void analyseLocation(File location) throws IOException {
        if (location.isDirectory()) {
            for (File file : location.listFiles()) {
                analyseLocation(file);
            }
        } else if (location.isFile()) {
            analyseFile(location);
        } else {
            System.err.println("Error: no such file or directory: \"" + location.getPath() + "\"");
        }
    }

    private void analyseFile(File file) throws IOException {
        ParseResult<CompilationUnit> parseResult = parser.parse(file);
        CompilationUnit compilationUnit = parseResult.getResult().orElse(null);

        if (compilationUnit != null) {
            PackageDeclaration packageDecl = compilationUnit.getPackageDeclaration().orElse(null);
            String packageName = packageDecl != null ? packageDecl.getNameAsString() : null;
            for (TypeDeclaration<?> decl : compilationUnit.getTypes()) {
                if (decl instanceof ClassOrInterfaceDeclaration
                        || decl instanceof EnumDeclaration) {
                    analysersAndResults.forEach((analyser, analyserResults) -> {
                        analyser.setClassNode(decl);
                        Results res = analyser.analyse();
                        String name = decl.getFullyQualifiedName().orElse(decl.getName().asString());
                        analyserResults.put(new ClassData(file.getPath(), name, packageName), res);
                    });
                }
            }
        } else {
            System.err.println("Warning: could not parse file \"" + file.getPath() + "\"");
        }
    }
}
