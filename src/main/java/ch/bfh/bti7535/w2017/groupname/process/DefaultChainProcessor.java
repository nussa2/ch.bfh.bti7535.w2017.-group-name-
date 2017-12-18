package ch.bfh.bti7535.w2017.groupname.process;

public class DefaultChainProcessor implements ChainProcessor{

    private int stepCount = 0;

    public void process(ProcessChainConfiguration chain) throws Exception {
        chain.init();
        applyFilters(0, (chain.getSteps().size()-1),chain);
    }

    private void applyFilters( int startStep, int stopStep, ProcessChainConfiguration chain) throws Exception {

        System.out.println("started process chain.");
        //System.out.println("instances = " + instances);

        stepCount = startStep;

        while (stepCount <= stopStep){
            ProcessStep step = chain.getSteps().get(stepCount);
            System.out.println("execute filter "+(stepCount+1)+" of "+(stopStep-startStep+1));

            step.init();
            if (stepCount > 0 && chain.getSteps().get((stepCount-1)).getResultDataSet() != null){
                step.setInitDataSet(chain.getSteps().get((stepCount-1)).getResultDataSet());
            }
            step.process();


            stepCount++;
        }

        //System.out.println("instances = " + instances);
    }
}
