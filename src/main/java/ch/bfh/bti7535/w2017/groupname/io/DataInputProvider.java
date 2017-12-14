package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instances;

public interface DataInputProvider {

    DataInputProvider setSource(String source);
    DataInputProvider init();
    Instances loadData();

}
