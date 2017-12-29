package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Liest ein ARFF-File aus den Resourcen und liefert die Instanzen zurück
 */
public class ArffResourceInputProvider implements DataProvider, ProcessStep {

    private String source_path;

    private Instances dataSet;

    @Override
    public DataProvider setSource(String source) {
        source_path = source;
        return this;
    }

    @Override
    public void init() {
    }

    @Override
    public void process() {
        dataSet = loadData();
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
    }

    @Override
    public Instances getResultDataSet() {
        return dataSet;
    }

    /**
     * Die Daten werden geladen und zurückgeliefert.
     *
     * @return
     */
    private Instances loadData() {
        ArffLoader loader = new ArffLoader();
        Instances data = null;

        try (InputStream inputStream = ArffResourceInputProvider.class.getResourceAsStream(source_path)){

            loader.setSource(inputStream);
            data = loader.getDataSet();

            if (data.classIndex() == -1){
                data.setClassIndex(data.numAttributes() - 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
