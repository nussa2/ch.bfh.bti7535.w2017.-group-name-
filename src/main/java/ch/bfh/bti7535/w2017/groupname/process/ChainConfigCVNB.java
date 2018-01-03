package ch.bfh.bti7535.w2017.groupname.process;


import ch.bfh.bti7535.w2017.groupname.classify.NBClassifier;
import ch.bfh.bti7535.w2017.groupname.filter.CrossValidationFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffFileInputProvider;
import ch.bfh.bti7535.w2017.groupname.io.InstancesLogger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nalet
 */
public class ChainConfigCVNB implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    private String filePath;
    
    /**
     *
     */
    @Override
    public void init() {
        //addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_sa_selected_attributes_top_90.arff"));
        addStep((ProcessStep) new ArffFileInputProvider().setSource(this.filePath));
        addStep(new InstancesLogger());
        addStep(new CrossValidationFilter());
        addStep(new NBClassifier());
    }
    
    /**
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @param processStep
     */
    @Override
    public void addStep(ProcessStep processStep) {
        steps.add(processStep);
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProcessStep> getSteps() {
        return steps;
    }

}
