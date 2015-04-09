package examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashMap;

import frege.runtime.Runtime;

public final class JHashMap {

	public static void main(String[] args) {
		try {
			if ("count".equals(args[0])) 
				count();
			else if ("uniq".equals(args[0]))
				uniq();
		} 
		catch (IOException e) {}
	}
	
	
	
	public static void uniq() throws IOException {
		HashMap<String, Integer> map = new HashMap<>();
		
		String line = null;
		Pattern words = Pattern.compile("\\w+", 448);	// same mode as in Frege, for fairness
		// int result = 0;
		BufferedReader in = Runtime.stdin.get();
		
		while ((line = in.readLine()) != null) {
			// Matcher m = words.matcher(line);
			// while (m.find()) {
			//	String key = m.group();
				Integer n = map.get(line);
				if (n == null) map.put(line, 1);
				else map.put(line, n+1);
			// }
		}
		int wörter = 0;
		for (int n : map.values()) wörter += n;
		System.err.println(wörter);
		System.out.println(map.size());

	}
	
	public static void count() throws IOException {
		String line = null;
		Pattern words = Pattern.compile("\\w+", 448);	// same mode as in Frege, for fairness
		int result = 0;
		
		while ((line = Runtime.stdin.get().readLine()) != null) {
			Matcher m = words.matcher(line);
			while (m.find()) result++;
		}
		System.out.println(result);
	}
	
}
