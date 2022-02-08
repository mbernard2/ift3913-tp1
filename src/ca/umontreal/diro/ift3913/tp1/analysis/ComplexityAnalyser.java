package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

public class ComplexityAnalyser implements Analyser {
    private JavaParser parser = new JavaParser();
    private Node classNode = null;

    /**
     * Sets the node to be analysed. To be called by ClassIterator.
     * @param node Node to be analysed.
     */
    @Override
    public void setClassNode(Node node) {
        classNode = node;
    }

    /**
     * Computes the McCabe cyclomatic complexity of the class provided
     * via the `setClassContent` method, and returns the result as a
     * ComplexityResults object.
     * @return Object containing the computed metrics.
     */
    @Override
    public ComplexityResults analyse() {

        return null;
    }
}
