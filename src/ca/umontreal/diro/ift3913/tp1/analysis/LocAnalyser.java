package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.Node;

public class LocAnalyser implements Analyser {
    /**
     * Sets the node to be analysed.
     * @param node Node to be analysed.
     */
    @Override
    public void setClassNode(Node node) {

    }

    /**
     * Analyses the source code provided via the `setClassContent` method, and
     * returns metrics regarding the number of lines in total compared to the
     * number of lines containing comments.
     * @return Object containing the computed metrics.
     */
    @Override
    public LocResults analyse() {
        // TODO
        return null;
    }
}
