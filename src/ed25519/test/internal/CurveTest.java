package ed25519.test.internal;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.internal.BigPoint;
import ed25519.application.internal.BitArray;
import ed25519.application.internal.CryptoNumber;
import ed25519.application.internal.Curve;

public class CurveTest {
	private Curve c;

	@BeforeClass
	protected void setUp() throws Exception {
		c = new Curve();
	}

	@Test(dependsOnMethods = {"ed25519.test.internal.CryptoNumberTest.testInvert", "ed25519.test.internal.BigPointTest.testgetPointfromY"}, dependsOnGroups = {"bigpoint", "basics"})
	public void testGetBasePoint() {
		CryptoNumber x = new CryptoNumber("15112221349535400772501151409588531511454012693041857206046113283949847762202");
		CryptoNumber y = new CryptoNumber("46316835694926478169428394003475163141307993866256225615783033603165251855960");
		BigPoint expected = new BigPoint(x,y);
		BigPoint basePoint = c.getBasePoint();
		Assert.assertEquals(basePoint, expected);
	}
	
	@DataProvider(name = "edwards")
	public static Object[][] edwardsProvider() {
		BigPoint zero = new BigPoint(new CryptoNumber(0), new CryptoNumber(0));
		BigPoint zeroOne = new BigPoint(new CryptoNumber(0), new CryptoNumber(1));
		BigPoint oneZero = new BigPoint(new CryptoNumber(1), new CryptoNumber(0));
		BigPoint one = new BigPoint(new CryptoNumber(1), new CryptoNumber(1));
		BigPoint base = new BigPoint(new CryptoNumber("15112221349535400772501151409588531511454012693041857206046113283949847762202"), new CryptoNumber("46316835694926478169428394003475163141307993866256225615783033603165251855960"));
		BigPoint result1 = new BigPoint(new CryptoNumber("243332"), new CryptoNumber("51380297829790456491237162401597246033761091082941378702394490295467508647106"));
		BigPoint result2 = new BigPoint(new CryptoNumber("24727413235106541002554574571675588834622768167397638456726423682521233608206"), new CryptoNumber("15549675580280190176352668710449542251549572066445060580507079593062643049417"));
		Object[][] data = {{zero.copy(), zero.copy(), zero},
				{one.copy(), one.copy(),result1},
				{zero.copy(), one.copy(), zero},
				{one.copy(), zero.copy(), zero},
				{zeroOne.copy(), oneZero.copy(), oneZero},
				{oneZero.copy(), zeroOne.copy(), oneZero},
				{base.copy(), base.copy(), result2}};
		return data;
	}
	
	@Test(dataProvider= "edwards", dependsOnGroups = {"bigpoint"}, dependsOnMethods = "ed25519.test.internal.CryptoNumberTest.testInvert")
	public void testEdwards(BigPoint first, BigPoint second, BigPoint expected) {
		BigPoint result = c.edwards(first, second);
		Assert.assertEquals(result, expected);
	}

	@DataProvider(name = "scalar")
	public static Object[][] scalarProvider() {
		BigPoint result1 = new BigPoint(new CryptoNumber("0"), new CryptoNumber("1"));
		BigPoint result2 = new BigPoint(new CryptoNumber("15112221349535400772501151409588531511454012693041857206046113283949847762202"), new CryptoNumber("46316835694926478169428394003475163141307993866256225615783033603165251855960"));
		BigPoint result3 = new BigPoint(new CryptoNumber("24727413235106541002554574571675588834622768167397638456726423682521233608206"), new CryptoNumber("15549675580280190176352668710449542251549572066445060580507079593062643049417"));
		Object[][] data = {{new CryptoNumber(0), result1},
				{new CryptoNumber(1), result2},
				{new CryptoNumber(2), result3}};
		return data;
	}
	
	@Test(dataProvider = "scalar", dependsOnGroups = {"bigpoint","basics","testbit"}, dependsOnMethods = {"testEdwards", "testGetBasePoint"})
	public void testScalarMult(CryptoNumber input, BigPoint expected) {
		BigPoint result = c.scalarmult(input);
		Assert.assertEquals(result, expected);
	}
	
	@DataProvider(name = "hint")
	public static Object[][] hintProvider() {
		BitArray input2 = new BitArray(DatatypeConverter.parseHexBinary("7a4dc0a2d5c6fbae5fb5b0d536a0a9e6b686369fa57a027687c363032154759648656c6c6f20576f726c64"));
		CryptoNumber expected1 = new CryptoNumber("3291835376408573590478209986637364656599265025014012802863049622424083630783948306431999498413285667939592978357630573418285899181951386474024455144309711");
		CryptoNumber expected2 = new CryptoNumber("9460441281220697484169569664861910275441876295883736108623813189937332058033439812391915346157421051113320734506128038195984077300170725992452881368203635");
		Object[][] data = {{new BitArray(""),expected1},{input2,expected2}};
		return data;
	}
	
	@Test(dataProvider=  "hint", dependsOnGroups = {"hash","byte"})
	public void testHint(BitArray input, CryptoNumber expected) throws NoSuchAlgorithmException {
		Assert.assertEquals(c.hint(input), expected);
	}
}
