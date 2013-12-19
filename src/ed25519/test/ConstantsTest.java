package ed25519.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.math.BigInteger;

import ed25519.application.Constants;

public class ConstantsTest {
	private Constants c;
	
	@BeforeMethod
	protected void setUp() throws Exception {
		c = new Constants();
	}

	@AfterMethod
	protected void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBitLength() {
		int expected = 256;
		AssertJUnit.assertEquals(expected, c.getb());
	}

	@Test
	public void testBigPrime() {
		BigInteger expected = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		AssertJUnit.assertEquals(expected, c.getq());
	}	
}
