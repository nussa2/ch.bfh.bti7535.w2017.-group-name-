package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instances;

import java.util.Enumeration;

public class InstancesLogger implements DataOutputProvider {
    
    public static void logInstanceInfo(Instances instances){
        System.out.println("summary: "+instances.toSummaryString());

        System.out.println("class attribute: "+instances.classAttribute());
        System.out.println("# attributes " + instances.numAttributes());

        System.out.println("attributes: ");
        Enumeration attributes = instances.enumerateAttributes();
        while (attributes.hasMoreElements()){
            System.out.println(attributes.nextElement());
        }

        System.out.println("# instances " + instances.numInstances());
        System.out.println("# classes " + instances.numClasses());

        System.out.println(instances.get(250));
        System.out.println(instances.get(250).classValue());

        System.out.println(instances.get(1012));
        System.out.println(instances.get(1012).classValue());
    }

    @Override
    public void init() {

    }

    @Override
    public void saveData(Instances dataset) {
        InstancesLogger.logInstanceInfo(dataset);
    }
}
