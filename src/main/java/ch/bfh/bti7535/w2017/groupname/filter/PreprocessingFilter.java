package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.stopwords.Rainbow;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * Filter, welcher den Text vektorisiert
 */
public class PreprocessingFilter implements ProcessStep {

    private StringToWordVector stringToWordVector = new StringToWordVector();
    private WordTokenizer wordTokenizer = new WordTokenizer();

    Instances dataset, resultset;

    public void init() {
        // Konfiguration des StringToWordVector
        stringToWordVector.setIDFTransform(true);
        stringToWordVector.setTFTransform(true);
        int[] array = {0};
        stringToWordVector.setAttributeIndicesArray(array);
        stringToWordVector.setDoNotOperateOnPerClassBasis(false);
        stringToWordVector.setInvertSelection(false);
        stringToWordVector.setLowerCaseTokens(true);
        stringToWordVector.setMinTermFreq(5);
        stringToWordVector.setOutputWordCounts(true);
        stringToWordVector.setPeriodicPruning(-1.0);
        stringToWordVector.setStopwordsHandler(new Rainbow());
        stringToWordVector.setWordsToKeep(10000);
        wordTokenizer.setDelimiters(" /\r\n\t.,;:\'\"()?!&#*+=_-<>`˜~|");
        stringToWordVector.setTokenizer(wordTokenizer);
        System.out.println("init preprocessing: " + stringToWordVector.toString());
    }

    @Override
    public void process() {
        System.out.println("started preprocessing..");
        try {
            stringToWordVector.setInputFormat(dataset);
            resultset = Filter.useFilter(dataset, stringToWordVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataset = dataSet;
    }

    @Override
    public Instances getResultDataSet() {
        return resultset;
    }

}
