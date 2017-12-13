package ch.bfh.bti7535.w2017.groupname.filter;

import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.stemmers.NullStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class PreprocessingFilter {

    private StringToWordVector stringToWordVector = new StringToWordVector();
    private WordTokenizer wordTokenizer = new WordTokenizer();
    private Stemmer stemmer = new NullStemmer();

    public Instances preprocess(Instances instances){
        try {
            stringToWordVector.setInputFormat(instances);
            return Filter.useFilter(instances, stringToWordVector);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void init() {
        stringToWordVector.setIDFTransform(false);
        stringToWordVector.setTFTransform(false);
        stringToWordVector.setAttributeIndices("first");
        stringToWordVector.setDoNotOperateOnPerClassBasis(true);
        //stringToWordVector.setNormalizeDocLength(new SelectedTag());
        stringToWordVector.setInvertSelection(false);
        stringToWordVector.setLowerCaseTokens(false);
        stringToWordVector.setMinTermFreq(1);
        stringToWordVector.setOutputWordCounts(false);
        stringToWordVector.setPeriodicPruning(-1.0);
        //stringToWordVector.setStemmer(stemmer);
        stringToWordVector.setWordsToKeep(5000);
        wordTokenizer.setDelimiters(" \r\n\t.,;:\'\"()?!");
        stringToWordVector.setTokenizer(wordTokenizer);
    }

}
