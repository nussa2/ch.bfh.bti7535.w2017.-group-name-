package ch.bfh.bti7535.w2017.groupname.process;


import ch.bfh.bti7535.w2017.groupname.classify.NBClassifier;
import ch.bfh.bti7535.w2017.groupname.filter.*;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * ChainConfig für Sentiment Attributselektion.
 */
public class ChainConfigSentimentAttrSelCVNB implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    /**
     *
     */
    @Override
    public void init() {
        addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_reviews_raw.arff"));
        addStep(new PreprocessingSimpleFilter());
        addStep(new SentimentLexiconWeightedFilter());
        addStep(new AttributeSelectionFilter());
        addStep(new CrossValidationFilter());
        addStep(new NBClassifier());
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
