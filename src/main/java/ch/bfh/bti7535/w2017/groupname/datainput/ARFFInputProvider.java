package ch.bfh.bti7535.w2017.groupname.datainput;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.IOException;
import java.io.InputStream;

public class ARFFInputProvider implements DataInputProvider {


    @Override
    public void init() {
    }

    @Override
    public Instances loadData() {
        ArffLoader loader = new ArffLoader();
        Instances data = null;

        try (InputStream inputStream = ARFFInputProvider.class.getResourceAsStream("/movie_reviews_raw.arff")){

            loader.setSource(inputStream);
            //loader.getStructure();
            data = loader.getDataSet();

            //data.setClassIndex(data.numAttributes() - 1);

            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

            /*Instance instance;
            while ((instance = loader.getNextInstance(data)) != null){
                data.add(instance);
            }*/

            return data;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
