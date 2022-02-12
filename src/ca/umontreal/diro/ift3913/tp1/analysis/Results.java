package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public interface Results {
    /**
     * Adds another Result's values to this.
     * @param results Other objet whose values to combine.
     */
    void combine(Results results);

    /**
     * Implements the Visitor's pattern for converting the computed
     * values to the proper output format.
     * @param visitor Object whose `visit` method to call.
     */
    void accept(OutputVisitor visitor);
}
