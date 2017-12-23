package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.features.SentimentLexiconEntry;
import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SentimentLexiconProvider {

    public List<SentimentLexiconEntry> loadSentimentLexicon(){
        return generateSentimentList(parseCSV());
    }

    private List<SentimentLexiconEntry> generateSentimentList(String[][] spreadsheet) {
        List<SentimentLexiconEntry> sentimentLexiconEntries = new ArrayList<>();

        for (int i = 1; i < spreadsheet.length; i++) {
            String word[] = spreadsheet[i];
            SentimentLexiconEntry sentimentLexiconEntry = new SentimentLexiconEntry(word[0]);

            for (int j = 1; j < word.length; j++) {
                if (word[j] != null && !word[j].isEmpty()){
                    sentimentLexiconEntry.addSentiment(word[j]);
                }
            }
            sentimentLexiconEntries.add(sentimentLexiconEntry);
        }

        return sentimentLexiconEntries;
    }

    private String[][] parseCSV() {

        String line = "";
        String cvsSplitBy = ";";
        String[][] spreadsheet = new String[12000][300];
        //Map<String,Map<Integer,String>> parsedCSV = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("inquirerbasic.CSV").getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int lineCount = 0;

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lineSplits = line.split(cvsSplitBy);
                //System.out.println("number of splits: "+lineSplits.length);
                lineSplits[0] = lineSplits[0].toLowerCase();

                for (int col = 0; col < lineSplits.length;col++){
                   // parsedCSV.put()
                    spreadsheet[lineCount][col] = lineSplits[col];
                }
                lineCount++;
            }
            System.out.println("number of lines: "+lineCount+1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(Arrays.toString(spreadsheet));
        return spreadsheet;
    }
}
