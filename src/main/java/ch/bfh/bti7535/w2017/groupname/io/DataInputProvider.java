package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instances;

public interface DataInputProvider {

    void init();
    Instances loadData();

}
