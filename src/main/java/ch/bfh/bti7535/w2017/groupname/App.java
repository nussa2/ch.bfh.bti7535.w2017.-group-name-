package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.*;

/**
 * Hello world!
 */
public class App {

    /**
     * Hauptprogramm zum generieren der ARFF-Files im Temp-Ordner (PP_....) und zum Validieren
     * Run-Configs sind im .idea-Ordner zur Verfügung gestellt
     *
     * @param args Die durchzuführende Aktion
     */
    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "PP_SentimentLexiconCount":
                    // Erfolgsquote: 63.4%
                    ChainConfigSentimentLexicon sentimentLexiconChain = new ChainConfigSentimentLexicon();
                    new DefaultChainProcessor().process(sentimentLexiconChain);
                    break;
                case "PP_SentimentLexiconPercent":
                    // Erfolgsquote: 63.5%
                    ChainConfigSentimentPercentLexicon sentimentLexiconPercentChain = new ChainConfigSentimentPercentLexicon();
                    new DefaultChainProcessor().process(sentimentLexiconPercentChain);
                    break;
                case "PP_AttributeSelection":
                    // Erfolgsquote: 78.3%
                    ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();
                    new DefaultChainProcessor().process(vectorASChain);
                    break;
                case "Validate":
                    /* Hier muss man noch ch.bfh.bti7535.w2017.groupname.process.ChainConfigCVNB.addStep
                        anpassen, damit es das richtige input-file nimmt
                     */
                    ChainConfigCVNB filterChain = new ChainConfigCVNB();
                    CVEvaluationChainProcessor cvEvaluationChainProcessor = new CVEvaluationChainProcessor();
                    cvEvaluationChainProcessor.process(filterChain);
                    System.out.println("Error Rate: " + cvEvaluationChainProcessor.getValidationResultErrorRate());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
