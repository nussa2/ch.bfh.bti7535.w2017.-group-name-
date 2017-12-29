package ch.bfh.bti7535.w2017.groupname.process;

import weka.core.Instances;

/**
 * Interface für einen ProcessStep
 */
public interface ProcessStep {

    void init();

    void process() throws Exception;

    void setInitDataSet(Instances dataSet);

    Instances getResultDataSet();
}
