package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.IOException;
import java.io.InputStream;

public class ArffResourceInputProvider implements DataInputProvider {

    private String source_path;

    @Override
    public DataInputProvider setSource(String source) {
        source_path = source;
        return this;
    }

    @Override
    public DataInputProvider init() {
        return this;
    }

    @Override
    public Instances loadData() {
        ArffLoader loader = new ArffLoader();
        Instances data = null;

        try (InputStream inputStream = ArffResourceInputProvider.class.getResourceAsStream(source_path)){

            loader.setSource(inputStream);
            //data = loader.getStructure();
            data = loader.getDataSet();

            //data.setClassIndex(data.numAttributes() - 1);

            if (data.classIndex() == -1){
                data.setClassIndex(data.numAttributes() - 1);
            }

            //Instance instance;
            /*while ((instance = loader.getNextInstance(data)) != null){
                //System.out.println("loaded instance: "+instance);
                data.add(instance);
            }*/
            //for (int i = 0; i)

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
