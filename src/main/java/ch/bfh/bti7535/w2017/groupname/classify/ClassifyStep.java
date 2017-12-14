package ch.bfh.bti7535.w2017.groupname.classify;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;

public interface ClassifyStep extends ProcessStep {
    void train(Instances instances) throws Exception;
    double evaluate(Instances referenceSet, Instances testSet) throws Exception;
}
