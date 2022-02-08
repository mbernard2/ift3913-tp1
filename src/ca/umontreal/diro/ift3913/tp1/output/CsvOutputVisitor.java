package ca.umontreal.diro.ift3913.tp1.output;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityResults;
import ca.umontreal.diro.ift3913.tp1.analysis.LocResults;

public class CsvOutputVisitor implements OutputVisitor {
    private final String pathColumnName;
    private final String itemColumnName;
    private final String locColumnName;
    private final String clocColumnName;
    private final String dcColumnName;
    private final String complexityColumnName;
    private final String bcColumnName;

    private String pathValue = "";
    private String itemNameValue = "";

    public CsvOutputVisitor(
            String pathColumnName,
            String itemColumnName,
            String locColumnName,
            String clocColumnName,
            String dcColumnName,
            String complexityColumnName,
            String bcColumnName) {
        this.pathColumnName = pathColumnName;
        this.itemColumnName = itemColumnName;
        this.locColumnName = locColumnName;
        this.clocColumnName = clocColumnName;
        this.dcColumnName = dcColumnName;
        this.complexityColumnName = complexityColumnName;
        this.bcColumnName = bcColumnName;
    }

    /**
     * Sets the value to be set to the "path" column.
     * @param path Path to be outputted.
     */
    @Override
    public void setCurrentPath(String path) {
        pathValue = path;
    }

    /**
     * Sets the value to be set to the "class" or "package" column.
     * @param name Name to be outputted.
     */
    @Override
    public void setCurrentItemName(String name) {
        itemNameValue = name;
    }

    /**
     * Visits a LocResults object.
     * @param results Object to visit.
     */
    @Override
    public void visit(LocResults results) {
        // TODO
    }

    /**
     * Visits a ComplexityResults object.
     * @param results Object to visit.
     */
    @Override
    public void visit(ComplexityResults results) {
        // TODO
    }

    /**
     * Returns the constructed CSV file based on visited values.
     */
    @Override
    public void getCsvString() {

    }
}
