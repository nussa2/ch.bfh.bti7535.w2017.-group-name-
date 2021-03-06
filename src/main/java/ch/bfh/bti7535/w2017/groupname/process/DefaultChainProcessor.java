package ch.bfh.bti7535.w2017.groupname.process;

/**
 * Ein Processor zum generieren von ARFF-Files (ohne anschliessende Validation)
 */
public class DefaultChainProcessor implements ChainProcessor{

    private int stepCount = 0;

    public void process(ProcessChainConfiguration chain) throws Exception {
        chain.init();
        applyFilters(0, (chain.getSteps().size()-1),chain);
    }

    /**
     * Wendet die gewünschten Filter an
     *
     * @param startStep
     * @param stopStep
     * @param chain
     * @throws Exception
     */
    private void applyFilters(int startStep, int stopStep, ProcessChainConfiguration chain) throws Exception {

        System.out.println("started process chain.");
        //System.out.println("instances = " + instances);

        stepCount = startStep;

        while (stepCount <= stopStep){
            ProcessStep step = chain.getSteps().get(stepCount);
            System.out.println("execute step "+(stepCount+1)+" of "+(stopStep-startStep+1));

            step.init();
            if (stepCount > 0 && chain.getSteps().get((stepCount-1)).getResultDataSet() != null){
                step.setInitDataSet(chain.getSteps().get((stepCount-1)).getResultDataSet());
            }
            step.process();


            stepCount++;
        }
    }
}
