package frege.run8;

import java.util.function.*;
/**
 * Helper functions to convert between Java8 function types and Frege functions.
 * @author ingo
 *
 */
public abstract class Utils {
	/** <p>Make a {@link BiFunction} from a Frege function that takes 2 arguments</p> */
	public static<T,U,R> BiFunction<T, U, R> bifunction(Func.U<T, Func.U<U, R>> f) {
		return (T a, U b) -> f
					.apply(Thunk.lazy(a))
					.call()
					.apply(Thunk.lazy(b))
					.call();
	} 

}