package ch.bfh.bti7535.w2017.groupname.process;

import weka.core.Instances;

import java.util.List;

public interface ProcessChainConfiguration {

    void init();
    void addStep(ProcessStep processStep);
    List<ProcessStep> getSteps();
    Instances getResultSet();
}
