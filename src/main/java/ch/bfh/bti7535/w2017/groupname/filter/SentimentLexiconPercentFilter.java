package ch.bfh.bti7535.w2017.groupname.filter;

import ch.bfh.bti7535.w2017.groupname.features.SentimentLexiconEntry;
import ch.bfh.bti7535.w2017.groupname.io.InstanceBuilder;
import ch.bfh.bti7535.w2017.groupname.io.SentimentLexiconProvider;
import ch.bfh.bti7535.w2017.groupname.process.ProcessStep;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Filter zum Klassifizieren mit dem General Inquirer von Harward
 * Die Counts sind aber Prozentual auf die Länge des Textes herabgebrochen
 */
public class SentimentLexiconPercentFilter implements ProcessStep {

    private Instances dataSet;
    private Instances resultSet;
    private List<SentimentLexiconEntry> lexicon;
    private List<String> wantedAttributes = new ArrayList<>();

    @Override
    public void init() {
        lexicon = new SentimentLexiconProvider().loadSentimentLexicon();
        // Zu berücksichtigende Attribute auswählen
        addWantedAttributes("Positiv", "Negativ", "Hostile", "Pleasur", "Pain");
        /*
        Alle Möglichen Spalten-Bezeichnungen
        addWantedAttributes("Positiv", "Negativ", "Pstv", "Affil", "Ngtv", "Hostile", "Strong", "Power", "Weak", "Submit",
                "Active", "Passive", "Pleasur", "Pain", "Feel", "Arousal", "EMOT", "Virtue", "Vice", "Ovrst", "Undrst",
                "Academ", "Doctrin", "Econ@", "Exch", "ECON", "Exprsv", "Legal", "Milit", "Polit@", "POLIT", "Relig",
                "Role", "COLL", "Work", "Ritual", "SocRel", "Race", "Kin@", "MALE", "Female", "Nonadlt", "HU", "ANI",
                "PLACE", "Social", "Region", "Route", "Aquatic", "Land", "Sky", "Object", "Tool", "Food", "Vehicle",
                "BldgPt", "ComnObj", "NatObj", "BodyPt", "ComForm", "COM", "Say", "Need", "Goal", "Try", "Means",
                "Persist", "Complet", "Fail", "NatrPro", "Begin", "Vary", "Increas", "Decreas", "Finish", "Stay",
                "Rise", "Exert", "Fetch", "Travel", "Fall", "Think", "Know", "Causal", "Ought", "Perceiv", "Compare",
                "Eval@", "EVAL", "Solve", "Abs@", "ABS", "Quality", "Quan", "NUMB", "ORD", "CARD", "FREQ", "DIST",
                "Time@", "TIME", "Space", "POS", "DIM", "Rel", "COLOR", "Self", "Our", "You", "Name", "Yes", "No",
                "Negate", "Intrj", "IAV", "DAV", "SV", "IPadj", "IndAdj", "PowGain", "PowLoss", "PowEnds", "PowAren",
                "PowCon", "PowCoop", "PowAuPt", "PowPt", "PowDoct", "PowAuth", "PowOth", "PowTot", "RcEthic", "RcRelig",
                "RcGain", "RcLoss", "RcEnds", "RcTot", "RspGain", "RspLoss", "RspOth", "RspTot", "AffGain", "AffLoss",
                "AffPt", "AffOth", "AffTot", "WltPt", "WltTran", "WltOth", "WltTot", "WlbGain", "WlbLoss", "WlbPhys",
                "WlbPsyc", "WlbPt", "WlbTot", "EnlGain", "EnlLoss", "EnlEnds", "EnlPt", "EnlOth", "EnlTot", "SklAsth",
                "SklPt", "SklOth", "SklTot", "TrnGain", "TrnLoss", "TranLw", "MeansLw", "EndsLw", "ArenaLw", "PtLw",
                "Nation", "Anomie", "NegAff", "PosAff", "SureLw", "If", "NotLw", "TimeSpc", "FormLw", "Othtags", "Defined");*/
    }

