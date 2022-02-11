package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.TypeDeclaration;
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
	private String path;

	/**
	 * Sets the node to be analysed. To be called by ClassIterator.
	 * 
	 * @param node Node to be analysed.
	 */
	@Override
	public void setClassNode(TypeDeclaration<?> node) {
		// Unused
	}

	/**
	 * Analyses the source code provided via the `setClassContent` method, and
	 * returns metrics regarding the number of lines in total compared to the number
	 * of lines containing comments.
	 * 
	 * @return Object containing the computed metrics.
	 */
	@Override
	public LocResults analyse() {
		// Unused
		return null;
	}

	/**
	 * Provides a Results object whose fields are all initialized to 0.
	 * 
	 * @return A default Results object.
	 */
	@Override
	public Results getDefaultResults() {
		return LocResults.zero();
	}
	
	/**
	 * 
	 * INCOMPLET
	 * 
	 */
	public Results analyse(String code) throws FileNotFoundException {
		int LOC = class_LOC(code);
		int CLOC = class_CLOC(code);
		int DC = CLOC / LOC;
		
		return new LocResults(LOC, CLOC, DC);
	}

	public static int class_LOC(String code) throws FileNotFoundException {
		int loc = 0;
		Scanner scanner = new Scanner(code);

		while (scanner.hasNextLine()) {
			String ligne = scanner.nextLine();
			if (ligne.trim().length() > 0)
				loc++;
		}
		return loc;
	}

	public static int class_CLOC(String code) throws FileNotFoundException {
		int cloc = 0;
		boolean comment = false;
		Scanner scanner = new Scanner(code);

		while (scanner.hasNextLine()) {
			String ligne = scanner.nextLine();
			if (ligne.trim().length() > 0) {
				if (ligne.contains("/*")) comment = true;
				if (comment || ligne.contains("//")) ++cloc;
				if (ligne.contains("*/")) comment = false;
			}
		}
		return cloc;
	}

	public static float class_DC(int CLOC, int LOC) {
		return (float) CLOC / LOC;
	}

	/**
	 * Method imported and adapted from JavaParser. See
	 * {@link com.github.javaparser.printer.DefaultPrettyPrinterVisitor}, method
	 * {@code printOrphanCommentsBeforeThisChildNode}.
	 * 
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
			if (!(everything.get(i) instanceof Comment))
				positionOfPreviousChild = i;
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
