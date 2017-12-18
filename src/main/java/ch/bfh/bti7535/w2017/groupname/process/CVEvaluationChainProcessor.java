package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.classify.ClassifierStep;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class CVEvaluationChainProcessor implements ChainProcessor {

    private int folds = 10;

    private int stepCount = 0;

    private double validationResultErrorRate;

    @Override
    public void process(ProcessChainConfiguration chain) throws Exception {
        chain.init();
        applyFilters(0, (chain.getSteps().size()-1),chain);
    }

    private void applyFilters(int startStep, int stopStep, ProcessChainConfiguration chain) throws Exception {

        System.out.println("started process chain.");

        stepCount = startStep;

        while (stepCount <= stopStep){
            ProcessStep step = chain.getSteps().get(stepCount);
            System.out.println("execute step "+(stepCount+1)+" of "+(stopStep-startStep+1));

            Instances dataSet = null;
            step.init();

            if (stepCount > 0 && chain.getSteps().get((stepCount-1)).getResultDataSet() != null){
                dataSet =  chain.getSteps().get((stepCount-1)).getResultDataSet();
            }

            if (step instanceof ClassifierStep){
                double result = processEvaluated(step, dataSet);
                validationResultErrorRate = result;
                return;
            } else {
                step.setInitDataSet(dataSet);
                step.process();
            }

            stepCount++;
        }
    }

    private double processEvaluated(ProcessStep step, Instances dataSet) throws Exception {
        System.out.println("classify with "+folds+" folds");
        double error = 0;
        for (int n = 0; n < folds; n++) {
            System.out.println("fold "+(n+1));
            Instances train = dataSet.trainCV(folds, n);
            Instances test =  dataSet.testCV(folds, n);

            ClassifierStep classifierStep = (ClassifierStep) step;
            classifierStep.setInitDataSet(train);

            System.out.println("train classifier");
            classifierStep.process();

            error = error + evaluate(classifierStep, dataSet, test);
        }
        double meanError = error/((double)folds);
        System.out.println("evaluation done, mean success rate: "+(1-meanError));
        return meanError;
    }

    private double evaluate(ClassifierStep classifierStep, Instances referenceSet, Instances testSet) throws Exception {
        System.out.println("evaluate classifier");

        Evaluation evaluation = new Evaluation(referenceSet);
        evaluation.evaluateModel(classifierStep.getClassifier(), testSet);
        System.out.println("Evaluation done");
        System.out.println("Error rate is: "+evaluation.errorRate());
        System.out.println("Evaluation summary: "+evaluation.toSummaryString());
        return evaluation.errorRate();
    }

    public double getValidationResultErrorRate() {
        return validationResultErrorRate;
    }
}
