package ch.bfh.bti7535.w2017.groupname.process;

import ch.bfh.bti7535.w2017.groupname.filter.FilterStep;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

public class DefaultFilterProcessChain implements ProcessChain {

    private List<FilterStep> filters = new ArrayList<>();
    private List<Instances> dataSets = new ArrayList<>();
    private int stepCount = 0;

    public void addFilter(FilterStep filter) {
        filters.add(filter);
    }

    @Override
    public void init() {

    }

    public void process() throws Exception {
        applyFilters(0, (filters.size()-1));
    }

    public void addDataSet(Instances instances){
        dataSets.add(instances);
    }

    public Instances getResultSet(){
        return dataSets.get(dataSets.size()-1);
    }

    private void applyFilters( int startStep, int stopStep) throws Exception {

        System.out.println("started process chain.");
        //System.out.println("instances = " + instances);

        stepCount = startStep;
        int dataSetCount = 0;

        while (stepCount <= stopStep){
            System.out.println("execute filter "+stepCount+" of "+(stopStep-startStep+1));
            filters.get(stepCount).init();
            dataSets.add(filters.get(stepCount).process(dataSets.get(dataSetCount)));

            stepCount++;
            dataSetCount++;
        }

        //System.out.println("instances = " + instances);
    }
}
