package frege.runtime.v78;

import java.util.concurrent.Callable;

/**
 * Common interface of all lazy values.
 * 
 * @author ingo
 *
 */
public interface Lazy extends Callable<Object> {

	/**
	 * <p> Compute the value if it is needed. </p>
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public abstract Object call();
}