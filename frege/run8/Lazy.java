package frege.run8;

import java.util.concurrent.Callable;


/**
 * Common interface of all lazy values.
 * 
 * @author ingo
 *
 */
// @FunctionalInterface
public interface Lazy<R> extends Callable<R> {

	/**
	 * <p> Compute the value if it is needed. </p>
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public abstract R call();

	/**
	 * <p> Tell if this is really a {@link Thunk} </p> 
	 * @return
	 */
	public default Thunk<R> asThunk() { return null; }
	
	/**
	 * <p> Tell if this is shared. </p>
	 * <p> Data and functions whose {@link Lazy#call()} method returns <b>this</b> as well
	 * as simple boxes that just hold a value ready to be supplied and {@link Thunk}s 
	 * are considered shared.
	 * <p> But a bare lambda expression is assumed to be in need of sharing. For example: </p>
	 * <code> () -&gt; 42 </code>
	 * @return false, if sharing this would make any sense, otherwise true
	 */
	public default boolean isShared() { return false; }

}
