package esercizio1;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {

	public static String hash(String p) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(p.getBytes(Charset.defaultCharset()));
		String encoded = Base64.getEncoder().encodeToString(hash);
		return encoded;
	}

	public static void main(String[] args) {
		try {
			System.out.println(hash(args[0]));
		} catch (NoSuchAlgorithmException n) {
			System.out.print(n.getMessage());
		} catch (UnsupportedEncodingException u) {
			System.out.print(u.getMessage());
		}
	}
}
