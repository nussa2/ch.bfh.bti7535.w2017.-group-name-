package ch.bfh.bti7535.w2017.groupname.io;

import ch.bfh.bti7535.w2017.groupname.features.SentimentLexiconEntry;
import weka.core.DenseInstance;
import weka.core.Instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LexiconInstanceCreator {
    static List<SentimentLexiconEntry> lexicon;
    static Map<String, Integer> counter = new HashMap<>();
    static List<String> indexer = new ArrayList<>();
    //static String[] wantedAttributes = {"Positiv", "Negativ", "Pstv", "Affil", "Ngtv", "Hostile", "Strong", "Power", "Weak", "Submit", "Active", "Passive", "Pleasur", "Pain", "Feel", "Arousal", "EMOT", "Virtue", "Vice", "Ovrst", "Undrst", "Academ", "Doctrin", "Econ@", "Exch", "ECON", "Exprsv", "Legal", "Milit", "Polit@", "POLIT", "Relig", "Role", "COLL", "Work", "Ritual", "SocRel", "Race", "Kin@", "MALE", "Female", "Nonadlt", "HU", "ANI", "PLACE", "Social", "Region", "Route", "Aquatic", "Land", "Sky", "Object", "Tool", "Food", "Vehicle", "BldgPt", "ComnObj", "NatObj", "BodyPt", "ComForm", "COM", "Say", "Need", "Goal", "Try", "Means", "Persist", "Complet", "Fail", "NatrPro", "Begin", "Vary", "Increas", "Decreas", "Finish", "Stay", "Rise", "Exert", "Fetch", "Travel", "Fall", "Think", "Know", "Causal", "Ought", "Perceiv", "Compare", "Eval@", "EVAL", "Solve", "Abs@", "ABS", "Quality", "Quan", "NUMB", "ORD", "CARD", "FREQ", "DIST", "Time@", "TIME", "Space", "POS", "DIM", "Rel", "COLOR", "Self", "Our", "You", "Name", "Yes", "No", "Negate", "Intrj", "IAV", "DAV", "SV", "IPadj", "IndAdj", "PowGain", "PowLoss", "PowEnds", "PowAren", "PowCon", "PowCoop", "PowAuPt", "PowPt", "PowDoct", "PowAuth", "PowOth", "PowTot", "RcEthic", "RcRelig", "RcGain", "RcLoss", "RcEnds", "RcTot", "RspGain", "RspLoss", "RspOth", "RspTot", "AffGain", "AffLoss", "AffPt", "AffOth", "AffTot", "WltPt", "WltTran", "WltOth", "WltTot", "WlbGain", "WlbLoss", "WlbPhys", "WlbPsyc", "WlbPt", "WlbTot", "EnlGain", "EnlLoss", "EnlEnds", "EnlPt", "EnlOth", "EnlTot", "SklAsth", "SklPt", "SklOth", "SklTot", "TrnGain", "TrnLoss", "TranLw", "MeansLw", "EndsLw", "ArenaLw", "PtLw", "Nation", "Anomie", "NegAff", "PosAff", "SureLw", "If", "NotLw", "TimeSpc", "FormLw", "Othtags", "Defined"};
    static final String[] wantedAttributes = {"Positiv", "Negativ", "Hostile", "Strong", "Power", "Weak", "Submit", "Active", "Passive", "Pleasur", "Pain", "Feel", "Arousal"};
    // TODO Replace with good stopword-removal
    private final String[] thingsToRemove = {"\\\\'", "\\\\n", "\\)", "\\(", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "/", "-", ":", "\\?", "\\!"};

    // TODO Remove main method
    public static void main(String[] args) {
        getAndSetLexicon();
        addWantedAttributes();
        new LexiconInstanceCreator().createInstance("plot : two teen couples go to a church party , drink and then drive . \\nthey get into an accident . \\none of the guys dies , but his girlfriend continues to see him in her life , and has nightmares . \\nwhat\\'s the deal ? \\nwatch the movie and \\\" sorta \\\" find out . . . \\ncritique : a mind-fuck movie for the teen generation that touches on a very cool idea , but presents it in a very bad package . \\nwhich is what makes this review an even harder one to write , since i generally applaud films which attempt to break the mold , mess with your head and such ( lost highway & memento ) , but there are good and bad ways of making all types of films , and these folks just didn\\'t snag this one correctly . \\nthey seem to have taken this pretty neat concept , but executed it terribly . \\nso what are the problems with the movie ? \\nwell , its main problem is that it\\'s simply too jumbled . \\nit starts off \\\" normal \\\" but then downshifts into this \\\" fantasy \\\" world in which you , as an audience member , have no idea what\\'s going on . \\nthere are dreams , there are characters coming back from the dead , there are others who look like the dead , there are strange apparitions , there are disappearances , there are a looooot of chase scenes , there are tons of weird things that happen , and most of it is simply not explained . \\nnow i personally don\\'t mind trying to unravel a film every now and then , but when all it does is give me the same clue over and over again , i get kind of fed up after a while , which is this film\\'s biggest problem . \\nit\\'s obviously got this big secret to hide , but it seems to want to hide it completely until its final five minutes . \\nand do they make things entertaining , thrilling or even engaging , in the meantime ? \\nnot really . \\nthe sad part is that the arrow and i both dig on flicks like this , so we actually figured most of it out by the half-way point , so all of the strangeness after that did start to make a little bit of sense , but it still didn\\'t the make the film all that more entertaining . \\ni guess the bottom line with movies like this is that you should always make sure that the audience is \\\" into it \\\" even before they are given the secret password to enter your world of understanding . \\ni mean , showing melissa sagemiller running away from visions for about 20 minutes throughout the movie is just plain lazy ! ! \\nokay , we get it . . . there \\nare people chasing her and we don\\'t know who they are . \\ndo we really need to see it over and over again ? \\nhow about giving us different scenes offering further insight into all of the strangeness going down in the movie ? \\napparently , the studio took this film away from its director and chopped it up themselves , and it shows . \\nthere might\\'ve been a pretty decent teen mind-fuck movie in here somewhere , but i guess \\\" the suits \\\" decided that turning it into a music video with little edge , would make more sense . \\nthe actors are pretty good for the most part , although wes bentley just seemed to be playing the exact same character that he did in american beauty , only in a new neighborhood . \\nbut my biggest kudos go out to sagemiller , who holds her own throughout the entire film , and actually has you feeling her character\\'s unraveling . \\noverall , the film doesn\\'t stick because it doesn\\'t entertain , it\\'s confusing , it rarely excites and it feels pretty redundant for most of its runtime , despite a pretty cool ending and explanation to all of the craziness that came before it . \\noh , and by the way , this is not a horror or teen slasher flick . . . it\\'s \\njust packaged to look that way because someone is apparently assuming that the genre is still hot with the kids . \\nit also wrapped production two years ago and has been sitting on the shelves ever since . \\nwhatever . . . skip \\nit ! \\nwhere\\'s joblo coming from ? \\na nightmare of elm street 3 ( 7/10 ) - blair witch 2 ( 7/10 ) - the crow ( 9/10 ) - the crow : salvation ( 4/10 ) - lost highway ( 10/10 ) - memento ( 10/10 ) - the others ( 9/10 ) - stir of echoes ( 8/10 ) \\n", "neg");
    }

    public Instance createInstance(String text, String clazz) {
        List<String> wordlist = splitAndFilterText(text);
        for (String word : wordlist) {
            List<String> attributes = findWordAttributes(word);
            if (attributes == null) {
                continue;
            }
            for (String attribute : attributes) {
                if (counter.containsKey(attribute)) {
                    incrementCounter(attribute);
                }
            }
        }
        Map<String, Double> attributeValues = calculateAttributeValues(text);
        System.out.println("attributeValues = " + attributeValues);
        Instance inst = createInstance(clazz, attributeValues);
        System.out.println("inst = " + inst);
        return inst;
    }

    private void incrementCounter(String attribute) {
        Integer oldValue = counter.get(attribute);
        counter.put(attribute, ++oldValue);
    }

    private Map<String, Double> calculateAttributeValues(String text) {
        Map<String, Double> result = new HashMap<>();
        for (String s : counter.keySet()) {
            result.put(s, (double) counter.get(s) / text.split(" ").length);
        }
        return result;
    }

    private static void getAndSetLexicon() {
        CSVFileInputProvider csvFileInputProvider = new CSVFileInputProvider();
        String[][] parsResult = csvFileInputProvider.parseCSV();
        lexicon = csvFileInputProvider.generateSentimentList(parsResult);
    }

    private static void addWantedAttributes() {
        for (String attribute : wantedAttributes) {
            counter.put(attribute, 0);
            indexer.add(attribute);
            System.out.println("Add wanted Attribute: " + attribute);
        }
    }

    private List<String> findWordAttributes(String word) {
        List<SentimentLexiconEntry> collect = lexicon.stream() //
                .filter(s -> (s.getWord() != null && s.getWord() //
                        .equals(word)))//
                .collect(Collectors.toList());
        return (collect.size() == 1) ? collect.get(0).getSentiments() : null;
    }

    private List<String> splitAndFilterText(String text) {
        List<String> result = new ArrayList<>();
        String filteredText = filterText(text);
        for (String word : filteredText.split(" ")) {
            if (word.length() < 3) {
                continue;
            }
            result.add(word.toLowerCase());
            System.out.println(word.toLowerCase());
        }
        // TODO set class
        return result;
    }

    private String filterText(String text) {
        String newText = text;
        for (String thingToRemove : thingsToRemove) {
            newText = newText.replaceAll(thingToRemove, "");
        }
        return newText;
    }

    private Instance createInstance(String clazz, Map<String, Double> attributeValues) {
        Instance newInstance = new DenseInstance(attributeValues.size());
        for (String attribute : attributeValues.keySet()) {
            int index = indexer.indexOf(attribute);
            newInstance.setValue(index, attributeValues.get(attribute));
        }
        return newInstance;
    }

}
