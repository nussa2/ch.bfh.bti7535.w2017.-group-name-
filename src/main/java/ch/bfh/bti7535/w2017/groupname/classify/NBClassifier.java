package ch.bfh.bti7535.w2017.groupname.classify;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

/**
 * Der NaiveBayes-Classifier
 */
public class NBClassifier implements ClassifierStep {

    NaiveBayes naiveBayes = new NaiveBayes();
    Instances trainset;

    /**
     *
     */
    @Override
    public void init() {
        // no implementation
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void process() throws Exception {
        train();
    }

    /**
     *
     * @param dataSet
     */
    @Override
    public void setInitDataSet(Instances dataSet) {
        trainset = dataSet;
    }

    /**
     *
     * @return
     */
    @Override
    public Instances getResultDataSet() {
        return trainset;
    }

    @Override
    public Classifier getClassifier() {
        return naiveBayes;
    }

    @Override
    public void train() throws Exception {
        naiveBayes.buildClassifier(trainset);
    }
}
