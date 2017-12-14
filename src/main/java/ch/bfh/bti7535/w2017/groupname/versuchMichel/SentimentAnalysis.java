package ch.bfh.bti7535.w2017.groupname.versuchMichel;

import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.io.DataInputProvider;
import weka.classifiers.trees.J48;
import weka.core.*;
import weka.core.converters.TextDirectoryLoader;
import weka.core.stemmers.NullStemmer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SentimentAnalysis {
    private List<Filter> filters = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        SentimentAnalysis analysis = new SentimentAnalysis();
        StringToWordVector stringToWordVector = new StringToWordVector();
        configureStringToWordVector(stringToWordVector);
        analysis.addFilter(stringToWordVector);

        DataInputProvider dataInputProvider = new ArffResourceInputProvider();
        Instances instances = dataInputProvider.loadData();
        Instances filteredInstances = analysis.applyFilters(instances);

        J48 classifier = new J48();
        classifier.classifyInstance(analysis.createInstance("good, really nice!", filteredInstances));
        classifier.buildClassifier(filteredInstances);
        System.out.println("classifier = " + classifier);
    }

    private static void configureStringToWordVector(StringToWordVector stringToWordVector) {
        stringToWordVector.setIDFTransform(false);
        stringToWordVector.setTFTransform(false);
        stringToWordVector.setAttributeIndices("first");
        stringToWordVector.setDoNotOperateOnPerClassBasis(false);
        stringToWordVector.setInvertSelection(false);
        stringToWordVector.setLowerCaseTokens(false);
        stringToWordVector.setMinTermFreq(1);
        stringToWordVector.setOutputWordCounts(false);
        stringToWordVector.setPeriodicPruning(-1.0);
        stringToWordVector.setStemmer(new NullStemmer());
        stringToWordVector.setWordsToKeep(2000);
    }

    private Instance createInstance(String review, Instances currentInstances) {
        Instance instance = new DenseInstance(2);
        Attribute attribute = currentInstances.attribute("text_review");
        instance.setValue(attribute, attribute.addStringValue(review));
        instance.setDataset(currentInstances);
        return instance;
    }

    private Instances applyFilters(Instances instances) throws Exception {
        System.out.println("instances = " + instances);
        Instances result = null;
        for (Filter filter : filters) {
            filter.setInputFormat(instances);
            result = Filter.useFilter(instances, filter);
        }
        System.out.println("instances = " + instances);
        return result;
    }

    private Instances readRawData() throws IOException {
        TextDirectoryLoader loader = new TextDirectoryLoader();
        loader.setDirectory(new File("C:\\Users\\Miche\\Desktop\\txt_sentoken"));
        loader.setDebug(true);
        loader.setOutputFilename(false);
        return loader.getDataSet();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }
}
