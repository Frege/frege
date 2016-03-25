package frege.run7;

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
	public  R call();

	/**
	 * <p> Tell if this is really a {@link Thunk} </p> 
	 * @return
	 */
	public Thunk<R> asThunk();

	/**
	 * <p> Tell if this is shared. </p>
	 * <p> Data and functions whose {@link Lazy#call()} method returns <b>this</b> as well
	 * as simple boxes that just hold a value ready to be supplied and {@link Thunk}s 
	 * are considered shared.
	 * <p> But a bare lambda expression is assumed to be in need of sharing. For example: </p>
	 * <code> () -&gt; 42 </code>
	 */
	public boolean isShared(); 
	/**
	 */
	public static abstract class D<X> implements Lazy<X> {
		@Override public Thunk<X> asThunk() { return null; }
		@Override public boolean  isShared() { return false; }
	}
}