package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArffFileInputProvider implements DataProvider, ProcessStep{


    public static final String ARFF_FILE_ENDING = ".arff";

    private Instances dataSet;
    String destPath;

    @Override
    public DataProvider setSource(String source) {
        destPath = source;
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void process() throws Exception {
        dataSet = loadInstancesFromFile();
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public Instances getResultDataSet() {
        return dataSet;
    }

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

    private String composeFileName(){
        String filename = "movie_sa_temp_1513615918865";
        return System.getProperty("user.home") + destPath +filename+ ARFF_FILE_ENDING;
    }
}
