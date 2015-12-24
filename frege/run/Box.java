package frege.run;
/**
 * <p>This is a bit of a hack to allow native types like String or Arrays to appear
 * as types of kind * -> * so that they can be instances of ListView, ListSource, etc.</p>
 * 
 * <p> It is supposed to work through boxing such data in a box, and unboxing it again,
 * when needed. The compiler has special support for this.</p>
 * 
 * <p> Unfortunately, though, the important property that (T<A>) and (Kind.U<T<?>, A>) 
 * are identical (i.e. the same object, just differently typed) gets lost this way.
 * And this means that this whole approach breaks down in more complicated cases,
 * like with uncons, where the kinded form of the type appears inside a Maybe and a Tuple.
 * <br>
 * Example:
 *    tail :: ListView => c . c e -> c e
 * <br>
 * Java signature in type class interface:
 * <br>
 *  <C extends Kind.U<C, ?>, E> Kind.U<C, E> tail(Kind.U<C, E>) ....
 * <br>
 * Instance for String  C = Box<String,?>
 * <br>
 * Java signature in instance
 * <br>
 * Kind.U<Box<String, ?>, E> tail(Kind.U<Box<String,?>, E>)
 * <br>
 * Actual worker method:  String tail(String )
 * </p>
 * 
 * <p>
 * We can here easily unbox the incoming argument, pass it to the worker and
 * box the result. However with uncons, the shape of the result must be:
 * 
 * TMaybe<TTuple2<E, Kind.U<Box<String,?>, E>>>
 * 
 * but we actually get a Maybe with a tuple that has a String as second argument! 
 * We can't just cast this, but need to actually reconstruct the value with a boxed String! 
 * Since this is the only problematic case (?) in the standard library, we have a special
 * compiler generated function to adjust this.
 * </p>
 * 
 * 
 * @author ingo
 *
 * @param <T>  The native base type. If generic, the type argument should equal A
 * @param <A>  The type variable symbolising the type argument of T, which may be a phantom type if T is not generic.
 */
public final class Box<T,A> 
		implements Lazy<T>, Kind.U<Box<T,?>, A> {
	public Box(T d) { data = d; }
	final private T data;
	final public T call() { return data; }
	final public static<B,E> Box<B,E> coerce(Kind.U<Box<B,?>, E> it) { return (Box<B,E>)it; }
	final public T unkind(Kind.U<Box<T,?>, A> it) {
		return coerce(it).call();
	}
}
