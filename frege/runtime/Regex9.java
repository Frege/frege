/**
 * 
 */
package frege.runtime;

import java.util.regex.Matcher;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * <p> Helper functions for PreludeBase and PreludeRegex </p>
 * 
 * <p> The methods here help us to hide the {@link java.util.regex.Matcher} type, 
 * which is impure, and cannot made pure, because copying is effectively impossible
 * at least from JDK9 on. </p>
 * <p> Instead, all Frege code will work with {@link java.util.regex.MatchResult}, which is
 * a pure interface, and since JDK9 apparently backed by an immutable class. </p>
 * @author ingo
 *
 */
public class Regex9 {
	
	/*** <p>implements the (=~) operator</p> */
	public static MatchResult findResult(String s, Pattern p) {
		Matcher m = p.matcher(s);
		if (m.find()) return m;
		else return null;
	}
	
	/*** <p>implements the findAt function with offset</p> */
	public static MatchResult findResult(String s, Pattern p, int n) {
		Matcher m = p.matcher(s);
		if (n >= 0 && n < s.length() && m.find(n)) return m;
		else return null;
	}
	
	/*** <p>implements the (~) operator</p> */
	public static boolean find(String s, Pattern p) {
		return p.matcher(s).find();
	}
	
	/*** <p>implements replaceFirst</p> */
	public static String replaceFirst(String s, Pattern p, String r) {
		return p.matcher(s).replaceFirst(r);
	}
	
	/*** <p>implements replaceAll</p> */
	public static String replaceAll(String s, Pattern p, String r) {
		return p.matcher(s).replaceAll(r);
	}
}
