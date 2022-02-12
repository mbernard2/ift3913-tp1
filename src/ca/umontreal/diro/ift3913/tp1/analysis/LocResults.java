package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class LocResults implements Results {
	private int LOC;
	private int CLOC;
	private float DC;
	
    /**
     * Produces a Results object with acts as a neutral element under addition.
     * @return A Result for which all fields have value 0.
     */
    public static Results zero() {
        return new LocResults(0, 0, 0);
    }

    /**
     * Constructs a new Results containing LOC metrics.
     * @param Number of lines of code.
     * @param Number of lines of code that contain comments.
     * @param Density of comments.
     */
    LocResults(int LOC, int CLOC, float DC) {
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
    }

    /**
     * Adds another Result's values to this.
     * @param results Other objet whose values to use.
     */
    @Override
    public void add(Results results) {
        if (results instanceof LocResults) {
            LocResults locResults = (LocResults) results;
            LOC += locResults.LOC;
            CLOC += locResults.CLOC;
            DC += locResults.DC;
        }
    }

    public int getLoc() {
        return LOC;
    }

    public int getCloc() {
        return CLOC;
    }

    public float getDc() {
        return DC;
    }
    
    /**
     * Implements the Visitor's pattern for converting the computed
     * values to the proper output format.
     * @param visitor Object whose `visit` method to call.
     */
    @Override
    public void accept(OutputVisitor visitor) {
        visitor.visit(this);
    }
}
