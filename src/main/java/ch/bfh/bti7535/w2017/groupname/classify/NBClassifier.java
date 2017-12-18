package ch.bfh.bti7535.w2017.groupname.classify;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NBClassifier implements ClassifierStep {

    NaiveBayes naiveBayes = new NaiveBayes();
    Instances trainset;


    @Override
    public void init() {

    }

    @Override
    public void process() throws Exception {
        train();
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        trainset = dataSet;
    }

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
