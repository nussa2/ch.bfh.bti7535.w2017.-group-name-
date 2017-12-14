package ch.bfh.bti7535.w2017.groupname.process;

import weka.core.Instances;

public interface ProcessStep {

    void init();
    Instances process(Instances dataset);
}
