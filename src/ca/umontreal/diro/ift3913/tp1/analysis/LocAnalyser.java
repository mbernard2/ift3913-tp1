package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.github.javaparser.utils.PositionUtils.sortByBeginPosition;

public class LocAnalyser implements Analyser {
    /**
     * Sets the node to be analysed. To be called by ClassIterator.
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

    /**
     * Method imported and adapted from JavaParser.
     * See {@link com.github.javaparser.printer.DefaultPrettyPrinterVisitor},
     * method {@code printOrphanCommentsBeforeThisChildNode}.
     * @param node Node for which to get comments that appeared before it.
     */
    private NodeList<Comment> getOrphanCommentsBeforeThisChildNode(final Node node) {
        if (node instanceof Comment)
            return new NodeList<>();

        Node parent = node.getParentNode().orElse(null);
        if (parent == null)
            return new NodeList<>();

        NodeList<Comment> ret = new NodeList<>();

        List<Node> everything = new ArrayList<>(parent.getChildNodes());
        sortByBeginPosition(everything);
        int positionOfTheChild = -1;
        for (int i = 0; i < everything.size(); ++i) { // indexOf is by equality, so this is used to index by identity
            if (everything.get(i) == node) {
                positionOfTheChild = i;
                break;
            }
        }
        if (positionOfTheChild == -1) {
            throw new AssertionError("I am not a child of my parent.");
        }
        int positionOfPreviousChild = -1;
        for (int i = positionOfTheChild - 1; i >= 0 && positionOfPreviousChild == -1; i--) {
            if (!(everything.get(i) instanceof Comment)) positionOfPreviousChild = i;
        }
        for (int i = positionOfPreviousChild + 1; i < positionOfTheChild; i++) {
            Node nodeToPrint = everything.get(i);
            if (!(nodeToPrint instanceof Comment))
                throw new RuntimeException(
                        "Expected comment, instead " + nodeToPrint.getClass() + ". Position of previous child: "
                                + positionOfPreviousChild + ", position of child " + positionOfTheChild);
            else
                ret.add((Comment) nodeToPrint);
        }

        return ret;
    }
}
