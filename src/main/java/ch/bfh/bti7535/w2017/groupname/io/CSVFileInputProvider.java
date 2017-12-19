package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.features.WordSentiment;
import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVFileInputProvider implements DataProvider, ProcessStep {

    private Instances dataSet;
    String destPath;

    public static final String CSV_FILE_ENDING = ".csv";

    @Override
    public DataProvider setSource(String source) {
        destPath = source;
        return this;
    }

    @Override
    public void init() {

    }

    @Override
    public void process() throws Exception {
        parseCSV();
        //dataSet = load();
    }

    @Override
    public void setInitDataSet(Instances dataSet) {

    }

    @Override
    public Instances getResultDataSet() {
        return dataSet;
    }

    private Instances load() throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setFieldSeparator(";");
        loader.setSource(new File(composeFileName()));
        Instances data = loader.getDataSet();

        return data;
    }

    private String composeFileName(){
        String filename = "inquirerbasic";
        return System.getProperty("user.home") + destPath +filename+ CSV_FILE_ENDING;
    }

    private List<WordSentiment> generateSentimentList(String[][] spreadsheet){
        List<WordSentiment> wordSentiments = new ArrayList<>();

        for (int i = 0; i < spreadsheet.length; i++) {
            String word[] = spreadsheet[i];
            for (int j = 0; j < word.length; j++) {
                WordSentiment wordSentiment = null;
                if (j == 0){
                    wordSentiment = new WordSentiment(word[0]);
                } else {
                    if (word[j] != null && word[j].isEmpty())
                    wordSentiment.addSentiment(word[j]);
                }
            }
            
        }

        return wordSentiments;
    }

    private String[][] parseCSV(){

        String csvFile = composeFileName();
        String line = "";
        String cvsSplitBy = ";";
        String[][] spreadsheet = new String[300][12000];
        //Map<String,Map<Integer,String>> parsedCSV = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int lineCount = 0;

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lineSplits = line.split(cvsSplitBy);
                System.out.println("number of splits: "+lineSplits.length);

                for (int col = 0; col < lineSplits.length;col++){
                   // parsedCSV.put()
                    spreadsheet[col][lineCount] = lineSplits[col];
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