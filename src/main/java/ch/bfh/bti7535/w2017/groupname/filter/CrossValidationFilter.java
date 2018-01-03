package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;

import java.util.Random;

/**
 * k-fold zum Trainieren und Testen
 */
public class CrossValidationFilter implements ProcessStep {

    int seed = 5;

    /**
     *
     */
    public static int FOLDS = 10;

    Instances dataset, resultset;

    /**
     *
     */
    @Override
    public void init() {

    }

    /**
     *
     */
    @Override
    public void process() {
        // Seeded Number Generator erstellen
        Random rand = new Random(seed);
        // Orginaldaten kopieren
        resultset = new Instances(dataset);
        resultset.randomize(rand);
        resultset.stratify(FOLDS);
    }

    /**
     *
     * @param dataSet
     */
    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataset = dataSet;
    }

    /**
     *
     * @return
     */
    @Override
    public Instances getResultDataSet() {
        return resultset;
    }
}
