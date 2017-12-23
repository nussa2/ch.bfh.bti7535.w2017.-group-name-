package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import ch.bfh.bti7535.w2017.groupname.filter.SentimentLexiconFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.io.ArffTempFileOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.SentimentLexiconProvider;
import ch.bfh.bti7535.w2017.groupname.io.InstancesLogger;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class ChainConfigSentimentLexicon implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    @Override
    public void init() {
        addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_reviews_raw.arff"));
        addStep(new PreprocessingFilter());
        addStep(new SentimentLexiconFilter());
        //addStep((ProcessStep) new SentimentLexiconProvider().setSource("/temp/movie-sa/"));
        //addStep(new InstancesLogger());
        addStep((ProcessStep) new ArffTempFileOutputProvider().setSource("/temp/movie-sa/"));
    }

    @Override
    public void addStep(ProcessStep processStep) {
        steps.add(processStep);
    }

    @Override
    public List<ProcessStep> getSteps() {
        return steps;
    }

    @Override
    public Instances getResultSet() {
        return steps.get(steps.size()-1).getResultDataSet();
    }
}
