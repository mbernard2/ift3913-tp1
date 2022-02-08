package ca.umontreal.diro.ift3913.tp1.analysis;

public interface Analyser {
    /**
     * Sets the code to be analysed. Should represent a complete source file
     * since in Java, each file contains one and only one class.
     * @param content Class code to be analysed.
     */
    void setClassContent(String content);

    /**
     * Performs the analysis and returns the results as an instance
     * of a Results object.
     * @return Object containing the computed metrics.
     */
    Results analyse();
}
