package ca.umontreal.diro.ift3913.tp1.output;

import ca.umontreal.diro.ift3913.tp1.analysis.ComplexityResults;
import ca.umontreal.diro.ift3913.tp1.analysis.LocResults;
import com.github.javaparser.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class CsvOutputVisitor implements OutputVisitor {
    private static class Line {
        float loc;
        float cloc;
        float dc;
        float complexity;
    }

    private final String pathColumnName;
    private final String itemColumnName;
    private final String locColumnName;
    private final String clocColumnName;
    private final String dcColumnName;
    private final String complexityColumnName;
    private final String bcColumnName;

    private String pathValue = "";
    private String itemNameValue = "";

    private Map<Pair<String, String>, Line> data = new HashMap<>();

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
        Line line = getCurrentLine();
        line.loc = results.getLoc();
        line.cloc = results.getCloc();
        line.dc = results.getDc();
    }

    /**
     * Visits a ComplexityResults object.
     * @param results Object to visit.
     */
    @Override
    public void visit(ComplexityResults results) {
        Line line = getCurrentLine();
        line.complexity = results.getWeightedMethodsOrClasses();
    }

    /**
     * Returns the constructed CSV file based on visited values.
     * @return CSV file content.
     */
    @Override
    public String getCsvString() {
        StringBuilder builder = new StringBuilder();

        builder.append(pathColumnName)
               .append(",")
               .append(itemColumnName)
               .append(",")
               .append(locColumnName)
               .append(",")
               .append(clocColumnName)
               .append(",")
               .append(dcColumnName)
               .append(",")
               .append(complexityColumnName)
               .append(",")
               .append(bcColumnName)
               .append("\n");

        data.forEach((key, lineData) -> {
            float bc = lineData.dc / lineData.complexity;
            builder.append(key.a)
                   .append(",")
                   .append(key.b)
                   .append(",")
                   .append(lineData.loc)
                   .append(",")
                   .append(lineData.cloc)
                   .append(",")
                   .append(lineData.dc)
                   .append(",")
                   .append(lineData.complexity)
                   .append(",")
                   .append(bc)
                   .append("\n");
        });

        return builder.toString();
    }

    private Line getCurrentLine() {
        Pair<String, String> pair = new Pair<>(pathValue, itemNameValue);

        if (!data.containsKey(pair)) {
            Line newLine = new Line();
            data.put(pair, newLine);
            return newLine;
        } else {
            return data.get(pair);
        }
    }
}
