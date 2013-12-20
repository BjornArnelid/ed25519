package ed25519.test;

import java.math.BigInteger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
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
	
	@DataProvider(name = "expmod")
	public static Object[][] expmodProvider() {
		Constants c = new Constants();
		BigInteger zero = BigInteger.valueOf(0);
		BigInteger one = BigInteger.valueOf(1);
		BigInteger two = BigInteger.valueOf(2);
		BigInteger three = BigInteger.valueOf(3);
		BigInteger four = BigInteger.valueOf(4);
		BigInteger twentyseven = BigInteger.valueOf(27);
		BigInteger halfq = c.getq().divide(BigInteger.valueOf(2));
		BigInteger qplusone = c.getq().add(BigInteger.valueOf(1));
		BigInteger large1 = new BigInteger("43422033463993573283839119378257965444976244249615211514796594002967423614962");
		BigInteger large2 = new BigInteger("36185027886661311069865932815214971204146870208012676262330495002472853012468");
		BigInteger large3 = new BigInteger("39803530675327442176852526096736468324561557228813943888563544502720138313715");
		Object[][] data = new Object[][] {{one, zero, one}, {one, one, one},
				{two, one, two}, {qplusone, one, one}, {one, two, one},
				{two, two, four}, {halfq, two, large1},
				{three, three, twentyseven}, {halfq, three, large2},
				{halfq, four, large3}};
		return data;
	}
	
	@Test(dataProvider = "expmod", dependsOnMethods = {"ed25519.test.ConstantsTest.testBigPrime"}, groups = {"expmod"})
	public void testExpmod(BigInteger first, BigInteger second, BigInteger expected) {
		BigInteger actual = n.expmod(first, second);
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dependsOnGroups = {"expmod"})
	public void testGetI() {
		BigInteger expected = new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		Assert.assertEquals(n.getI(), expected);
	}
}
