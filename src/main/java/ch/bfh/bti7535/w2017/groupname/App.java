package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.CVEvaluationChainProcessor;
import ch.bfh.bti7535.w2017.groupname.process.ChainConfigCVNB;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {

        /*ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();

        try {
            new DefaultChainProcessor().process(vectorASChain);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        ChainConfigCVNB filterChain = new ChainConfigCVNB();

        try {
            new CVEvaluationChainProcessor().process(filterChain);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
