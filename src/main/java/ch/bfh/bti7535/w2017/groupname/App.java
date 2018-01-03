package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.console.Console;
import ch.bfh.bti7535.w2017.groupname.console.Option;
import ch.bfh.bti7535.w2017.groupname.process.*;
import java.io.File;

/**
 * Hello world!
 */
public class App {

    /**
     * Hauptprogramm zum generieren der ARFF-Files im Temp-Ordner (PP_....) und zum Validieren Run-Configs sind im .idea-Ordner zur Verfügung gestellt
     *
     * @param args Die durchzuführende Aktion
     */
    public static void main(String[] args) {
        Console.clear();
        Console console = new Console();

        console.addOption(new Option("PP_SentimentLexiconCount", () -> {
            try {
                // Erfolgsquote: 63.4%
                ChainConfigSentimentLexicon sentimentLexiconChain = new ChainConfigSentimentLexicon();
                new DefaultChainProcessor().process(sentimentLexiconChain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        console.addOption(new Option("PP_SentimentLexiconPercent", () -> {
            try {
                // Erfolgsquote: 63.5%
                ChainConfigSentimentPercentLexicon sentimentLexiconPercentChain = new ChainConfigSentimentPercentLexicon();
                new DefaultChainProcessor().process(sentimentLexiconPercentChain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        console.addOption(new Option("PP_SentimentLexiconWeight", () -> {
            try {
                ChainConfigSentimentWeightLexicon sentimentLexiconWeightedChain = new ChainConfigSentimentWeightLexicon();
                new DefaultChainProcessor().process(sentimentLexiconWeightedChain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        console.addOption(new Option("PP_AttributeSelection", () -> {
            try {
                // Erfolgsquote: 78.3%
                ChainConfigVectorAttSelect vectorASChain = new ChainConfigVectorAttSelect();
                new DefaultChainProcessor().process(vectorASChain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        console.addOption(new Option("Validate", () -> {
            try {
                String filePath = Console.selectFile("/temp/movie-sa");
                
                ChainConfigCVNB filterChain = new ChainConfigCVNB();
                filterChain.setFilePath(filePath);
                CVEvaluationChainProcessor cvEvaluationChainProcessor = new CVEvaluationChainProcessor();
                cvEvaluationChainProcessor.process(filterChain);
                System.out.println("Error Rate: " + cvEvaluationChainProcessor.getValidationResultErrorRate());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        
        console.addOption(new Option("Exit", "Leave the program", () -> {
            System.exit(0);
        }));

        console.printAndWait();
    }

}
