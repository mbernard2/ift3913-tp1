package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class LocResults implements Results {

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
     * Implements the Visitor's pattern for converting the computed
     * values to the proper output format.
     * @param visitor Object whose `visit` method to call.
     */
    @Override
    public void accept(OutputVisitor visitor) {
        visitor.visit(this);
    }
}
