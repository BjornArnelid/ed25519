package ed25519.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ed25519.application.Hash;

public class HashTest {
	
	private Hash sha512;
	private byte[] input;

	@BeforeMethod
	protected void setUp() throws Exception {
		sha512 = new Hash("SHA-512");
		input = "0".getBytes();
		sha512.digest(input);
	}
	@Test(groups = {"hash"})
	public void testHashString() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] expected = DatatypeConverter.parseHexBinary("31bca02094eb78126a517b206a88c73cfa9ec6f704c7030d18212cace820f025f00bf0ea68dbf3f3a5436ca63b53bf7bf80ad8d5de7d8359d0b7fed9dbc3ab99");
		Assert.assertTrue(Arrays.equals(expected, sha512.getBytes()),
				"Expected: " + expected + "\nbut was " + sha512.getBytes());
	}
	
	@DataProvider(name="getbit")
	public static Object[][] getbitProvider() {
		Object[][] data = {{0,1},{8,0},{10,1}};
		return data;
	}
		
	@Test(dataProvider = "getbit", groups = {"hash"})
	public void testGetBitInHash(int n, int expected) throws NoSuchAlgorithmException { 
		Assert.assertEquals(sha512.getBit(n), expected);
	}

	@Test
	public void testGetBytes() {
		
		Assert.fail();
	}
}
