package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class ArffTempFileOutputProvider implements DataOutputProvider{

    public static final String TEMP_DIR = "/temp/movie-sa/";
    public static final String ARFF_FILE_ENDING = ".arff";
    ArffSaver saver = new ArffSaver();
    String userHome = System.getProperty("user.home");


    @Override
    public void init() {

    }

    @Override
    public void saveData(Instances dataset) {
        String filename = "movie_sa_temp_"+ Instant.now().toEpochMilli();
        File outputFile = new File(userHome+ TEMP_DIR +filename+ ARFF_FILE_ENDING);
        try {
            saver.setInstances(dataset);
            saver.setFile(outputFile);
            saver.writeBatch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
