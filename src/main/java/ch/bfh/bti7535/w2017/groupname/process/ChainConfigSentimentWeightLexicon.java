package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import ch.bfh.bti7535.w2017.groupname.filter.SentimentLexiconWeightedFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.io.ArffTempFileOutputProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class ChainConfigSentimentWeightLexicon implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    /**
     *
     */
    @Override
    public void init() {
        addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_reviews_raw.arff"));
        addStep(new PreprocessingFilter());
        addStep(new SentimentLexiconWeightedFilter());
        addStep((ProcessStep) new ArffTempFileOutputProvider().setSource("/temp/movie-sa/"));
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
