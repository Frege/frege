/**
  */
package frege.runtime;

/**
 * <p>Provides a uniq object that is useless and cannot be casted
 * to any other type, except {@link Object}.</p>
 * 
 * <p>This is used in the runtime to detect so called black holes.
 * Black holes are expressions that demand evaluation of themselves
 * while they are avaluated.</p>
 * 
 *  <p>Consider:</p>
 *  
 *  <p><code>let a = a + 1 in a + 42</code></p>
 *  
 *  <p>Note that the following is not a blackhole:</p>
 *  
 *  <p><code> let a = 1:a in take 20 a </code></p>
 *  
 *  <p>because evaluation of <code>1:a</code> does not demand
 *  evaluation of <code>a</code>.</p>
 *  
 * @author ingo
 *
 */
public class BlackHole {
	private BlackHole() {}
	/**
	 * <p>A uniq object that is useless and cannot be casted
	 * to any other type, except {@link Object}.</p>
	 */
	final public static BlackHole it = new BlackHole();
}
