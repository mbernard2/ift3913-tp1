package ca.umontreal.diro.ift3913.tp1.analysis;

public class LocAnalyser implements Analyser {
    /**
     * Sets the code to be analysed. Should represent a complete source file
     * since in Java, each file contains one and only one class.
     * @param content Class code to be analysed.
     */
    @Override
    public void setClassContent(String content) {

    }

    /**
     * Analyses the source code provided via the `setClassContent` method, and
     * returns metrics regarding the number of lines in total compared to the
     * number of lines containing comments.
     * @return Object containing the computed metrics.
     */
    @Override
    public LocResults analyse() {
        // TODO
        return null;
    }
}
