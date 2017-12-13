package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.datainput.ARFFInputProvider;
import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {


        Instances instances = new ARFFInputProvider().loadData();
        //instances.setClassIndex(1);

        PreprocessingFilter ppFilter = new PreprocessingFilter();
        ppFilter.init();
        Instances ppInstances = ppFilter.preprocess(instances);


        System.out.println("summary: "+ppInstances.toSummaryString());

        Enumeration attributes = ppInstances.enumerateAttributes();
        while (attributes.hasMoreElements()){
            System.out.println(attributes.nextElement());
        }

        System.out.println("class attribute: "+ppInstances.classAttribute());
        System.out.println("# attributes " + ppInstances.numAttributes());
        System.out.println("# instances " + ppInstances.numInstances());
        System.out.println("# classes " + ppInstances.numClasses());

        System.out.println(instances.get(250));
        System.out.println(ppInstances.get(250));
        System.out.println(ppInstances.get(250).classValue());

        System.out.println(instances.get(1012));
        System.out.println(ppInstances.get(1012));
        System.out.println(ppInstances.get(1012).classValue());

        NaiveBayes naiveBayes = new NaiveBayes();
    }
}
