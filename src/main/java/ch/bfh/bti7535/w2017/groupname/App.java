package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.*;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        String bla = "ChainConfigSentimentLexicon"; // TODO Mit args ersetzen
        try {
            switch (bla) {
                case "ChainConfigSentimentLexicon":
                    ChainConfigSentimentLexicon sentimentLexiconChain = new ChainConfigSentimentLexicon();
                    new DefaultChainProcessor().process(sentimentLexiconChain);

                    break;
                case "ChainConfigVectorAttSelect":
                    ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();
                    new DefaultChainProcessor().process(vectorASChain);

                    break;
                case "ChainConfigCVNB":

                    ChainConfigCVNB filterChain = new ChainConfigCVNB();
                    CVEvaluationChainProcessor cvEvaluationChainProcessor = new CVEvaluationChainProcessor();
                    cvEvaluationChainProcessor.process(filterChain);
                    System.out.println("Error Rate: " + cvEvaluationChainProcessor.getValidationResultErrorRate());

                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
