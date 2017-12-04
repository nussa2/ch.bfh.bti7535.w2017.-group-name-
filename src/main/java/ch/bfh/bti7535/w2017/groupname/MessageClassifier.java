package ch.bfh.bti7535.w2017.groupname;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Java program for classifying short text messages into two classes 'miss'
 * and 'hit'.
 * <p/>
 * See also wiki article <a href="http://weka.wiki.sourceforge.net/MessageClassifier">MessageClassifier</a>.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @version $Revision$
 */
public class MessageClassifier
        implements Serializable {

    /**
     * for serialization.
     */
    private static final long serialVersionUID = -123455813150452885L;
    public static final String MESSAGECLASSIFIER_MODEL = "messageclassifier.model";

    /**
     * The training data gathered so far.
     */
    private Instances m_Data = null;

    /**
     * The filter used to generate the word counts.
     */
    private StringToWordVector m_Filter = new StringToWordVector();

    /**
     * The actual classifier.
     */
    private Classifier m_Classifier = new J48();

    /**
     * Whether the model is up to date.
     */
    private boolean m_UpToDate;

    /**
     * Constructs empty training dataset.
     */
    public MessageClassifier() {
        String nameOfDataset = "MessageClassificationProblem";

        // Create vector of attributes.
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(2);

        // Add attribute for holding messages.
        attributes.add(new Attribute("Message", (ArrayList<String>) null));

        // Add class attribute.
        ArrayList<String> classValues = new ArrayList<String>(2);
        classValues.add("miss");
        classValues.add("hit");
        attributes.add(new Attribute("Class", classValues));

        // Create dataset with initial capacity of 100, and set index of class.
        m_Data = new Instances(nameOfDataset, attributes, 100);
        m_Data.setClassIndex(m_Data.numAttributes() - 1);
    }

    /**
     * Updates model using the given training message.
     *
     * @param message    the message content
     * @param classValue the class label
     */
    public void updateData(String message, String classValue) {
        // Make message into instance.
        Instance instance = makeInstance(message, m_Data);

        // Set class value for instance.
        instance.setClassValue(classValue);

        // Add instance to training data.
        m_Data.add(instance);

        m_UpToDate = false;
    }

    /**
     * Classifies a given message.
     *
     * @param message the message content
     * @throws Exception if classification fails
     */
    public void classifyMessage(String message) throws Exception {
        // Check whether classifier has been built.
        if (m_Data.numInstances() == 0)
            throw new Exception("No classifier available.");

        // Check whether classifier and filter are up to date.
        if (!m_UpToDate) {
            // Initialize filter and tell it about the input format.
            m_Filter.setInputFormat(m_Data);

            // Generate word counts from the training data.
            Instances filteredData = Filter.useFilter(m_Data, m_Filter);

            // Rebuild classifier.
            m_Classifier.buildClassifier(filteredData);

            m_UpToDate = true;
        }

        // Make separate little test set so that message
        // does not get added to string attribute in m_Data.
        Instances testset = m_Data.stringFreeStructure();

        // Make message into test instance.
        Instance instance = makeInstance(message, testset);

        // Filter instance.
        m_Filter.input(instance);
        Instance filteredInstance = m_Filter.output();

        // Get index of predicted class value.
        double predicted = m_Classifier.classifyInstance(filteredInstance);

        // Output class value.
        System.err.println("Message classified as : " +
                m_Data.classAttribute().value((int) predicted));
    }

    /**
     * Method that converts a text message into an instance.
     *
     * @param text the message content to convert
     * @param data the header information
     * @return the generated Instance
     */
    private Instance makeInstance(String text, Instances data) {
        // Create instance of length two.
        Instance instance = new DenseInstance(2);

        // Set value for message attribute
        Attribute messageAtt = data.attribute("Message");
        instance.setValue(messageAtt, messageAtt.addStringValue(text));

        // Give instance access to attribute information from the dataset.
        instance.setDataset(data);

        return instance;
    }

    /**
     * Main method. The following parameters are recognized:
     * <ul>
     * <li>
     * <code>-m messagefile</code><br/>
     * Points to the file containing the message to classify or use for
     * updating the model.
     * </li>
     * <li>
     * <code>-c classlabel</code><br/>
     * The class label of the message if model is to be updated. Omit for
     * classification of a message.
     * </li>
     * <li>
     * <code>-t modelfile</code><br/>
     * The file containing the model. If it doesn't exist, it will be
     * created automatically.
     * </li>
     * </ul>
     *
     * @param args the commandline options
     */
    public static void main(String[] args) throws Exception {
        read();
        test();

    }

    private static void test() throws Exception {
        MessageClassifier messageClassifier = getMessageClassifier();
        messageClassifier.classifyMessage("what a great film . \n" +
                "what a stunning , touching , heart-wrenching , heart-warming , life-affirming , miraculous film . \n" +
                ">from its opening moments , _life is beautiful_ walks that fine line between serious tragedy and uplifting comedy . \n" +
                "a car with no brakes speeds through the town , and guido ( roberto benigni ) motions people to move out of the way . \n" +
                "they mistake him to be the fascist president , traveling to their town that day . \n" +
                "heil ! \n" +
                "heil ! \n" +
                "huh ? \n" +
                "shortly thereafter , the president drives through , and people stare , with blank faces . \n" +
                "this is standard , but somehow fresh and postmodern stuff : the clown who is also a jew , triumphs over the anti-semitic society in which he = lives . \n" +
                "granted , _the great dictator_ comes to mind--charlie chaplin+s almost masterpiece . \n" +
                "i think lib ( which benigni wrote and directed ) trumps gd in one essential point : chaplin+s jewish barber gives a great speech at the climax , but to do so , he had to break completely from character . \n" +
                "benigni is given a similar situation : he is mistaken as a fascist dignitary , and must explain to a classroom filled with schoolchildren = on how scientists have concluded that aryans are the superior race . \n" +
                "benigni+s guido stays in character , and delivers the speech , keeping = the subject matter intact while showing the absurdity of its concept . \n" +
                "it = is one of the great satirical scenes in modern cinema . \n" +
                "there are many other scenes , especially in the first half , which are bright and loopy and funny and silly . \n" +
                "slapstick reigns , and although the film is subtitled , there was no doubt that a universal language was being conveyed . \n" +
                "and although it+s focus is on the courtship of guido = to dora ( played by nicoletta braschi , benigni+s real life wife ) , a silent undercurrent creeps in . \n" +
                "this is clearly a racist society , evidenced by the aforementioned scene and others , and it will soon affect him and = his family . \n" +
                "the second half of the film takes place five years later , in an unnamed concentration camp , where guido , dora ( by her insistence ) , and their five-year old son , giosu=e9 ( giorgio cantarini ) , are deported . \n" +
                "guido , seeing the horrors , is desperate to protect his wife and child . \n" +
                "for = his wife , who is separated from him , he must find ways to communicate to = her that he is all right . \n" +
                "for his son , ( and this is the most controversial part of the film ) he convinces the youngster that this is all one big elaborate game : it+s rules include hiding , being very quiet , and learning to not ask for seconds . \n" +
                "which raises the big questions : how do we deal with pain ? \n" +
                "with persecution ? \n" +
                "with injustice on the worst level ? \n" +
                "it+s said that tragedies bring out the best in people , finding strengths they did not realize they had . \n" +
                "other times they deteriorate , become overwhelmed = with little strength or incentive to swim . \n" +
                "sometimes even , people giggle in these moments , as if there were a vacant emotion behind them , yearning for some brightness to soothe their wounds . \n" +
                "benigni+s guido realizes this . \n" +
                "he is the clown , but he is not a fool . \n" +
                "he sees the slurs and the vandalism , and while feeling the weight of = the verbal attacks , he still has the audacity to see if everyone around = him , perhaps even the antagonists , laugh . \n" +
                "his liberty is stripped , but not his dignity , and certainly not the dignities of those around him . \n" +
                "it+s obvious from the onset that here is someone who is very much against fascism and the preposterousness of an aryan nation , but does so with a grinning , joyful demeanor . \n" +
                "perhaps that is a form of denial . \n" +
                "perhaps his alternate reality is all that he could muster ( not unlike the very different _brazil_ ) . \n" +
                "or . . . \n" +
                "perhaps this is an example of great fortitude , with no weapons but = wit . \n" +
                "whatever his purposes is subject to debate , but also a sure sign that this is one of the great three-dimensional characters , stranded amidst = a terrifyingly risky concept . \n" +
                "surprisingly , it works . \n" +
                "it is an important footnote that this film was not intended to be an accurate reflection of the holocaust . \n" +
                "it+s not that the atrocities are trivialized , far from it . \n" +
                "they don+t have to be broadcast and explicitly shown to muster the same horror--less is more . \n" +
                "i believe = that benigni was cautious to preserve the tone , and i like the theory that = it is guido+s alternate reality that we+re seeing . \n" +
                "however , any criticism that this is rewriting history , that the holocaust wasn+t as bad as it seems , is simply off-target . \n" +
                "benigni+s film has won audience awards at cannes , at toronto , and is a definite shoo-in for best foreign language film ( hopefully for best picture as well ) . \n" +
                "he has been made an honorary jew by a jerusalem film society . \n" +
                "it swept the italian academy awards . \n" +
                "controversy notwithstanding , it is one of the great films this year , and as of this writing , the one to beat for best film . \n" +
                "masterful . \n" +
                "miraculous . \n" +
                "a must-see . \n");
    }

    private static void read() throws Exception {
        readAndAdd("hit", "C:\\Users\\Miche\\Documents\\_DEV\\datascience\\ch.bfh.bti7535.w2017.-group-name-\\input\\pos");
        readAndAdd("miss", "C:\\Users\\Miche\\Documents\\_DEV\\datascience\\ch.bfh.bti7535.w2017.-group-name-\\input\\neg");
    }

    private static void readAndAdd(String classification, String path) throws Exception {
        DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(path), p -> p.toFile().isFile());
        Iterator<Path> iterator = paths.iterator();
        MessageClassifier messageCl = getMessageClassifier();
        while (iterator.hasNext()) {
            Path next = iterator.next();
            StringBuffer message = getMessage(next.toAbsolutePath().toString());
            System.out.println(next.toAbsolutePath().toString());
            messageCl.updateData(message.toString(), classification);
        }
        SerializationHelper.write(MESSAGECLASSIFIER_MODEL, messageCl);
    }

    private static MessageClassifier getMessageClassifier() throws Exception {
        MessageClassifier messageCl;
        try {
            messageCl = (MessageClassifier) SerializationHelper.read(MESSAGECLASSIFIER_MODEL);
        } catch (FileNotFoundException e) {
            messageCl = new MessageClassifier();
        }
        return messageCl;
    }

    private static StringBuffer getMessage(String file) throws IOException {
        FileReader m = new FileReader(file);
        StringBuffer message = new StringBuffer();
        int l;
        while ((l = m.read()) != -1)
            message.append((char) l);
        m.close();
        return message;
    }

    private static void old(String[] args) {
        try {
            // Read message file into string.
            String messageName = Utils.getOption('m', args);
            if (messageName.length() == 0)
                throw new Exception("Must provide name of message file ('-m <file>').");
            FileReader m = new FileReader(messageName);
            StringBuffer message = new StringBuffer();
            int l;
            while ((l = m.read()) != -1)
                message.append((char) l);
            m.close();

            // Check if class value is given.
            String classValue = Utils.getOption('c', args);

            // If model file exists, read it, otherwise create new one.
            String modelName = Utils.getOption('t', args);
            if (modelName.length() == 0)
                throw new Exception("Must provide name of model file ('-t <file>').");
            MessageClassifier messageCl;
            try {
                messageCl = (MessageClassifier) SerializationHelper.read(modelName);
            } catch (FileNotFoundException e) {
                messageCl = new MessageClassifier();
            }

            // Check if there are any options left
            Utils.checkForRemainingOptions(args);

            // Process message.
            if (classValue.length() != 0)
                messageCl.updateData(message.toString(), classValue);
            else
                messageCl.classifyMessage(message.toString());

            // Save message classifier object only if it was updated.
            if (classValue.length() != 0)
                SerializationHelper.write(modelName, messageCl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}