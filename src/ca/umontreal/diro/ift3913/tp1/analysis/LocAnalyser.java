package ca.umontreal.diro.ift3913.tp1.analysis;

import com.github.javaparser.ast.body.TypeDeclaration;

import java.util.Scanner;

import static com.github.javaparser.utils.PositionUtils.sortByBeginPosition;

public class LocAnalyser implements Analyser {
	private TypeDeclaration<?> node;

	/**
	 * Sets the node to be analysed. To be called by ClassIterator.
	 * 
	 * @param node Node to be analysed.
	 */
	@Override
	public void setClassNode(TypeDeclaration<?> node) {
		this.node = node;
	}

	/**
	 * Analyses the source code provided via the `setClassContent` method, and
	 * returns metrics regarding the number of lines in total compared to the number
	 * of lines containing comments.
	 * 
	 * @return Object containing the computed metrics.
	 */
	@Override
	public Results analyse() {
		return analyse(node.toString());
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
	private Results analyse(String code) {
		int loc = computeClassLoc(code);
		int cloc = computeClassCloc(code);
		float dc = (float) cloc / loc;
		
		return new LocResults(loc, cloc, dc);
	}

	private static int computeClassLoc(String code) {
		int loc = 0;
		Scanner scanner = new Scanner(code);

		while (scanner.hasNextLine()) {
			String ligne = scanner.nextLine();
			if (ligne.trim().length() > 0)
				loc++;
		}
		return loc;
	}

	private static int computeClassCloc(String code) {
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
}
