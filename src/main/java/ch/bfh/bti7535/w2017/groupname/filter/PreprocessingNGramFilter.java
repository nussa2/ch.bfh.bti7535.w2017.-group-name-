package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.stopwords.Rainbow;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * Filter, welcher den Text vektorisiert
 */
public class PreprocessingNGramFilter implements ProcessStep {

    private StringToWordVector stringToWordVector = new StringToWordVector();
    private NGramTokenizer nGramTokenizer = new NGramTokenizer();

    Instances dataset, resultset;

    /**
     *
     */
    public void init() {
        stringToWordVector.setIDFTransform(true);
        stringToWordVector.setTFTransform(true);
        stringToWordVector.setAttributeIndices("first-last");
        int[] array ={0};
        stringToWordVector.setAttributeIndicesArray(array);
        stringToWordVector.setDoNotOperateOnPerClassBasis(false);
        stringToWordVector.setInvertSelection(false);
        stringToWordVector.setLowerCaseTokens(true);
        stringToWordVector.setMinTermFreq(1);
        stringToWordVector.setOutputWordCounts(true);
        stringToWordVector.setPeriodicPruning(-1.0);
        stringToWordVector.setStopwordsHandler(new Rainbow());
        stringToWordVector.setWordsToKeep(4000);
        nGramTokenizer.setNGramMinSize(1);
        nGramTokenizer.setNGramMaxSize(5);
        stringToWordVector.setTokenizer(nGramTokenizer);
        System.out.println("init preprocessing: " + stringToWordVector.toString());
    }

    /**
     *
     */
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

    /**
     *
     * @param dataSet
     */
    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataset = dataSet;
    }

    /**
     *
     * @return
     */
    @Override
    public Instances getResultDataSet() {
        return resultset;
    }

}
