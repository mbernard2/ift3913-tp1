package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Provides a Results object whose fields are all initialized to 0.
     * @return A default Results object.
     */
    @Override
    public Results getDefaultResults() {
        return LocResults.zero();
    }

    //INCOMPLET
public int analyse(String code) throws FileNotFoundException {
    	
    	int class_LOC = 0;
    	int paquet_LOC = 0;
   
    	File file = new File(code);
    	Scanner fichier = new Scanner(file);
        String fileName = fichier.toString();
        Pattern javaPattern = Pattern.compile("\\.java");
        
        if(javaPattern.matcher(fileName).find()){
        	while (fichier.hasNextLine()) {
        		String ligne = fichier.nextLine();
        		if (ligne.trim().length()>0)
        			class_LOC++;
        	}
    		return class_LOC;	
        }else {
        	if(file.isDirectory()) {
        		File[] fichiers = new File(code).listFiles();
        		for (File s : fichiers) {
                    String nom = s.getName();
                    if (s.isFile() && nom.endsWith(".java")) {
                        class_LOC += analyse(s.getPath());
                    }else if (s.isDirectory()) {
                    	paquet_LOC += analyse(s.getPath());
                    }
        		}
        	}
        }
		return paquet_LOC;
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
