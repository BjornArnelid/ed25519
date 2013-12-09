package ed25519.test;

import java.math.BigInteger;

import junit.framework.TestCase;
import ed25519.application.Constants;

public class ConstantsTest extends TestCase {
	private Constants c;
	
	protected void setUp() throws Exception {
		super.setUp();
		c = new Constants();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetBitLength() {
		int expected = 256;
		assertEquals(expected, c.getb());
	}

	public void testBigPrime() {
		BigInteger expected = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		assertEquals(expected, c.getq());
	}	
}
