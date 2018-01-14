package ch.bfh.bti7535.w2017.groupname.classify;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.classifiers.Classifier;

/**
 * Interface für den ClassifierStep
 */
public interface ClassifierStep extends ProcessStep {
    /**
     * Classifier holen
     *
     * @return
     */
    Classifier getClassifier();

    /**
     * Classifier trainieren
     * @throws Exception
     */
    void train() throws Exception;
}
