package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class AttributeSelectionFilter implements ProcessStep {

    AttributeSelection attributeSelection = new AttributeSelection();
    CfsSubsetEval cfsSubsetEval = new CfsSubsetEval();
    BestFirst bestFirstSearch = new BestFirst();

    Instances dataset, resultset;

    @Override
    public void init() {
        attributeSelection.setEvaluator(cfsSubsetEval);

        bestFirstSearch.setDirection(new SelectedTag("Forward",BestFirst.TAGS_SELECTION));
        try {
            bestFirstSearch.setSearchTermination(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        attributeSelection.setSearch(bestFirstSearch);
        System.out.println("init attribute selection: "+attributeSelection.toString());
    }

    @Override
    public void process() {
        System.out.println("started attribute selection..");
        try {
            attributeSelection.setInputFormat(dataset);
            //stringToWordVector.input()
            resultset = Filter.useFilter(dataset, attributeSelection);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
