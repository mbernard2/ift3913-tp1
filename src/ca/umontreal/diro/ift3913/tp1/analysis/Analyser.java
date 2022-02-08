package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.Node;

public interface Analyser {
    /**
     * Sets the node to be analysed.
     * @param node Node to be analysed.
     */
    void setClassNode(Node node);

    /**
     * Performs the analysis and returns the results as an instance
     * of a Results object.
     * @return Object containing the computed metrics.
     */
    Results analyse();
}
