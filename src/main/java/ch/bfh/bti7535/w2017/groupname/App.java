package ch.bfh.bti7535.w2017.groupname;

import ch.bfh.bti7535.w2017.groupname.process.CVEvaluationChainProcessor;
import ch.bfh.bti7535.w2017.groupname.process.CVNBProcessChain;
import ch.bfh.bti7535.w2017.groupname.process.DefaultChainProcessor;
import ch.bfh.bti7535.w2017.groupname.process.VectorASChain;
import weka.core.Instances;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {

        VectorASChain vectorASChain = new VectorASChain();

        try {
            new DefaultChainProcessor().process(vectorASChain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*CVNBProcessChain filterChain = new CVNBProcessChain();

        try {
            new CVEvaluationChainProcessor().process(filterChain);
        } catch (Exception e) {
            e.printStackTrace();
        }*/




    }

}
