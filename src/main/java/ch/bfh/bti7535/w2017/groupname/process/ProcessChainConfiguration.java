package ch.bfh.bti7535.w2017.groupname.process;

import java.util.List;

/**
 * Interface für eine ProcessChainConfiguration
 */
public interface ProcessChainConfiguration {

    void init();

    void addStep(ProcessStep processStep);

    List<ProcessStep> getSteps();
}
