package ch.bfh.bti7535.w2017.groupname.filter;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

public class AttributeSelectionFilter implements FilterStep {

    AttributeSelection attributeSelection = new AttributeSelection();
    CfsSubsetEval cfsSubsetEval = new CfsSubsetEval();
    BestFirst bestFirstSearch = new BestFirst();

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
    public Instances process(Instances dataset) {
        System.out.println("started attribute selection..");
        try {
            attributeSelection.setInputFormat(dataset);
            //stringToWordVector.input()
            return Filter.useFilter(dataset, attributeSelection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
