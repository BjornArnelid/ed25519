package ed25519.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite();
		//$JUnit-BEGIN$
		suite.addTestSuite(HashTest.class);
		//suite.addTestSuite(CryptoTest.class);
		suite.addTestSuite(NumberUtilsTest.class);
		suite.addTestSuite(BigPointTest.class);
		suite.addTestSuite(ConstantsTest.class);
		suite.addTestSuite(CurveTest.class);
		//$JUnit-END$
		return suite;
	}
}
