package ch.bfh.bti7535.w2017.groupname.classify;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NBClassifier implements ClassifyStep {

    NaiveBayes naiveBayes = new NaiveBayes();


    @Override
    public void init() {

    }

    @Override
    public Instances process(Instances dataset) {
        return null;
    }

    @Override
    public void train(Instances instances) throws Exception {
        naiveBayes.buildClassifier(instances);
    }

    @Override
    public double evaluate(Instances referenceSet, Instances testSet) throws Exception {
        Evaluation evaluation = new Evaluation(referenceSet);
        evaluation.evaluateModel(naiveBayes, testSet);
        System.out.println("Evaluation done");
        System.out.println("Error rate is: "+evaluation.errorRate());
        System.out.println("Evaluation summary: "+evaluation.toSummaryString());
        return evaluation.errorRate();
    }

}
