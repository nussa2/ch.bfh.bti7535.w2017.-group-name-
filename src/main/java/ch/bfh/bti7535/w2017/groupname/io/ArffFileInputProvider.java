package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Liefert die Inhalte einer Arff-Datei als Instances zurück
 */
public class ArffFileInputProvider implements DataProvider, ProcessStep{

    private Instances dataSet;
    String destPath;

    /**
     *
     * @param source
     * @return
     */
    @Override
    public DataProvider setSource(String source) {
        destPath = source;
        return this;
    }

    /**
     *
     */
    @Override
    public void init() {

    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        dataSet = loadInstancesFromFile();
    }

    /**
     *
     * @param dataSet
     */
    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataSet = dataSet;
    }

    /**
     *
     * @return
     */
    @Override
    public Instances getResultDataSet() {
        return dataSet;
    }

    /**
     * Liest die Instancen aus dem File
     *
     * @return
     */
    private Instances loadInstancesFromFile(){
        Instances data = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(composeFileName()))) {
            data = new Instances(reader);

            if (data.classIndex() == -1){
                data.setClassIndex(data.numAttributes() - 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Gibt den zusammengesetzten Filenamen zurück
     * @return
     */
    private String composeFileName() {
        return System.getProperty("user.home") + destPath;
    }
}
