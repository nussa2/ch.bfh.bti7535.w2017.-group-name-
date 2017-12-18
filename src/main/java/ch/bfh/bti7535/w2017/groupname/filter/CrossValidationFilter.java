package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;

import java.util.Random;

public class CrossValidationFilter implements ProcessStep {

    int seed = 5;
    public static int FOLDS = 10;

    Instances dataset, resultset;

    @Override
    public void init() {

    }

    @Override
    public void process() {
        Random rand = new Random(seed);   // create seeded number generator
        resultset = new Instances(dataset);   // create copy of original data
        resultset.randomize(rand);         // randomize data with number generator
        resultset.stratify(FOLDS);
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataset = dataSet;
    }

    @Override
    public Instances getResultDataSet() {
        return resultset;
    }
}
