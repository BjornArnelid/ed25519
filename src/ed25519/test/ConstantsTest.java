package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ed25519.application.Constants;

public class ConstantsTest {
	private Constants c;
	
	@BeforeClass
	protected void setUp() throws Exception {
		c = new Constants();
	}
	
	@Test
	public void testGetBitLength() {
		int expected = 256;
		Assert.assertEquals(c.getb(), expected);
	}

	@Test
	public void testBigPrime() {
		BigInteger expected = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
		Assert.assertEquals(c.getq(), expected);
	}	
}
