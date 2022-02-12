package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class LocResults implements Results {
	private int loc;
	private int cloc;
	private float dc;
	
    /**
     * Produces a Results object with acts as a neutral element under addition.
     * @return A Result for which all fields have value 0.
     */
    public static Results zero() {
        return new LocResults(0, 0, 0);
    }

    /**
     * Constructs a new Results containing LOC metrics.
     * @param
     * @param
     * @param
     */
    LocResults(int loc, int cloc, float dc) {
        this.loc = loc;
        this.cloc = cloc;
        this.dc = dc;
    }

    /**
     * Adds another Result's values to this. Updates the DC value
     * accordingly (LOC ÷ CLOC).
     * @param results Other objet whose values to combine.
     */
    @Override
    public void combine(Results results) {
        if (results instanceof LocResults) {
            LocResults locResults = (LocResults) results;
            loc += locResults.loc;
            cloc += locResults.cloc;
            dc = (float) cloc / loc;
        }
    }

    public int getLoc() {
        return loc;
    }

    public int getCloc() {
        return cloc;
    }

    public float getDc() {
        return dc;
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
