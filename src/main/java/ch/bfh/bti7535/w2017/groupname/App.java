package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.filter.AttributeSelectionFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffTempFileOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.DataOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.InstancesLogger;
import ch.bfh.bti7535.w2017.groupname.process.DefaultFilterProcessChain;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {

        DefaultFilterProcessChain processChain = new DefaultFilterProcessChain();

        Instances instances = new ArffResourceInputProvider().loadData();

        DataOutputProvider logger = new InstancesLogger();

        logger.saveData(instances);

        processChain.addDataSet(instances);
        processChain.addFilter(new PreprocessingFilter());
        processChain.addFilter(new AttributeSelectionFilter());


        try {
            processChain.process();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Instances ppInstances = processChain.getResultSet();

        DataOutputProvider tempFileSaver = new ArffTempFileOutputProvider();

        tempFileSaver.saveData(ppInstances);

        NaiveBayes naiveBayes = new NaiveBayes();
    }

}
