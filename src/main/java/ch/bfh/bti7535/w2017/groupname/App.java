package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.*;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "PP_SentimentLexiconPercent":
                    ChainConfigSentimentPercentLexicon sentimentLexiconPercentChain = new ChainConfigSentimentPercentLexicon();
                    new DefaultChainProcessor().process(sentimentLexiconPercentChain);
                    break;
                case "PP_SentimentLexiconCount":
                    ChainConfigSentimentLexicon sentimentLexiconChain = new ChainConfigSentimentLexicon();
                    new DefaultChainProcessor().process(sentimentLexiconChain);
                    break;
                case "PP_AttributeSelection":
                    ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();
                    new DefaultChainProcessor().process(vectorASChain);
                    break;
                case "Validate":
                    // Hier muss man noch ch.bfh.bti7535.w2017.groupname.process.ChainConfigCVNB.addStep anpassen
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
