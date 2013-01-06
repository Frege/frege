package frege.runtime;

import java.util.concurrent.Callable;

/**
 * Common interface of all lazy values.
 * 
 * @author ingo
 *
 */
public interface Lazy extends Callable<Object> {

	/**
	 * <p> Compute and cache the value by repeatedly invoking {@link Lazy#eval}, 
	 * or return previously cached result. </p>
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	public abstract Object call();

	/**
	 * <p>Evaluate this lazy value.</p>
	 * <p>
	 * This method is intended for use in generated code, as the compiler
	 * hopefully never errs about the expected type.
	 * </p>
	 * 
	 * @throws ClassCastException if the
	 *         evaluated value cannot be casted to <code>R</code>         
	 * 
	 * @return the evaluated value conveniently casted to the expected return type <code>R</code>.
	 * @author ingo
	 */

	public abstract <R> R forced();

}