package ch.bfh.bti7535.w2017.groupname;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit evaluate for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the evaluate case
     *
     * @param testName name of the evaluate case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
