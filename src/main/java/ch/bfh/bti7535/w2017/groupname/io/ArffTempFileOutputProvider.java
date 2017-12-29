package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

/**
 * Schreibt ein ARFF-File ins UserHome
 */
public class ArffTempFileOutputProvider implements DataProvider, ProcessStep {

    public static final String ARFF_FILE_ENDING = ".arff";
    ArffSaver saver = new ArffSaver();
    private Instances dataSet;
    String destPath;

    @Override
    public void init() {

    }

    @Override
    public void process() {
        saveData(dataSet);
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public Instances getResultDataSet() {
        return dataSet;
    }

    /**
     * Setzt den Pfad zum neuen File zusammen
     *
     * @return
     */
    private String composePath() {
        String filename = "movie_sa_temp_" + Instant.now().toEpochMilli();
        return System.getProperty("user.home") + destPath + filename + ARFF_FILE_ENDING;
    }

    /**
     * Hier passiert das effektive Schreiben auf die HD
     * @param dataset
     */
    private void saveData(Instances dataset) {
        File outputFile = new File(composePath());
        try {
            saver.setInstances(dataset);
            saver.setFile(outputFile);
            saver.writeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataProvider setSource(String source) {
        this.destPath = source;
        return this;
    }
}
