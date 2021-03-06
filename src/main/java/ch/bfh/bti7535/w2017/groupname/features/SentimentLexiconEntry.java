package ch.bfh.bti7535.w2017.groupname.features;

import java.util.ArrayList;
import java.util.List;

/**
 * Definiert ein Wort mit den zugehörigen Sentiments gemäss http://www.wjh.harvard.edu/~inquirer/spreadsheet_guide.htm
 */
public class SentimentLexiconEntry {

    private String word;
    private List<String> sentiments = new ArrayList<>();

    /**
     *
     * @param word
     */
    public SentimentLexiconEntry(String word) {
        this.word = word;
    }

    /**
     *
     * @return
     */
    public List<String> getSentiments() {
        return sentiments;
    }

    /**
     *
     * @param sentiment
     */
    public void addSentiment(String sentiment){
        sentiments.add(sentiment);
    }

    /**
     *
     * @return
     */
    public String getWord() {
        return word;
    }
}
