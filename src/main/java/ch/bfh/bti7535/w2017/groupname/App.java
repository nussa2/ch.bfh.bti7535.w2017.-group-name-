package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.CVEvaluationChainProcessor;
import ch.bfh.bti7535.w2017.groupname.process.ChainConfigCVNB;
import ch.bfh.bti7535.w2017.groupname.process.ChainConfigVectorAttSelect;
import ch.bfh.bti7535.w2017.groupname.process.DefaultChainProcessor;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        String bla = "ChainConfigCVNB"; // TODO Mit args ersetzen
        switch (bla) {
            case "ChainConfigVectorAttSelect":
                ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();

                try {
                    new DefaultChainProcessor().process(vectorASChain);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "ChainConfigCVNB":
                ChainConfigCVNB filterChain = new ChainConfigCVNB();

                try {
                    CVEvaluationChainProcessor cvEvaluationChainProcessor = new CVEvaluationChainProcessor();
                    cvEvaluationChainProcessor.process(filterChain);
                    System.out.println("Error Rate: " + cvEvaluationChainProcessor.getValidationResultErrorRate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
