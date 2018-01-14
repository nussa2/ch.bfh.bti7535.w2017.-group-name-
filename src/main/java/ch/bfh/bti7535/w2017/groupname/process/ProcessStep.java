package ch.bfh.bti7535.w2017.groupname.process;

import weka.core.Instances;

/**
 * Interface für einen ProcessStep
 */
public interface ProcessStep {

    /**
     * 
     */
    void init();

    /**
     *
     * @throws Exception
     */
    void process() throws Exception;

    /**
     *
     * @param dataSet
     */
    void setInitDataSet(Instances dataSet);

    /**
     *
     * @return
     */
    Instances getResultDataSet();
}
