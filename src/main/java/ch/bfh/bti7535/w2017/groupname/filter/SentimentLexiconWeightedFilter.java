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
 *
 * @author nalet
 */
public class SentimentLexiconWeightedFilter implements ProcessStep {

    private Instances dataSet;
    private Instances resultSet;
    private List<SentimentLexiconEntry> lexicon;
    private Map<String, Double> weightedAttributes = new HashMap<>();

    /**
     *
     */
    @Override
    public void init() {
        lexicon = new SentimentLexiconProvider().loadSentimentLexicon();
        //addWantedAttributes("Positiv", "Negativ");
        /*addWantedAttributes("Positiv", "Negativ", "Pstv", "Affil", "Ngtv", "Hostile", "Strong", "Power", "Weak", "Submit",
                "Active", "Passive", "Pleasur", "Pain", "Feel", "Arousal", "EMOT", "Virtue", "Vice", "Ovrst", "Undrst",
                "Academ", "Doctrin", "Yes", "No", "Quality");*/

        addAttr("Positiv", 1.5);
        addAttr("Negativ", 1.5);
        addAttr("Pstv", 0.0);
        addAttr("Affil", 0.0);
        addAttr("Ngtv", 0.0);
        addAttr("Hostile", 1.0);
        addAttr("Strong", 0.0);
        addAttr("Power", 0.0);
        addAttr("Weak", 0.0);
        addAttr("Submit", 0.0);
        addAttr("Active", 0.0);
        addAttr("Passive", 0.0);
        addAttr("Pleasur", 1.0);
        addAttr("Pain", 1.0);
        addAttr("Feel", 0.0);
        addAttr("Arousal", 0.0);
        addAttr("EMOT", 0.0);
        addAttr("Virtue", 0.0);
        addAttr("Vice", 0.0);
        addAttr("Ovrst", 0.0);
        addAttr("Undrst", 0.0);
        addAttr("Academ", 0.0);
        addAttr("Doctrin", 0.0);
        addAttr("Econ@", 0.0);
        addAttr("Exch", 0.0);
        addAttr("ECON", 0.0);
        addAttr("Exprsv", 0.0);
        addAttr("Legal", 0.0);
        addAttr("Milit", 0.0);
        addAttr("Polit@", 0.0);
        addAttr("POLIT", 0.0);
        addAttr("Relig", 0.0);
        addAttr("Role", 0.0);
        addAttr("COLL", 0.0);
        addAttr("Work", 0.0);
        addAttr("Ritual", 0.0);
        addAttr("SocRel", 0.0);
        addAttr("Race", 0.0);
        addAttr("Kin@", 0.0);
        addAttr("MALE", 0.0);
        addAttr("Female", 0.0);
        addAttr("Nonadlt", 0.0);
        addAttr("HU", 0.0);
        addAttr("ANI", 0.0);
        addAttr("PLACE", 0.0);
        addAttr("Social", 0.0);
        addAttr("Region", 0.0);
        addAttr("Route", 0.0);
        addAttr("Aquatic", 0.0);
        addAttr("Land", 0.0);
        addAttr("Sky", 0.0);
        addAttr("Object", 0.0);
        addAttr("Tool", 0.0);
        addAttr("Food", 0.0);
        addAttr("Vehicle", 0.0);
        addAttr("BldgPt", 0.0);
        addAttr("ComnObj", 0.0);
        addAttr("NatObj", 0.0);
        addAttr("BodyPt", 0.0);
        addAttr("ComForm", 0.0);
        addAttr("COM", 0.0);
        addAttr("Say", 0.0);
        addAttr("Need", 0.0);
        addAttr("Goal", 0.0);
        addAttr("Try", 0.0);
        addAttr("Means", 0.0);
        addAttr("Persist", 0.0);
        addAttr("Complet", 0.0);
        addAttr("Fail", 0.0);
        addAttr("NatrPro", 0.0);
        addAttr("Begin", 0.0);
        addAttr("Vary", 0.0);
        addAttr("Increas", 0.0);
        addAttr("Decreas", 0.0);
        addAttr("Finish", 0.0);
        addAttr("Stay", 0.0);
        addAttr("Rise", 0.0);
        addAttr("Exert", 0.0);
        addAttr("Fetch", 0.0);
        addAttr("Travel", 0.0);
        addAttr("Fall", 0.0);
        addAttr("Think", 0.0);
        addAttr("Know", 0.0);
        addAttr("Causal", 0.0);
        addAttr("Ought", 0.0);
        addAttr("Perceiv", 0.0);
        addAttr("Compare", 0.0);
        addAttr("Eval@", 0.0);
        addAttr("EVAL", 0.0);
        addAttr("Solve", 0.0);
        addAttr("Abs@", 0.0);
        addAttr("ABS", 0.0);
        addAttr("Quality", 0.0);
        addAttr("Quan", 0.0);
        addAttr("NUMB", 0.0);
        addAttr("ORD", 0.0);
        addAttr("CARD", 0.0);
        addAttr("FREQ", 0.0);
        addAttr("DIST", 0.0);
        addAttr("Time@", 0.0);
        addAttr("TIME", 0.0);
        addAttr("Space", 0.0);
        addAttr("POS", 0.0);
        addAttr("DIM", 0.0);
        addAttr("Rel", 0.0);
        addAttr("COLOR", 0.0);
        addAttr("Self", 0.0);
        addAttr("Our", 0.0);
        addAttr("You", 0.0);
        addAttr("Name", 0.0);
        addAttr("Yes", 0.0);
        addAttr("No", 0.0);
        addAttr("Negate", 0.0);
        addAttr("Intrj", 0.0);
        addAttr("IAV", 0.0);
        addAttr("DAV", 0.0);
        addAttr("SV", 0.0);
        addAttr("IPadj", 0.0);
        addAttr("IndAdj", 0.0);
        addAttr("PowGain", 0.0);
        addAttr("PowLoss", 0.0);
        addAttr("PowEnds", 0.0);
        addAttr("PowAren", 0.0);
        addAttr("PowCon", 0.0);
        addAttr("PowCoop", 0.0);
        addAttr("PowAuPt", 0.0);
        addAttr("PowPt", 0.0);
        addAttr("PowDoct", 0.0);
        addAttr("PowAuth", 0.0);
        addAttr("PowOth", 0.0);
        addAttr("PowTot", 0.0);
        addAttr("RcEthic", 0.0);
        addAttr("RcRelig", 0.0);
        addAttr("RcGain", 0.0);
        addAttr("RcLoss", 0.0);
        addAttr("RcEnds", 0.0);
        addAttr("RcTot", 0.0);
        addAttr("RspGain", 0.0);
        addAttr("RspLoss", 0.0);
        addAttr("RspOth", 0.0);
        addAttr("RspTot", 0.0);
        addAttr("AffGain", 0.0);
        addAttr("AffLoss", 0.0);
        addAttr("AffPt", 0.0);
        addAttr("AffOth", 0.0);
        addAttr("AffTot", 0.0);
        addAttr("WltPt", 0.0);
        addAttr("WltTran", 0.0);
        addAttr("WltOth", 0.0);
        addAttr("WltTot", 0.0);
        addAttr("WlbGain", 0.0);
        addAttr("WlbLoss", 0.0);
        addAttr("WlbPhys", 0.0);
        addAttr("WlbPsyc", 0.0);
        addAttr("WlbPt", 0.0);
        addAttr("WlbTot", 0.0);
        addAttr("EnlGain", 0.0);
        addAttr("EnlLoss", 0.0);
        addAttr("EnlEnds", 0.0);
        addAttr("EnlPt", 0.0);
        addAttr("EnlOth", 0.0);
        addAttr("EnlTot", 0.0);
        addAttr("SklAsth", 0.0);
        addAttr("SklPt", 0.0);
        addAttr("SklOth", 0.0);
        addAttr("SklTot", 0.0);
        addAttr("TrnGain", 0.0);
        addAttr("TrnLoss", 0.0);
        addAttr("TranLw", 0.0);
        addAttr("MeansLw", 0.0);
        addAttr("EndsLw", 0.0);
        addAttr("ArenaLw", 0.0);
        addAttr("PtLw", 0.0);
        addAttr("Nation", 0.0);
        addAttr("Anomie", 0.0);
        addAttr("NegAff", 0.0);
        addAttr("PosAff", 0.0);
        addAttr("SureLw", 0.0);
        addAttr("If", 0.0);
        addAttr("NotLw", 0.0);
        addAttr("TimeSpc", 0.0);
        addAttr("FormLw", 0.0);
        addAttr("Othtags", 0.0);
        addAttr("Defined", 0.0);
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void process() throws Exception {

        Enumeration<Attribute> attributes = dataSet.enumerateAttributes();
        List<String> attributeList = new ArrayList<>();
        while (attributes.hasMoreElements()){
            String attribute = attributes.nextElement().name();
            attributeList.add(attribute);
        }

        InstanceBuilder instanceBuilder = initInstanceBuilder(attributeList);

        Enumeration<Instance> instances = dataSet.enumerateInstances();
        while (instances.hasMoreElements()) {
            Instance instance = instances.nextElement();
            Map<String,Double> wordlist = extractWordlist(instance);

            Map<String,Double> resultList = weightWordlistAgainstSentimentLexicon(wordlist);

            List<Object> values = new ArrayList<>();
            for (String attribute: attributeList) {
                Double weight = resultList.get(attribute);
                if (weight != null){
                    values.add(weight);
                } else {
                    values.add(0.0);
                }
            }
            //values.addAll(resultList.values());
            values.add(dataSet.classAttribute().value((int) instance.classValue()));

            instanceBuilder.addData(values);

        }

        Instances newInstances = instanceBuilder.build();
        resultSet = newInstances;
    }

    private InstanceBuilder initInstanceBuilder(List<String> attributes){
        InstanceBuilder instanceBuilder = new InstanceBuilder();
        try {
            instanceBuilder.setRelationName("sentiment_lexicon_based_relation");
            for (String attribute:attributes) {
                instanceBuilder.addNumericAttribute(attribute);
            }
            instanceBuilder.addNominalAttribute("review_class", "ndef", "neg", "pos");
            instanceBuilder.initClassIndex();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instanceBuilder;
    }

    private Map<String,Double> extractWordlist(Instance instance){
        //System.out.println("Instance to check against lexicon: "+instance);
        Map<String,Double> wordlist = new HashMap<>();
        for (int i = 1; i < instance.numValues(); i++) {
            int attrIndex = instance.index(i);
            Attribute attribute = dataSet.attribute(attrIndex);
            String name = attribute.name();
            double value = instance.value(attrIndex);
            //System.out.println("attribute: "+name+" value: "+value);
            wordlist.put(name,value);
        }
        return wordlist;
    }

    /**
     *
     * @param dataSet
     */
    @Override
    public void setInitDataSet(Instances dataSet) {
        this.dataSet = dataSet;
    }

    /**
     *
     * @return
     */
    @Override
    public Instances getResultDataSet() {
        return resultSet;
    }

    private Map<String, Double> weightWordlistAgainstSentimentLexicon(Map<String,Double> wordlist) {
        Map<String, Double> weightedWordlist = new HashMap<>();

        for (String word : wordlist.keySet()) {
            weightedWordlist.put(word, calculateWeightedWordCount(word,wordlist.get(word)));
        }

        return weightedWordlist;
    }

    private double calculateWeightedWordCount(String word, double wordCount){
        if (wordCount == 0.0){
            return 0.0;
        }
        double weightedWordCount = 0.0;

        List<String> attributes = findWordAttributes(word);
        if (attributes == null) {
            return 0.0;
        }

        for (String attribute : attributes) {
            double attributeWeight = getAttrWeight(attribute);
                if (attributeWeight > 0.0){
                    if (weightedWordCount == 0.0){
                        weightedWordCount = wordCount;
                    }
                    weightedWordCount = weightedWordCount*attributeWeight;
                }
                //System.out.println("word "+word+" has new weight "+attributeWeight);
            }

        return weightedWordCount;
    }

    private void addAttr(String attribute, Double weight) {
        this.weightedAttributes.put(attribute, weight);
        //System.out.println("Add weighted attribute: " + attribute + ": "+weight);
    }

    private double getAttrWeight(String attribute) {
        Double attributeWeight = weightedAttributes.get(attribute);
        if (attributeWeight == null) {
            return 0.0;
        }
        return attributeWeight;
    }

    private List<String> findWordAttributes(String word) {
        List<SentimentLexiconEntry> collect = lexicon.stream() //
                .filter(s -> (s.getWord() != null && s.getWord() //
                        .equals(word)))//
                .collect(Collectors.toList());
        return (collect.size() == 1) ? collect.get(0).getSentiments() : null;
    }

}
