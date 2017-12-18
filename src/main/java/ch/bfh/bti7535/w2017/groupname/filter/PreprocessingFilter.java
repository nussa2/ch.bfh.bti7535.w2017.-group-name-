package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class PreprocessingFilter implements ProcessStep {

    private StringToWordVector stringToWordVector = new StringToWordVector();
    private WordTokenizer wordTokenizer = new WordTokenizer();
    private Stemmer stemmer = new NullStemmer();

    Instances dataset, resultset;

    @Override
    public void process() {
        System.out.println("started preprocessing..");
        try {
            stringToWordVector.setInputFormat(dataset);
            //stringToWordVector.input()
            resultset = Filter.useFilter(dataset, stringToWordVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        stringToWordVector.setIDFTransform(false);
        stringToWordVector.setTFTransform(false);
        //stringToWordVector.setAttributeIndices("first");
        int[] array ={0};
        stringToWordVector.setAttributeIndicesArray(array);
        stringToWordVector.setDoNotOperateOnPerClassBasis(false);
        //stringToWordVector.setNormalizeDocLength(new SelectedTag());
        stringToWordVector.setInvertSelection(false);
        stringToWordVector.setLowerCaseTokens(false);
        stringToWordVector.setMinTermFreq(1);
        stringToWordVector.setOutputWordCounts(true);
        //stringToWordVector.setPeriodicPruning(-1.0);
        //stringToWordVector.setStemmer(stemmer);
        stringToWordVector.setWordsToKeep(4000);
        wordTokenizer.setDelimiters(" /\r\n\t.,;:\'\"()?!&#*+=_-<>`˜~|");
        stringToWordVector.setTokenizer(wordTokenizer);
        System.out.println("init preprocessing: "+stringToWordVector.toString());
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
