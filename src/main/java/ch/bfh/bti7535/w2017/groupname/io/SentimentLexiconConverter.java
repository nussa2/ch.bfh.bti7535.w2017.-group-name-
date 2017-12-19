package ch.bfh.bti7535.w2017.groupname.io;

import weka.core.Instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// todo design
public class SentimentLexiconConverter {
    static Map<String, List<String>> lexicon = new HashMap<>();
    static Map<String, Integer> counter = new HashMap<>();

    static {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Love");
        lexicon.put("MySpecialWord", list1);

        counter.put("Love", 0); // Add for every relevant Attribute

    }

    public static void main(String[] args) {

        new SentimentLexiconConverter().neueIdee("MySpecialWord I like Food", "pos");
    }

    public void neueIdee(String text, String clazz) {
        List<String> wordlist = splitAndFilterText(text);
        for (String word : wordlist) {
            List<String> attributes = lexicon.get(word);
            if (attributes == null) {
                continue;
            }
            for (String attribute : attributes) {
                if (counter.containsKey(attribute)) {
                    Integer oldValue = counter.get(attribute);
                    counter.put(attribute, ++oldValue);
                }
            }
        }
        Map<String, Double> attributeValues = new HashMap<>();
        for (String s : counter.keySet()) {
            attributeValues.put(s, (double) counter.get(s) / text.split(" ").length);
        }
        System.out.println("attributeValues = " + attributeValues);
        Instance inst = createInstance(clazz, attributeValues);
        // aus diesen werten machen wir eine Instanz und speichern diese

    }

    private List<String> splitAndFilterText(String text) {
        List<String> result = new ArrayList<>();
        // TODO Satzzeichen filtern, stopwords filtern usw
        for (String word : text.split(" ")) {
            result.add(word);
        }
        return result;
    }

    private Instance createInstance(String clazz, Map<String, Double> attributeValues) {
        return null;
    }
/*

    public static final String ATTRIBUTE_PREFIX = "Attribute_";
    private Map<String, Integer> attributeIndexMap = new HashMap<>();

    public static void main(String[] args) {
        new SentimentLexiconConverter().muesmenobenenne();
    }

    public void muesmenobenenne() {
        SentimentLexiconEntry entry = new SentimentLexiconEntry("bla");
        entry.addSentiment("Love");

        // TODO auslagern
        List<String> allPossibleAttributes = new ArrayList<>();
        allPossibleAttributes.add("Love");
        allPossibleAttributes.add("Hate");
        ArrayList<Attribute> attributes = createAttributesAndFillMap(allPossibleAttributes);

        Instances dataset = new Instances("Dataset", attributes, 1);

        Instance newInstance = new DenseInstance(attributes.size());
        for (String attribute : entry.getSentiments()) {
            Integer index = attributeIndexMap.get(ATTRIBUTE_PREFIX + attribute);
            newInstance.setValue(index, 1.0);
        }
        dataset.add(newInstance);
    }

    private ArrayList<Attribute> createAttributesAndFillMap(List<String> entries) {
        ArrayList<Attribute> atts = new ArrayList<Attribute>();
        for (int i = 0; i < entries.size(); i++) {
            String attributeName = ATTRIBUTE_PREFIX + entries.get(i);
            atts.add(new Attribute(attributeName, i));
            attributeIndexMap.put(attributeName, i);
        }
        return atts;
    }*/
}
