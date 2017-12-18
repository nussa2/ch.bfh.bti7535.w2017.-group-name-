package ch.bfh.bti7535.w2017.groupname.process;


import ch.bfh.bti7535.w2017.groupname.classify.NBClassifier;
import ch.bfh.bti7535.w2017.groupname.filter.CrossValidationFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class ChainConfigCVNB implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    @Override
    public void init() {
        addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_sa_selected_attributes_top_90.arff"));
        addStep(new CrossValidationFilter());
        addStep(new NBClassifier());
    }

    @Override
    public void addStep(ProcessStep processStep) {
        steps.add(processStep);
    }

    @Override
    public List<ProcessStep> getSteps() {
        return steps;
    }

    public Instances getResultSet(){
        return steps.get(steps.size()-1).getResultDataSet();
    }

}
