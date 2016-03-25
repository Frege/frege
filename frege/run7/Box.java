/**
 * <p>Boxed (native) values.</p>
 */
package frege.run7;

/**
 * <p>Java values and primitives can be put in boxes to make them lazy. This should use less
 * memory than {@link Thunk}s, and may also avoid auto(un)boxing for primitives.</p>
 * 
 * <p>The method {@link Thunk#lazy} will create appropriate boxes.</p>
 * @author ingo
 *
 */
public  class Box<R> implements Lazy<R> {

	final R item;

	public Box(final R it) {
		item = it;
	}

	@Override
	public R call() { return item; }
	
	@Override
	public boolean isShared() { return true; }

	@Override
	public Thunk<R> asThunk() { return null; }

	public static class B implements Lazy<Boolean> {

		final boolean data;
		
		public B(final boolean it) { data = it; }
		
		@Override
		public Boolean call() { return data; }

		@Override
		public Thunk<Boolean> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}
	
	/**
	 * <p>O, like in "octet", since B already used for booleans</p>
	 * @author ingo
	 *
	 */
	public static class O implements Lazy<Byte> {

		final byte data;
		
		public O(final byte it) { data = it; }
		
		@Override
		public Byte call() { return data; }

		@Override
		public Thunk<Byte> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}

	public static class S implements Lazy<Short> {

		final short data;
		
		public S(final short it) { data = it; }
		
		@Override
		public Short call() { return data; }

		@Override
		public Thunk<Short> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}
	
	public static class C implements Lazy<Character> {
		final char data;
		
		public C(final char it) { data = it; }

		@Override
		public Character call() { return data; }

		@Override
		public Thunk<Character> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}
	
	public static class I implements Lazy<Integer> {
		final int data;
		
		public I(final int it) { data = it; }

		@Override
		public Integer call() { return data; }

		@Override
		public Thunk<Integer> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}

	public static class L implements Lazy<Long> {
		final long data;
		
		public L(final long it) { data = it; }

		@Override
		public Long call() { return data; }

		@Override
		public Thunk<Long> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}

	public static class F implements Lazy<Float> {
		final float data;
		
		public F(final float it) { data = it; }

		@Override
		public Float call() { return data; }

		@Override
		public Thunk<Float> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}
	
	public static class D implements Lazy<Double> {
		final double data;
		
		public D(final double it) { data = it; }

		@Override
		public Double call() { return data; }

		@Override
		public Thunk<Double> asThunk() { return null; }
		
		@Override
		public boolean isShared() { return true; }
	}

}
