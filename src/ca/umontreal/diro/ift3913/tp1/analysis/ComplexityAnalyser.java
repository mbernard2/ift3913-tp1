package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.Optional;

public class ComplexityAnalyser implements Analyser {
    private TypeDeclaration<?> classNode = null;
    private float complexity = 0;

    /**
     * Sets the node to be analysed. To be called by ClassIterator.
     * @param node Node to be analysed.
     */
    @Override
    public void setClassNode(TypeDeclaration<?> node) {
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
        complexity = 0;

        classNode.getMembers().forEach(decl -> {
            decl.ifMethodDeclaration(method -> {
                Optional<BlockStmt> body = method.getBody();
                body.ifPresent(blockStmt -> {
                    complexity++;
                    computeBlockComplexity(blockStmt);
                });
            });
        });

        return new ComplexityResults(complexity);
    }

    /**
     * Provides a Results object whose fields are all initialized to 0.
     * @return A default Results object.
     */
    @Override
    public Results getDefaultResults() {
        return ComplexityResults.zero();
    }

    private void computeBlockComplexity(BlockStmt block) {
        block.getStatements().forEach(this::computeStatementComplexity);
    }

    private void computeStatementComplexity(Statement statement) {
        statement.ifBlockStmt(this::computeBlockComplexity);

        statement.ifIfStmt(ifStmt -> {
            complexity++;
            ifStmt.getElseStmt().ifPresent(this::computeStatementComplexity);
        });

        statement.ifSwitchStmt(switchStmt -> {
            // Complexity = number of cases (`default` doesn't count since
            // it acts like an `else` statement which doesn't create a new
            // linearly independent path).
            switchStmt.getEntries().forEach(switchEntry -> {
                if (switchEntry.getLabels().isNonEmpty())
                    // entry with no labels = `default` entry
                    complexity++;
                switchEntry.getStatements().forEach(this::computeStatementComplexity);
            });
        });

        statement.ifWhileStmt(whileStmt -> {
            complexity++;
            computeStatementComplexity(whileStmt.getBody());
        });

        statement.ifDoStmt(doStmt -> {
            complexity++;
            computeStatementComplexity(doStmt.getBody());
        });

        statement.ifForStmt(forStmt -> {
            complexity++;
            computeStatementComplexity(forStmt.getBody());
        });

        statement.ifForEachStmt(forEachStmt -> {
            complexity++;
            computeStatementComplexity(forEachStmt.getBody());
        });
    }
}
