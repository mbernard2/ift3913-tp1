package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class LocResults implements Results {
	private int LOC;
	private int CLOC;
	private int DC;
	
    /**
     * Produces a Results object with acts as a neutral element under addition.
     * @return A Result for which all fields have value 0.
     */
    public static Results zero() {
        return null; // TODO
    }

    /**
     * Adds another Result's values to this.
     * @param results Other objet whose values to use.
     */
    @Override
    public void add(Results results) {

    }

    /**
     * Constructs a new Results containing LOC metrics.
     * @param 
     * @param 
     * @param 
     */
    LocResults(int LOC, int CLOC, int DC) {
        this.LOC = LOC;
        this.CLOC = CLOC;
        this.DC = DC;
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
