package ch.bfh.bti7535.w2017.groupname.filter;

import weka.core.Instances;

public interface FilterStep {

    void init();
    Instances process(Instances dataset);
}
