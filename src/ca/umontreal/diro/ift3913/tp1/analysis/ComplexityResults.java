package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class ComplexityResults implements Results {
    private float weightedMethodsOrClasses;

    /**
     * Produces a Results object with acts as a neutral element under addition.
     * @return A ComplexityResults for which all fields have value 0.
     */
    public static ComplexityResults zero() {
        return new ComplexityResults(0);
    }

    /**
     * Constructs a new Results containing complexity metrics.
     * @param weightedMethodsOrClasses WMC or WCP
     */
    ComplexityResults(float weightedMethodsOrClasses) {
        this.weightedMethodsOrClasses = weightedMethodsOrClasses;
    }

    /**
     * For a class, weighted sum of McCabe cyclomatic complexities.
     * For a package, sum of all WMC and WCP
     */
    public float getWeightedMethodsOrClasses() {
        return weightedMethodsOrClasses;
    }

    /**
     * Adds another Result's values to this.
     * @param results Other objet whose values to combine.
     */
    @Override
    public void combine(Results results) {
        if (results instanceof ComplexityResults) {
            weightedMethodsOrClasses += ((ComplexityResults) results).getWeightedMethodsOrClasses();
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