    @Override
    public void process() throws Exception {
        InstanceBuilder instanceBuilder = initInstanceBuilder();

        Enumeration<Instance> instances = dataSet.enumerateInstances();

        while (instances.hasMoreElements()) {
            Instance instance = instances.nextElement();
            Map<String, Double> wordlist = extractWordlist(instance);
            Map<String, Double> resultList = checkWordlistAgainstSentimentLexicon(wordlist);
            List<Object> values = new ArrayList<>();
            values.addAll(resultList.values());
            values.add(dataSet.classAttribute().value((int) instance.classValue()));

            instanceBuilder.addData(values);

        }

        Instances newInstances = instanceBuilder.build();
        resultSet = newInstances;
    }


    /**
     * Erstellt einen InstanceBuilder
     *
     * @return
     */
    private InstanceBuilder initInstanceBuilder() {
        InstanceBuilder instanceBuilder = new InstanceBuilder();
        try {
            instanceBuilder.setRelationName("sentiment_lexicon_based_relation");
            for (String attribute : wantedAttributes) {
                instanceBuilder.addNumericAttribute(attribute);
            }
            instanceBuilder.addNominalAttribute("review_class", "ndef", "neg", "pos");
            instanceBuilder.initClassIndex();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instanceBuilder;
    }


    /**
     * Extrahiert aus einer Instance die Wordlist
     *
     * @param instance
     * @return
     */
    private Map<String, Double> extractWordlist(Instance instance) {
        //System.out.println("Instance to check against lexicon: "+instance);
        //instance.value
        Map<String, Double> wordlist = new HashMap<>();
        for (int i = 1; i < instance.numValues(); i++) {
            int attrIndex = instance.index(i);
            Attribute attribute = dataSet.attribute(attrIndex);
            String name = attribute.name();
            double value = instance.value(attrIndex);
            //System.out.println("attribute: "+name+" value: "+value);
            wordlist.put(name, value);
        }
        return wordlist;
    }

    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public Instances getResultDataSet() {
        return resultSet;
    }


    /**
     * Liefert einen prozentualen Counter der Attribute zurück
     * @param wordlist
     * @return
     */
    private Map<String, Double> checkWordlistAgainstSentimentLexicon(Map<String, Double> wordlist) {
        Map<String, Double> counter = new HashMap<>();
        //List<String> indexer = new ArrayList<>();

        for (String attribute : wantedAttributes) {
            counter.put(attribute, 0.0);
            //indexer.add(attribute);
        }

        for (String word : wordlist.keySet()) {
            List<String> attributes = findWordAttributes(word);
            if (attributes == null) {
                continue;
            }
            for (String attribute : attributes) {
                if (counter.containsKey(attribute)) {
                    Double oldValue = counter.get(attribute);
                    counter.put(attribute, oldValue + wordlist.get(word));
                }
            }
        }
        Map<String, Double> attributeValues = calculateAttributeValues(counter);

        return attributeValues;
    }

    /**
     * Abstrahiert die Textlänge im Bezug auf die Anzahl des Auftretens des Wortes
     * @param counter
     * @return
     */
    private Map<String, Double> calculateAttributeValues(Map<String, Double> counter) {
        Map<String, Double> result = new HashMap<>();
        Double count = calculateWordCount(counter);
        for (String s : counter.keySet()) {
            result.put(s, (counter.get(s) / count));
        }
        return result;
    }

    /**
     * Zählt die Anzahl der Sentiments im Text
     * @param counter
     * @return
     */
    private Double calculateWordCount(Map<String, Double> counter) {
        Double count = new Double(0);
        for (Double c : counter.values()) {
            count += c;
        }
        return count;
    }

    /**
     * Zu berücksichtigendes Attribut hinzufügen
     * @param wantedAttributes
     */
    private void addWantedAttributes(String... wantedAttributes) {
        for (String attribute : wantedAttributes) {
            this.wantedAttributes.add(attribute);
            System.out.println("Add wanted Attribute: " + attribute);
        }
    }

    /**
     * liefert die Word-Sentiments für ein Wort aus dem Lexicon
     * @param word
     * @return
     */
    private List<String> findWordAttributes(String word) {
        List<SentimentLexiconEntry> collect = lexicon.stream() //
                .filter(s -> (s.getWord() != null && s.getWord() //
                        .equals(word)))//
                .collect(Collectors.toList());
        return (collect.size() == 1) ? collect.get(0).getSentiments() : null;
    }

}
