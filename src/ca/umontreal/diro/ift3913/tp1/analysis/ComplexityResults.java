package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class ComplexityResults implements Results {
    /** For a class, weighted sum of McCabe cyclomatic complexities.
     *  For a package, sum of all WMC and WCP */
    private float weightedMethodsOrClasses;

    /** For a class, DC ÷ WMC. For a package, DC ÷ WCP. */
    private float bc;

    /**
     * Adds another Result's values to this.
     *
     * @param results Other objet whose values to use.
     */
    @Override
    public void add(Results results) {
        if (results instanceof ComplexityResults) {
            weightedMethodsOrClasses += ((ComplexityResults) results).weightedMethodsOrClasses;
            bc += ((ComplexityResults) results).bc;
        }
    }

    /**
     * Implements the Visitor's pattern for converting the computed
     * values to the proper output format.
     *
     * @param visitor Object whose `visit` method to call.
     */
    @Override
    public void accept(OutputVisitor visitor) {
        visitor.visit(this);
    }
}
