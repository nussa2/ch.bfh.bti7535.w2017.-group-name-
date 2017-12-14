package ch.bfh.bti7535.w2017.groupname.filter;

import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.lang.reflect.Array;

public class PreprocessingFilter implements FilterStep {

    private StringToWordVector stringToWordVector = new StringToWordVector();
    private WordTokenizer wordTokenizer = new WordTokenizer();
    private Stemmer stemmer = new NullStemmer();


    @Override
    public Instances process(Instances instances) {
        System.out.println("started preprocessing..");
        try {
            stringToWordVector.setInputFormat(instances);
            //stringToWordVector.input()
            return Filter.useFilter(instances, stringToWordVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

}
