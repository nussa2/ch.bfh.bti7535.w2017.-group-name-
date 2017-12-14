package ch.bfh.bti7535.w2017.groupname.filter;

import weka.core.Instances;

import java.util.Random;

public class CrossValidationFilter implements FilterStep {

    int seed = 5;
    public static int FOLDS = 10;

    @Override
    public void init() {

    }

    @Override
    public Instances process(Instances dataset) {
        Random rand = new Random(seed);   // create seeded number generator
        Instances randData = new Instances(dataset);   // create copy of original data
        randData.randomize(rand);         // randomize data with number generator
        randData.stratify(FOLDS);
        return randData;
    }
}
