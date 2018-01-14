package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.filter.AttributeSelectionFilter;
import ch.bfh.bti7535.w2017.groupname.filter.AttributeSelectionRankFilter;
import ch.bfh.bti7535.w2017.groupname.filter.PreprocessingFilter;
import ch.bfh.bti7535.w2017.groupname.io.ArffResourceInputProvider;
import ch.bfh.bti7535.w2017.groupname.io.ArffTempFileOutputProvider;
import ch.bfh.bti7535.w2017.groupname.io.InstancesLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * ChainConfiguration zur Anwendung des AttributeSelectionFilter's. Die entsprechenden Daten werden
 * anschliessend im User-Home gespeichert
 */
public class ChainConfigRankAttSelect implements ProcessChainConfiguration {

    private List<ProcessStep> steps = new ArrayList<>();

    /**
     *
     */
    @Override
    public void init() {
        addStep((ProcessStep) new ArffResourceInputProvider().setSource("/movie_reviews_raw.arff"));
        addStep(new PreprocessingFilter());
        addStep(new InstancesLogger());
        addStep(new AttributeSelectionRankFilter());
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
