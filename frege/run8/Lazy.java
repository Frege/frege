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
}