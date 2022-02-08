package ca.umontreal.diro.ift3913.tp1.analysis;

import ca.umontreal.diro.ift3913.tp1.output.OutputVisitor;

public class ComplexityResults implements Results {
    private float weightedMethodsOrClasses;

    private float bc;

    /**
     * Produces a Results object with acts as a neutral element under addition.
     * @return A ComplexityResults for which all fields have value 0.
     */
    public static ComplexityResults zero() {
        return new ComplexityResults(0, 0);
    }

    /**
     * Constructs a new Results containing complexity metrics.
     * @param weightedMethodsOrClasses WMC or WCP
     * @param bc BC metric, defined as (DC ÷ WMC) or (DC ÷ WCP)
     */
    ComplexityResults(float weightedMethodsOrClasses, float bc) {
        this.weightedMethodsOrClasses = weightedMethodsOrClasses;
        this.bc = bc;
    }

    /**
     * For a class, weighted sum of McCabe cyclomatic complexities.
     * For a package, sum of all WMC and WCP
     */
    public float getWeightedMethodsOrClasses() {
        return weightedMethodsOrClasses;
    }

    /**
     * For a class, DC ÷ WMC. For a package, DC ÷ WCP.
     */
    public float getBc() {
        return bc;
    }

    /**
     * Adds another Result's values to this.
     *
     * @param results Other objet whose values to use.
     */
    @Override
    public void add(Results results) {
        if (results instanceof ComplexityResults) {
            weightedMethodsOrClasses = getWeightedMethodsOrClasses() + ((ComplexityResults) results).getWeightedMethodsOrClasses();
            bc = getBc() + ((ComplexityResults) results).getBc();
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
