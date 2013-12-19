package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ed25519.application.Constants;
import ed25519.application.NumberUtils;

public class NumberUtilsTest {
	private NumberUtils n;
	private Constants c;
	
	@BeforeClass
	protected void setUp() throws Exception {
		c = new Constants();
		n = new NumberUtils(c);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodfor0() {
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(0);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
	Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodfor1and1() {
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodfor2and1() {
		BigInteger first = BigInteger.valueOf(2);
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(2);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodforHugeand1() {
		BigInteger first = c.getq().add(BigInteger.valueOf(1));
		BigInteger second = BigInteger.valueOf(1);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodfor1and2() {
		BigInteger first = BigInteger.valueOf(1);
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = BigInteger.valueOf(1);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodfor2and2() {
		BigInteger first = BigInteger.valueOf(2);
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = BigInteger.valueOf(4);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}

	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodforHugeand2() {
		BigInteger first = c.getq().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(2);
		BigInteger expected = new BigInteger("43422033463993573283839119378257965444976244249615211514796594002967423614962");
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpModfor3and3() {
		BigInteger first = BigInteger.valueOf(3);
		BigInteger second = BigInteger.valueOf(3);
		BigInteger expected = BigInteger.valueOf(27);
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodforHugeand3() {
		BigInteger first = c.getq().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(3);
		BigInteger expected = new BigInteger("36185027886661311069865932815214971204146870208012676262330495002472853012468");
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}

	@Test(dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmodforHugeand4() {
		BigInteger first = c.getq().divide(BigInteger.valueOf(2));
		BigInteger second = BigInteger.valueOf(4);
		BigInteger expected = new BigInteger("39803530675327442176852526096736468324561557228813943888563544502720138313715");
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnGroups = {"expmod"})
	public void testGetI() {
		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		Assert.assertEquals(n.getI(), expected);
	}
}
