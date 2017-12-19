package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.*;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        String bla = "CSVImport"; // TODO Mit args ersetzen
        switch (bla) {
            case "ChainConfigVectorAttSelect":
                ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();

                try {
                    new DefaultChainProcessor().process(vectorASChain);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "CSVImport":
                ChainConfigCSVImport csvImport = new ChainConfigCSVImport();

                try {
                    new DefaultChainProcessor().process(csvImport);
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
