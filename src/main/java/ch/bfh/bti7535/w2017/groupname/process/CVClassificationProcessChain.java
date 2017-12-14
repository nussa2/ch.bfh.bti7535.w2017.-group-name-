package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.classify.ClassifyStep;
import ch.bfh.bti7535.w2017.groupname.filter.CrossValidationFilter;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class CVClassificationProcessChain implements ProcessChain {

    private List<Instances> dataSets = new ArrayList<>();
    private List<ClassifyStep> classifiers = new ArrayList<>();
    private int folds;

    @Override
    public void init() {
        folds = CrossValidationFilter.FOLDS;
    }

    public void addDataSet(Instances instances){
        dataSets.add(instances);
    }

    public Instances getResultSet(){
        return dataSets.get(dataSets.size()-1);
    }

    @Override
    public void process() throws Exception {
        System.out.println("classify with "+folds+" folds");
        double error = 0;
        for (int n = 0; n < folds; n++) {
            System.out.println("fold "+(n+1));
            Instances train = dataSets.get(0).trainCV(folds, n);
            Instances test =  dataSets.get(0).testCV(folds, n);

            classifiers.get(0).init();
            System.out.println("train classifier");
            classifiers.get(0).train(train);
            System.out.println("evaluate classifier");
            error = error + classifiers.get(0).evaluate(dataSets.get(0),test);
        }
        double meanError = error/((double)folds);
        System.out.println("evaluation done, mean success rate: "+(1-meanError));
    }

    @Override
    public void addStep(ProcessStep processStep) {
        classifiers.add((ClassifyStep) processStep);
    }
}
