package ch.bfh.bti7535.w2017.groupname.classify;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.classifiers.Classifier;

public interface ClassifierStep extends ProcessStep {
    Classifier getClassifier();
    void train() throws Exception;
}
