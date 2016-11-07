package demo.java7;

import java.net.URI;
import java.net.URISyntaxException;

public class CatchMultiExceptionsDemo {

	public static void main(String[] args) {
		String[] paths = {null, "^bcd", "abcd"};
		for (String path : paths) {
			System.out.println(String.format("%s: %s", path, open(path)));
		}
	}

	public static String open(String path) {
		try {
			new URI(path);
		} catch (NullPointerException | URISyntaxException e) {
			return e.getClass().toString();
		}
		return "success";
	}

}
