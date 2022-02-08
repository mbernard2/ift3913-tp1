package ca.umontreal.diro.ift3913.tp1.output;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityResults;
import ca.umontreal.diro.ift3913.tp1.analysis.LocResults;

public interface OutputVisitor {
    /**
     * Sets the value to be set to the "path" column.
     * @param path Path to be outputted.
     */
    void setCurrentPath(String path);

    /**
     * Sets the value to be set to the "class" or "package" column.
     * @param name Name to be outputted.
     */
    void setCurrentItemName(String name);

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

    /**
     * Returns the constructed CSV file based on visited values.
     */
    void getCsvString();
}
