package ca.umontreal.diro.ift3913.tp1.output;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityResults;
import ca.umontreal.diro.ift3913.tp1.analysis.LocResults;

public interface OutputVisitor {
    /**
     * Visits a LocResults object.
     * @param results Object to visit.
     */
    void visit(LocResults results);

    /**
     * Visits a ComplexityResults object.
     * @param results Object to visit.
     */
    void visit(ComplexityResults results);
}
