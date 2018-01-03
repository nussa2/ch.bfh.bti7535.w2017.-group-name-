package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.features.SentimentLexiconEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Stellt das SentimentLexicon gemäss http://www.wjh.harvard.edu/~inquirer/spreadsheet_guide.htm zur Verfügung
 */
public class SentimentLexiconProvider {

    /**
     *
     */
    public static final String INQUIRERBASIC_CSV = "inquirerbasic.CSV";

    /**
     * Gibt das SentimentLexicon zurück
     *
     * @return
     */
    public List<SentimentLexiconEntry> loadSentimentLexicon() {
        return generateSentimentList(parseCSV());
    }

    /**
     * Generiert das Lexicon aus dem CSV
     * @param spreadsheet
     * @return
     */
    private List<SentimentLexiconEntry> generateSentimentList(String[][] spreadsheet) {
        List<SentimentLexiconEntry> sentimentLexiconEntries = new ArrayList<>();

        for (int i = 1; i < spreadsheet.length; i++) {
            String word[] = spreadsheet[i];
            SentimentLexiconEntry sentimentLexiconEntry = new SentimentLexiconEntry(word[0]);

            for (int j = 1; j < word.length; j++) {
                if (word[j] != null && !word[j].isEmpty()) {
                    sentimentLexiconEntry.addSentiment(word[j]);
                }
            }
            sentimentLexiconEntries.add(sentimentLexiconEntry);
        }

        return sentimentLexiconEntries;
    }

    /**
     * Gibt ein CSV in der Form eines zweidimensionalen Arrays zurück
     * @return
     */
    private String[][] parseCSV() {

        String line;
        String cvsSplitBy = ";";
        String[][] spreadsheet = new String[12000][300];
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(INQUIRERBASIC_CSV).getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                String[] lineSplits = line.split(cvsSplitBy);
                lineSplits[0] = lineSplits[0].toLowerCase();

                for (int col = 0; col < lineSplits.length; col++) {
                    spreadsheet[lineCount][col] = lineSplits[col];
                }
                lineCount++;
            }
            System.out.println("number of lines: " + lineCount + 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return spreadsheet;
    }
}
