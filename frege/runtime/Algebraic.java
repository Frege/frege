package frege.runtime;

public abstract class Algebraic implements Lazy, Value {

	@Override
	public Algebraic call() {
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> R forced() {
		return (R) this;
	}

}
