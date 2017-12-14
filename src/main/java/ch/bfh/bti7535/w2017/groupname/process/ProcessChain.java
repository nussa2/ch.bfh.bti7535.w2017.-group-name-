package ch.bfh.bti7535.w2017.groupname.process;

import weka.core.Instances;

public interface ProcessChain {

    void init();
    void process() throws Exception;
    void addStep(ProcessStep processStep);
    void addDataSet(Instances dataSet);
    Instances getResultSet();
}
