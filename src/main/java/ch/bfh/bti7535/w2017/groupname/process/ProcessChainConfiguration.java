package ch.bfh.bti7535.w2017.groupname.process;

import java.util.List;

/**
 * Interface für eine ProcessChainConfiguration
 */
public interface ProcessChainConfiguration {

    /**
     *
     */
    void init();

    /**
     *
     * @param processStep
     */
    void addStep(ProcessStep processStep);

    /**
     *
     * @return
     */
    List<ProcessStep> getSteps();
}
