package ed25519.test.internal;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.CryptoNumber;

public class BigPointTest {
	
	@Test(groups = {"bigpoint"}, dependsOnGroups = {"basics"})
	public void testBigPointEquals() {
		BigPoint first = new BigPoint(new CryptoNumber(1), new CryptoNumber(2));
		BigPoint second = new BigPoint(new CryptoNumber(1), new CryptoNumber(2));
		Assert.assertEquals(first,second);
	}
	
	@Test(groups = {"bigpoint"}, dependsOnGroups = {"basics"})
	public void testBigPointNotEquals() {
		BigPoint first = new BigPoint(new CryptoNumber(1), new CryptoNumber(2));
		BigPoint second = new BigPoint(new CryptoNumber(3), new CryptoNumber(4));
		Assert.assertNotEquals(first, second);
	}
	
	@Test(groups = {"bigpoint"}, dependsOnMethods = {"ed25519.test.internal.ConstantsTest.testGetQ"})
	public void testXMod() {
		CryptoNumber expected = new CryptoNumber(1);
		BigPoint point = new BigPoint();
		point.setX(new CryptoNumber("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		Assert.assertEquals(point.getX(), expected);
	}
	
	@Test(groups = {"bigpoint"}, dependsOnMethods = {"ed25519.test.internal.ConstantsTest.testGetQ"})
	public void testYMod() {
		CryptoNumber expected = new CryptoNumber(1);
		BigPoint point = new BigPoint();
		point.setY(new CryptoNumber("57896044618658097711785492504343953926634992332820282019728792003956564819950"));
		Assert.assertEquals(point.getY(), expected);
	}

	@Test(groups = "bigpoint")
	public void testCopy() {
		BigPoint original = new BigPoint(new CryptoNumber(1), new CryptoNumber(1));
		BigPoint copy = original.copy();
		Assert.assertNotSame(original.getX(), copy.getX());
		Assert.assertNotSame(original.getY(), copy.getY());
	}
	
	@DataProvider(name = "fromY")
	public static Object[][] fromYProvider() {
		CryptoNumber minusOne = new CryptoNumber(-1);
		CryptoNumber zero = new CryptoNumber(0);
		CryptoNumber one = new CryptoNumber(1);
		CryptoNumber two = new CryptoNumber(2);
		CryptoNumber four = new CryptoNumber(4);
		CryptoNumber large1 = new CryptoNumber("19681161376707505956807079304988542015446066515923890162744021073123829784752");
		CryptoNumber large2 = new CryptoNumber("15185035556283238358358299567679723596735194429604445868805687310551713597428");
		CryptoNumber large3 = new CryptoNumber("26193273134124080442446118532604303931175156347221147955160486408041496074798");
		CryptoNumber large4 = new CryptoNumber("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		CryptoNumber large5 = new CryptoNumber("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		Object[][] data = {{minusOne, zero}, {zero.copy(), large1}, {one, zero}, {two, large2}, {four, large3}, {large4, large5}};
		return data;
	}
	
	@Test(dataProvider = "fromY", dependsOnMethods = {"ed25519.test.internal.CryptoNumberTest.testExpmod", "ed25519.test.internal.ConstantsTest.testGetD", "ed25519.test.internal.ConstantsTest.testGetI"})
	public void testgetPointfromY(CryptoNumber value, CryptoNumber expected) {
		Assert.assertEquals(new BigPoint(value).getX(), expected);
	}
}