package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.GainRatioAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

/**
 * Filter zur Auswahl der Klassen-Korrelation zuträglichsten Wörter
 */
public class AttributeSelectionRankFilter implements ProcessStep {

    AttributeSelection attributeSelection = new AttributeSelection();
    GainRatioAttributeEval gainRatioAttributeEval = new GainRatioAttributeEval();
    Ranker ranker = new Ranker();

    Instances dataset, resultset;

    /**
     *
     */
    @Override
    public void init() {
        // Konfiguration des gainRatioAttributeEval

        // Konfiguration des ranker
        ranker.setNumToSelect(1000);

        // Konfiguration der AttributeSelection
        attributeSelection.setEvaluator(gainRatioAttributeEval);
        attributeSelection.setSearch(ranker);
        System.out.println("init attribute selection: " + attributeSelection.toString());
    }

    /**
     *
     */
    @Override
    public void process() {
        System.out.println("started attribute selection..");
        try {
            attributeSelection.setInputFormat(dataset);
            resultset = Filter.useFilter(dataset, attributeSelection);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
