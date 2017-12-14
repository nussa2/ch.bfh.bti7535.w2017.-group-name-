package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.classify.NBClassifier;
import ch.bfh.bti7535.w2017.groupname.filter.AttributeSelectionFilter;
import ch.bfh.bti7535.w2017.groupname.filter.CrossValidationFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffTempFileOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.DataOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.InstancesLogger;
import ch.bfh.bti7535.w2017.groupname.process.CVClassificationProcessChain;
import ch.bfh.bti7535.w2017.groupname.process.DefaultFilterProcessChain;
import ch.bfh.bti7535.w2017.groupname.process.ProcessChain;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {

        DefaultFilterProcessChain filterChain = new DefaultFilterProcessChain();
        filterChain.init();

        //Instances instances = new ArffResourceInputProvider().init().setSource("/movie_reviews_raw.arff").loadData();
        Instances instances = new ArffResourceInputProvider().init().setSource("/movie_sa_selected_attributes_top_90.arff").loadData();

        DataOutputProvider logger = new InstancesLogger();

        logger.saveData(instances);

        filterChain.addDataSet(instances);
        //filterChain.addStep(new PreprocessingFilter());
        //filterChain.addStep(new AttributeSelectionFilter());
        filterChain.addStep(new CrossValidationFilter());



        try {
            filterChain.process();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Instances ppInstances = filterChain.getResultSet();
        logger.saveData(ppInstances);

        ProcessChain classificationChain = new CVClassificationProcessChain();
        classificationChain.init();
        classificationChain.addDataSet(ppInstances);
        classificationChain.addStep(new NBClassifier());

        try {
            classificationChain.process();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //DataOutputProvider tempFileSaver = new ArffTempFileOutputProvider();

        //tempFileSaver.saveData(ppInstances);
    }

}
