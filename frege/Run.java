package frege;
import java.lang.annotation.*;

/*
 * $Author$
 * $Revision$
 * $Id$
 * $Date$
 */

public class Run {
    public static class Undefined extends java.lang.IllegalArgumentException {
        public Undefined(String err) {
            super(err);
        }
        public Undefined(String err, Throwable cause) {
            super(err, cause);
        }
    }
    public static class PatternMatchFailed extends Undefined {
        public PatternMatchFailed(String pack, int line, String fun) {
            super(pack + "." + fun + " at line " + line);
        }
        public PatternMatchFailed(V x) {
            super("for value  " + x);
        }
    }
    public static class InstanceMethodMissing extends Undefined {
        public InstanceMethodMissing(Object type, String fun) {
            super(type.getClass().getName() + " has no implementation for " + fun);
        }
    }
    public static class FieldNotFound extends Undefined {
        public FieldNotFound(Object type, String fun) {
            super(type.getClass().getName() + " has no public field " + fun);
        }
    }
    public static class PostMortem extends java.lang.IllegalArgumentException {
        public PostMortem(Throwable t) { super("post mortem dump initiated", t); }
    }

    public static class BlackHole extends Undefined {
        public BlackHole(V v) {
            super("evaluation of non terminating expression " + v);
        }
    }
    /**
     *  This is the means to give control back to V.E(), requesting evaluation
     *  of another subexpression.
     *  Up to now, the call stack was:
     *     V.E() calls F.eval() which possibly calls V.E() for a subexpr and so on
     *  Now it works like this:
     *     V.E() calls F.eval()
     *     when a subexpression x must be evaluated, F.eval throws Trampolin(x)
     *     back in V.E(), the old expression v is pushed and evaluation continues
     *     with x. Eventually, x is evaluated and v is popped and evaluated further.
     */
    public static class Trampolin extends java.lang.Exception {
        L subex;      // the sub expression that must be evaluated before
        public void jump(V x) throws Trampolin {
            // System.err.print(".");
            this.subex = (L) x;
            throw this;
        }
    }
    public static Trampolin trampolin = new Trampolin();

    /**
     * This is the means to avoid stack overflows caused by repeated tail calls.
     * Like, e.g.
     * > f n = g (n-1)
     * > g n = f (n+1)
     * or, more realistic
     * printlist (a:as) = do { print a >> printlist as }
     * at runtime, this will expand to
     * \world1 -> case print a world1 of (r, world2) -> case printlist as of
     *     IO h -> h world2
     * But h is just again the same do block!
     */
    public static class Longjump extends java.lang.RuntimeException {
        //static Longjump longjmp = new Longjump();
        final public V subex;  // the yet to be processed result of a tail call
        Longjump(V it) { subex = it; }
        @Override public Throwable fillInStackTrace() { return this; }
        /**
        @Override public Throwable fillInStackTrace() {
            Throwable t = super.fillInStackTrace();
            System.err.println("Longjump from stack depth " + t.getStackTrace().length);
            return this;
        }
        ***/

        public void jump() {
            // subex = it;
            throw this;
        }
    }

    public static V tcGoto(final F f, final V v) {
        new Longjump(f.Z(v)).jump();
        //* not reached
        return v;
    }

    public static V tcLoop(final F f, V v) {
        V l = f.Z(v);
        // int id = new Throwable().getStackTrace().length;   // l.hashCode();
        boolean catched = true;
        v = null;   // give GC something to do meanwhile
        // System.err.println(id + ": " + "enter TC loop");
        loop: while (catched) {
            catched = false;
            try {
                // System.err.println(id + ": " + "trying " + l);
                return l.E();       // evaluate it
            }
            catch (Longjump lj) {
                // System.err.println(id + ": " + "come from stack depth " + lj.getStackTrace().length);
                l = lj.subex;
                // lj.subex = null;
                catched = true;
            }
            /*
            finally {
                if (!catched) {
                    System.err.println(id + ": " + "leave TC loop, result=" + l);
                }
            }
            */
        }
        throw new Error(/*id + ": " +*/ "must not happen in TC");
    }

    // private static java.util.LinkedList<L> stack = new java.util.LinkedList<L>();
    public static java.util.concurrent.ExecutorService async = null;
        // = java.util.concurrent.Executors.newCachedThreadPool();


	/**
	 * in order to keep the code short, we use single uppercase letters as follows
	 * 	letter	kind		description
	 *  A		method V	apply - next argument to function
	 *  B		method C	box - construct frege native value from java value
	 *  C		class		Constructed - subclass of V for constructed items
	 *  D		class		Delayed - a delayed computation
	 *  E		method V	eval - evaluate and return non-lazy value
	 *  F		class		Function - superclass for all function classes F1 ... F26
	 *  G       method extends Arr<T>    static method to create a base array, does a new T[size]
	 *  H       member C,N  static java.util.HashMap<String, frege.Run.V>
	 *  I		method V	get class function
	 *  J       method C    get field
	 *  K       method C    updated field
	 *  L		class		Lazy - a lazy value, evaluates to V and memoizes
	 *  M       class  N    returns Nothing if passed a null or Just (B(ref))
	 *  N		class		Native - subclass of V for native values
	 *  O		method V	ord - identify constructor
	 *  P
	 *  Q		method V	delayed? - returns true iff delayed
	 *  R		class		Record - superclass of all records
	 *  S
	 *  T
	 *  U		method V	unbox - get java native value
	 *  V		class		value - base class of all frege types
	 *  W
	 *  X		member 		constructor - integer field in sum types
	 *  Y
	 *  Z		method V	apply lazily - f.Z(x) == new L(f,x)
	 *  FD		class		Delayed application - f a
	 *
	 */

	/**
	 * common denominator for all frege runtime values
	 * The following laws hold:
	 * (1) When x of type V, then if x.v().delayed() == false
	 * then x may be cast to its actual type
	 * (2) there is no V x == null anywhere outside the very private
	 * part of a class
	 */
	static abstract public class V {
		/**
		 * @return  value that can be cast to an actual frege runtime type
		 */
		abstract public V	E();
		/**
		 * @return	if the value is not yet computed then true else false
		 */
		abstract public boolean Q();
		/**
		 * interface to class function
		 * @param key, the name of the function, i.e. "=="
		 * @return a lambda
		 */
		abstract public V I(java.lang.String key);
		/**
		 * get constructor
		 * @return a small integer that designates the constructor
		 */
		abstract public int O();
		abstract public V	A(V arg) throws Trampolin, InterruptedException,
		                                java.util.concurrent.ExecutionException;
		abstract public V	Z(V arg);
		public static int tosDepth = 3;     // print 3 levels
		public  String toString() { return this.toS(tosDepth); }
		public String toS(int n) { if (n < 1) return "..."; return super.toString(); }
	}

	static public int ord(V x) { return x.O(); }

	/**
	 * The common superclass of all non-native data types
	 * For sum types, the constructor $con is a zero base indication of what constructor
	 * was used to construct this.
	 * Each constructor value determines the fields a,b,...z that may be
	 * accessed. For a frege data constructor
	 *   Con a b c d e
	 * the members mem$a mem$b mem$c mem$d mem$e may be accessed.
	 * Note that the members are of type V and may well be delayed
	 * In records, the members are named after the fields, e.g. mem$alpha mem$beta
	 */
	static public class C extends V implements Cloneable {
	    public static long cEvals   = 0;
		public V A(V arg) { throw new Error("application of nonfunction"); }
		public V Z(V arg) { throw new Error("application of nonfunction"); }
		public boolean Q() { return false; }
		public C E() { cEvals++; return this; }
		public V I(String x) { throw new java.lang.Error("C.I("+ x + ")"); }
		public int O() { return 0; }		//* suitable for all product types
		public C() {}
		// final public int $con;           //* is present for all sum types
		// public C(final int con) { this.$con = con; }
		// final public V mem$a,mem$b,mem$c,mem$d,mem$e,mem$f,..,mem$z;    # Members
		// J :: {a | f::b } -> String -> b
        // K :: {a | f::b } -> String -> b -> a
        public frege.Run.V J(String f)  {
            Class<? extends C> cl = this.getClass();
            try {
                java.lang.reflect.Field fld = cl.getDeclaredField(f);
                // System.err.println("class " + cl.getName() + " does have field " + f);
                return (V) fld.get(this);
            }
            catch (NoSuchFieldException exc) {
                // System.err.println("type " + cl.getName() + " does not have field " + f);
            }
            catch (IllegalAccessException exc) {
                // System.err.println("illegal access to field " + f + " of " + cl.getName());
            }
            throw new FieldNotFound(this, f);
        }
        public frege.Run.C K(String f, V newval)  {
            Class<? extends C> cl = this.getClass();
            try {
                C that = (C) (this.clone());
                java.lang.reflect.Field fld = cl.getDeclaredField(f);
                // System.err.println("class " + cl.getName() + " does have field " + f);
                fld.set(that, newval);
                return that;
            }
            catch (CloneNotSupportedException exc) {    // can not possibly happen
                System.err.println(exc.getMessage());
                throw new Error("clone not supported for " + this.getClass().getName());
            }
            catch (NoSuchFieldException exc) {
                // System.err.println("type " + cl.getName() + " does not have field " + f);
            }
            catch (IllegalAccessException exc) {
                // System.err.println("illegal access to field " + f + " of " + cl.getName());
            }
            throw new FieldNotFound(this, f);
        }
	}
	public static V getLazy(V dies, String f) {
	    return ((C) dies).J(f);
	}
	public static C updLazy(V dies, String f, V newval) {
	    return ((C) dies).K(f, newval);
	}

	static final public class Var extends frege.Run.C {
	    public frege.Run.V  mem$a;
	    public Var(final frege.Run.V a) { super(); this.mem$a = a; }
	    /*
	    protected Var() {
	        super(""); this._a = null;
	    }
	    */
	    // final public static Var _Var(frege.Run.V a) { return new Var(__Var, a); }
	    public  Var E() { cEvals++; return this; }
	    public String toS(int n) {
	        if (n < 1) return "...";
	        return "(Var " + mem$a.toS(n-1) + ")";
	    }
	}


	/**
	 * The common superclass of all native data types.
	 * We cannot write N<T>, since java does not allow N<int>
	 * Thus, the interface is given formally in comments, where T resembles
	 * the type that was given on the right hand side after the native keyword.
	 */
	static public class N extends V {
	    public static long boxCount = 0;
	    public static long nEvals   = 0;
		public V A(V arg) { throw new Error("application of nonfunction"); }
		public V Z(V arg) { throw new Error("application of nonfunction"); }
		public int O()    { return 0;  }
		public boolean Q() { return false; }
		public N E() { nEvals++; return this; }
		public V I(String x) { throw new java.lang.Error("N.I(" + x + ")"); }
		// final public T mem$a;
		// private N(T x) { this.mem$a = x; }
		// public T U() { return this.mem$a; }
		// static public N B(T v) { return new N(v); }
		// static public frege.Prelude.Maybe M(T v) { return v==null? Nothing : Just B(v) }
	}

	/**
	 * The common superclass of all array types that hold references
	 * This is good for arrays of native reference types or frege types
	 */
	static public class Arr<T> extends N {
		final public T[] X;
		// private Arr(int n)   { this.$cv = new V[n]; }
		public Arr(T[] v)   { this.X = v; }
		final public T[] U() { return this.X; }
		// final public static<T> Arr<T> B(T[] v) { return new Arr<T>(v); }
		final public static V[] vanewV(int i) { return  (new V[i]); }
		final public static java.lang.String[] vanewString(int i) { return  (new java.lang.String[i]); }
		final public static java.net.URL[]     vanewURL(int i)    { return  (new java.net.URL[i]); }
		final public static<T> T[] vaupd(T[] arr, int i, T v) {
			T[] r = arr.clone();
			r[i] = v;
			return r;
		}
		final public static<T> void vaset(T[] arr, int i, T v) { arr[i] = v; }
		final public static<T> T vaget(T[] arr, int i) { return arr[i]; }
		final public static<T> int valen(T[] arr) { return arr.length; }
		public String toS(int n) {
		    if (n < 1) return "...";
		    return X.toString();
		}
	}

	/**
	 * A delayed computation that will be performed by E(), possibly
	 * returning another delayed value
	 *
	 * To be used like this
	 *   new D() { public V E() { return a V } }
	 */
	abstract static public class D extends V {
		abstract public V E();
		public boolean Q() { return true; }
		public V I(String x) { throw new java.lang.Error("D.I("+ x + ")"); }
		public V A(V arg) { throw new Error("application of delayed value"); }
		public V Z(V arg) { throw new Error("application of delayed value"); }
		public int O() { return E().O(); }
		// public D() {}
	}

	/**
	 * A delayed application
	 *
	static public class FD extends D {
		final private F fun;
		final V arg;

		public V E() {
			return fun.A(arg);
		}
		public FD(final F f, V a) { fun = f; arg = a; }
	}
	*/


	/**
	 * A lazy computation.
	 * Laziness arises in function application only.
	 * Whenever the last argument to a function is passed through
	 *    fun.Z(data)
	 * this returns
	 *    new L(fun, data)
	 * instead of actually evaluating the function.
	 * The value may later be evaluated with l.E()
	 * An L can be in 2 states:
	 *  1. (not yet evaluated) fun != null && data != null
	 *  2. (evaluated)         fun == null
	 * After construction, the L is in state 1.
	 * After evaluation it is in state 2 where data holds the result.
	 * When E() returns, data is guaranteed to be a subclass of F, C or N
	 *
	 */
	static public final class L extends V
	        implements java.util.concurrent.Callable<V> {
	    final static class LStack {
	        final private LStack next;
	        final private L      data;
	        public LStack(LStack old, L datum) { next = old; data = datum; }
	        public L      dat()  { return data; }
	        public LStack pop()  { return next; }
	    }

	    public static long lEvals   = 0;
	    public static long rEvals   = 0;

		private boolean blackhole = false;
		private F fun  = null;
		private V data = null;
		public boolean Q() { return fun != null /*? true : data instanceof L */; }
		public V call() throws Exception { return E(); }
		public String toS(int n) {
		    if (n < 1) return "...";
		    if (fun == null) return "(" + data.toS(n) + ")";
		    return "(" + fun.toS(n-1) + " $ " + data.toS(n-1) + ")";
		}
		final public V E() {
		    L dies = this;
		    LStack stack = null;
		    loop: while (true) synchronized (dies) {
    		    if (dies.fun != null) {
    		        if (dies.blackhole) throw new BlackHole(dies);
		            dies.blackhole = true;
    		        try {
    		            dies.data = dies.fun.A(dies.data);
    		            lEvals++;
    		        } catch (Trampolin t) {
    		            dies.blackhole = false;
    		            stack = new LStack(stack, dies);   // push this
    		            dies = t.subex;
    		            continue loop;
    		        } catch  (Longjump l) {
    		            throw l;
    		        } catch  (frege.Run.PostMortem pmx) {
                        java.lang.System.err.println("called during evaluation of " + dies);
                        while (stack != null) {
                            dies  = stack.dat();
                            stack = stack.pop();
                            java.lang.System.err.println("caused by evaluation of " + dies);
                        }
                        throw pmx;
                    } catch  (java.lang.Exception rte) {
                        java.lang.System.err.println((rte.getClass().getName()+": ")+rte.getMessage());
                        java.lang.System.err.println("catched during evaluation of " + dies);
                        while (stack != null) {
                            dies  = stack.dat();
                            stack = stack.pop();
                            java.lang.System.err.println("caused by evaluation of " + dies);
                        }
                        throw new frege.Run.PostMortem(rte);
                    }
    		            /*
    		        } catch (InterruptedException iex) {
    		            throw new Undefined("interrupted ", iex.getCause());
    		        } catch (java.util.concurrent.ExecutionException eex) {
    		            throw new Undefined("undefined result in subtask while evaluating " + dies, eex.getCause());
    		        }
    		        */
    		        dies.blackhole = false;
    		        dies.fun = null;
    		    }
    		    else rEvals++;
    		    assert dies.fun == null && !(dies.blackhole);
    		    // the evaluation might have returned another lazy value
			    while (dies.data instanceof L) {
			        // is this possible?
			        if (dies.data == dies)  throw new BlackHole(dies);
				    else 			        dies.data = dies.data.E();
			    }
			    if (stack == null) break loop;
			    dies  = stack.dat();
			    stack = stack.pop();
			}
			// the stack is empty,
			//   and we return what we were asked for
			//   and it is not null and properly evaluated
			assert stack     == null
			    && dies      == this
			    && !(dies.blackhole)
			    && dies.fun  == null
			    && dies.data != null
			    && !(dies.data instanceof L);
			return dies.data;
		}
		public V I(String x) { return E().I(x); }
		public int O() { return E().O(); }
		public L(final F f, final V a) { fun = f; data = a; }
		final public V A(V arg) throws Trampolin,
		                                InterruptedException,
		                                java.util.concurrent.ExecutionException
		                                { return E().A(arg); }
		final public V Z(V arg) { return E().Z(arg); }
	}

	/**
	 * This is the superclass of all frege functions as well as lambdas.
	 * There will be subclasses F1 through F26 for all arities.
	 * \a{x} will be an F1 if x is not a lambda
	 * \a\b{x} will be an F2, and so forth
	 *
	 *  This seemingly complicated mechanism helps to reduce the dozens of
	 *  anonymous subclasses that would occur otherwise.
	 *  Note that if lambdas appear other than as top-expression or as result
	 *  in another lambda, an extra F1 subclass is needed because of closures.
	 */
	abstract public static class F extends V {
		// protected boolean lazy = false;
		// protected int     count = 0;
		public boolean Q() { return false; }
		public int O()    { throw new Error("function has no constructor");  }
		/**
		 * @see frege.Run.V#E()
		 * note that E() does not evaluate the function!
		 */
		public F E() { return this; }

		/**
		 * applies this to arg
		 * @param arg
		 * @return the result of the function application (F, L or V)
		 */
		// public abstract V A(final V arg) throws Trampolin;

		/**
		 * delays application of this to arg
		 * The following holds: f.Z(arg).E() == f.a(arg).E() can be casted
		 * to C, F or N
		 * @param arg
		 * @return a L(azy value), which, when evaluated, does this.a(arg).v()
		 */
		public abstract V Z(final V arg);
		public static java.util.HashMap<java.lang.String, frege.Run.V> H = new java.util.HashMap<java.lang.String, frege.Run.V>();
        public V I(final java.lang.String fun) {
          final V res = H.get(fun);
          if (res != null) return res;
          throw new InstanceMethodMissing(this, fun);
        }
	}

	/**
	 * print statistics
	 */
	public static void pstats() {
	    System.err.print("boxes: " + N.boxCount);
	    long evals = C.cEvals + N.nEvals + L.lEvals + L.rEvals;
	    System.err.print(", evals: " + evals
	        + " (nat "    + N.nEvals * 100 / evals
	        + "%, alg "   + C.cEvals * 100 / evals
	        + "%, lazy "  + L.lEvals * 100 / evals
	        + "%, re "    + L.rEvals * 100 / evals
	        + "%)  ");
	}

	/* the rest of this file was generated by frege/genF.pl */

	abstract static public class F1 extends F {
	 public final F1  E() { return this; }
	 public F1() { }
	 public V A(final V a1) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	  return F1.this.eval(a1);
	 }
	 public L Z(final V a1) {
	  return new L(this, a1);
	 }
	 public abstract V eval(final V a1) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F2 extends F {
	 public final F2  E() { return this; }
	 public F2() { }
	 public F1 A(final V a1)  {
	  return new F1() {
	  public V A(final V a2) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	   return F2.this.eval(a1, a2);
	  }
	  public L Z(final V a2) {
	   return new L(this, a2);
	  }
	  public V eval(V a2) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F2.this.toS(n); return "("+F2.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F1 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F3 extends F {
	 public final F3  E() { return this; }
	 public F3() { }
	 public F2 A(final V a1)  {
	  return new F2() {
	  public F1 A(final V a2)  {
	   return new F1() {
	   public V A(final V a3) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	    return F3.this.eval(a1, a2, a3);
	   }
	   public L Z(final V a3) {
	    return new L(this, a3);
	   }
	   public V eval(V a3) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F3.this.toS(n); return "("+F3.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F1 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F3.this.toS(n); return "("+F3.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F2 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F4 extends F {
	 public final F4  E() { return this; }
	 public F4() { }
	 public F3 A(final V a1)  {
	  return new F3() {
	  public F2 A(final V a2)  {
	   return new F2() {
	   public F1 A(final V a3)  {
	    return new F1() {
	    public V A(final V a4) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	     return F4.this.eval(a1, a2, a3, a4);
	    }
	    public L Z(final V a4) {
	     return new L(this, a4);
	    }
	    public V eval(V a4) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F4.this.toS(n); return "("+F4.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F1 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F4.this.toS(n); return "("+F4.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F2 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F4.this.toS(n); return "("+F4.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F3 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F5 extends F {
	 public final F5  E() { return this; }
	 public F5() { }
	 public F4 A(final V a1)  {
	  return new F4() {
	  public F3 A(final V a2)  {
	   return new F3() {
	   public F2 A(final V a3)  {
	    return new F2() {
	    public F1 A(final V a4)  {
	     return new F1() {
	     public V A(final V a5) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	      return F5.this.eval(a1, a2, a3, a4, a5);
	     }
	     public L Z(final V a5) {
	      return new L(this, a5);
	     }
	     public V eval(V a5) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F5.this.toS(n); return "("+F5.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F1 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F5.this.toS(n); return "("+F5.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F2 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F5.this.toS(n); return "("+F5.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F3 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F5.this.toS(n); return "("+F5.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F4 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F6 extends F {
	 public final F6  E() { return this; }
	 public F6() { }
	 public F5 A(final V a1)  {
	  return new F5() {
	  public F4 A(final V a2)  {
	   return new F4() {
	   public F3 A(final V a3)  {
	    return new F3() {
	    public F2 A(final V a4)  {
	     return new F2() {
	     public F1 A(final V a5)  {
	      return new F1() {
	      public V A(final V a6) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	       return F6.this.eval(a1, a2, a3, a4, a5, a6);
	      }
	      public L Z(final V a6) {
	       return new L(this, a6);
	      }
	      public V eval(V a6) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F6.this.toS(n); return "("+F6.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F1 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F6.this.toS(n); return "("+F6.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F2 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F6.this.toS(n); return "("+F6.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F3 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F6.this.toS(n); return "("+F6.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F4 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F6.this.toS(n); return "("+F6.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F5 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F7 extends F {
	 public final F7  E() { return this; }
	 public F7() { }
	 public F6 A(final V a1)  {
	  return new F6() {
	  public F5 A(final V a2)  {
	   return new F5() {
	   public F4 A(final V a3)  {
	    return new F4() {
	    public F3 A(final V a4)  {
	     return new F3() {
	     public F2 A(final V a5)  {
	      return new F2() {
	      public F1 A(final V a6)  {
	       return new F1() {
	       public V A(final V a7) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	        return F7.this.eval(a1, a2, a3, a4, a5, a6, a7);
	       }
	       public L Z(final V a7) {
	        return new L(this, a7);
	       }
	       public V eval(V a7) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F1 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F2 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F3 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F4 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F5 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F7.this.toS(n); return "("+F7.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F6 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F8 extends F {
	 public final F8  E() { return this; }
	 public F8() { }
	 public F7 A(final V a1)  {
	  return new F7() {
	  public F6 A(final V a2)  {
	   return new F6() {
	   public F5 A(final V a3)  {
	    return new F5() {
	    public F4 A(final V a4)  {
	     return new F4() {
	     public F3 A(final V a5)  {
	      return new F3() {
	      public F2 A(final V a6)  {
	       return new F2() {
	       public F1 A(final V a7)  {
	        return new F1() {
	        public V A(final V a8) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	         return F8.this.eval(a1, a2, a3, a4, a5, a6, a7, a8);
	        }
	        public L Z(final V a8) {
	         return new L(this, a8);
	        }
	        public V eval(V a8) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F1 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F2 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F3 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F4 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F5 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F6 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F8.this.toS(n); return "("+F8.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F7 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F9 extends F {
	 public final F9  E() { return this; }
	 public F9() { }
	 public F8 A(final V a1)  {
	  return new F8() {
	  public F7 A(final V a2)  {
	   return new F7() {
	   public F6 A(final V a3)  {
	    return new F6() {
	    public F5 A(final V a4)  {
	     return new F5() {
	     public F4 A(final V a5)  {
	      return new F4() {
	      public F3 A(final V a6)  {
	       return new F3() {
	       public F2 A(final V a7)  {
	        return new F2() {
	        public F1 A(final V a8)  {
	         return new F1() {
	         public V A(final V a9) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	          return F9.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9);
	         }
	         public L Z(final V a9) {
	          return new L(this, a9);
	         }
	         public V eval(V a9) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F1 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F2 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F3 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F4 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F5 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F6 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F7 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F9.this.toS(n); return "("+F9.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F8 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F10 extends F {
	 public final F10  E() { return this; }
	 public F10() { }
	 public F9 A(final V a1)  {
	  return new F9() {
	  public F8 A(final V a2)  {
	   return new F8() {
	   public F7 A(final V a3)  {
	    return new F7() {
	    public F6 A(final V a4)  {
	     return new F6() {
	     public F5 A(final V a5)  {
	      return new F5() {
	      public F4 A(final V a6)  {
	       return new F4() {
	       public F3 A(final V a7)  {
	        return new F3() {
	        public F2 A(final V a8)  {
	         return new F2() {
	         public F1 A(final V a9)  {
	          return new F1() {
	          public V A(final V a10) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	           return F10.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);
	          }
	          public L Z(final V a10) {
	           return new L(this, a10);
	          }
	          public V eval(V a10) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F1 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F2 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F3 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F4 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F5 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F6 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F7 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F8 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F10.this.toS(n); return "("+F10.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F9 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F11 extends F {
	 public final F11  E() { return this; }
	 public F11() { }
	 public F10 A(final V a1)  {
	  return new F10() {
	  public F9 A(final V a2)  {
	   return new F9() {
	   public F8 A(final V a3)  {
	    return new F8() {
	    public F7 A(final V a4)  {
	     return new F7() {
	     public F6 A(final V a5)  {
	      return new F6() {
	      public F5 A(final V a6)  {
	       return new F5() {
	       public F4 A(final V a7)  {
	        return new F4() {
	        public F3 A(final V a8)  {
	         return new F3() {
	         public F2 A(final V a9)  {
	          return new F2() {
	          public F1 A(final V a10)  {
	           return new F1() {
	           public V A(final V a11) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	            return F11.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
	           }
	           public L Z(final V a11) {
	            return new L(this, a11);
	           }
	           public V eval(V a11) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F1 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F2 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F3 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F4 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F5 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F6 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F7 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F8 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F9 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F11.this.toS(n); return "("+F11.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F10 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F12 extends F {
	 public final F12  E() { return this; }
	 public F12() { }
	 public F11 A(final V a1)  {
	  return new F11() {
	  public F10 A(final V a2)  {
	   return new F10() {
	   public F9 A(final V a3)  {
	    return new F9() {
	    public F8 A(final V a4)  {
	     return new F8() {
	     public F7 A(final V a5)  {
	      return new F7() {
	      public F6 A(final V a6)  {
	       return new F6() {
	       public F5 A(final V a7)  {
	        return new F5() {
	        public F4 A(final V a8)  {
	         return new F4() {
	         public F3 A(final V a9)  {
	          return new F3() {
	          public F2 A(final V a10)  {
	           return new F2() {
	           public F1 A(final V a11)  {
	            return new F1() {
	            public V A(final V a12) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	             return F12.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12);
	            }
	            public L Z(final V a12) {
	             return new L(this, a12);
	            }
	            public V eval(V a12) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F1 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F2 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F3 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F4 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F5 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F6 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F7 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F8 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F9 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F10 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F12.this.toS(n); return "("+F12.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F11 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F13 extends F {
	 public final F13  E() { return this; }
	 public F13() { }
	 public F12 A(final V a1)  {
	  return new F12() {
	  public F11 A(final V a2)  {
	   return new F11() {
	   public F10 A(final V a3)  {
	    return new F10() {
	    public F9 A(final V a4)  {
	     return new F9() {
	     public F8 A(final V a5)  {
	      return new F8() {
	      public F7 A(final V a6)  {
	       return new F7() {
	       public F6 A(final V a7)  {
	        return new F6() {
	        public F5 A(final V a8)  {
	         return new F5() {
	         public F4 A(final V a9)  {
	          return new F4() {
	          public F3 A(final V a10)  {
	           return new F3() {
	           public F2 A(final V a11)  {
	            return new F2() {
	            public F1 A(final V a12)  {
	             return new F1() {
	             public V A(final V a13) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	              return F13.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13);
	             }
	             public L Z(final V a13) {
	              return new L(this, a13);
	             }
	             public V eval(V a13) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F1 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F2 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F3 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F4 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F5 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F6 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F7 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F8 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F9 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F10 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F11 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F13.this.toS(n); return "("+F13.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F12 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F14 extends F {
	 public final F14  E() { return this; }
	 public F14() { }
	 public F13 A(final V a1)  {
	  return new F13() {
	  public F12 A(final V a2)  {
	   return new F12() {
	   public F11 A(final V a3)  {
	    return new F11() {
	    public F10 A(final V a4)  {
	     return new F10() {
	     public F9 A(final V a5)  {
	      return new F9() {
	      public F8 A(final V a6)  {
	       return new F8() {
	       public F7 A(final V a7)  {
	        return new F7() {
	        public F6 A(final V a8)  {
	         return new F6() {
	         public F5 A(final V a9)  {
	          return new F5() {
	          public F4 A(final V a10)  {
	           return new F4() {
	           public F3 A(final V a11)  {
	            return new F3() {
	            public F2 A(final V a12)  {
	             return new F2() {
	             public F1 A(final V a13)  {
	              return new F1() {
	              public V A(final V a14) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	               return F14.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14);
	              }
	              public L Z(final V a14) {
	               return new L(this, a14);
	              }
	              public V eval(V a14) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F1 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F2 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F3 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F4 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F5 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F6 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F7 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F8 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F9 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F10 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F11 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F12 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F14.this.toS(n); return "("+F14.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F13 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F15 extends F {
	 public final F15  E() { return this; }
	 public F15() { }
	 public F14 A(final V a1)  {
	  return new F14() {
	  public F13 A(final V a2)  {
	   return new F13() {
	   public F12 A(final V a3)  {
	    return new F12() {
	    public F11 A(final V a4)  {
	     return new F11() {
	     public F10 A(final V a5)  {
	      return new F10() {
	      public F9 A(final V a6)  {
	       return new F9() {
	       public F8 A(final V a7)  {
	        return new F8() {
	        public F7 A(final V a8)  {
	         return new F7() {
	         public F6 A(final V a9)  {
	          return new F6() {
	          public F5 A(final V a10)  {
	           return new F5() {
	           public F4 A(final V a11)  {
	            return new F4() {
	            public F3 A(final V a12)  {
	             return new F3() {
	             public F2 A(final V a13)  {
	              return new F2() {
	              public F1 A(final V a14)  {
	               return new F1() {
	               public V A(final V a15) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                return F15.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15);
	               }
	               public L Z(final V a15) {
	                return new L(this, a15);
	               }
	               public V eval(V a15) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F1 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F2 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F3 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F4 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F5 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F6 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F7 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F8 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F9 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F10 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F11 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F12 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F13 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F15.this.toS(n); return "("+F15.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F14 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F16 extends F {
	 public final F16  E() { return this; }
	 public F16() { }
	 public F15 A(final V a1)  {
	  return new F15() {
	  public F14 A(final V a2)  {
	   return new F14() {
	   public F13 A(final V a3)  {
	    return new F13() {
	    public F12 A(final V a4)  {
	     return new F12() {
	     public F11 A(final V a5)  {
	      return new F11() {
	      public F10 A(final V a6)  {
	       return new F10() {
	       public F9 A(final V a7)  {
	        return new F9() {
	        public F8 A(final V a8)  {
	         return new F8() {
	         public F7 A(final V a9)  {
	          return new F7() {
	          public F6 A(final V a10)  {
	           return new F6() {
	           public F5 A(final V a11)  {
	            return new F5() {
	            public F4 A(final V a12)  {
	             return new F4() {
	             public F3 A(final V a13)  {
	              return new F3() {
	              public F2 A(final V a14)  {
	               return new F2() {
	               public F1 A(final V a15)  {
	                return new F1() {
	                public V A(final V a16) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                 return F16.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16);
	                }
	                public L Z(final V a16) {
	                 return new L(this, a16);
	                }
	                public V eval(V a16) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F1 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F2 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F3 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F4 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F5 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F6 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F7 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F8 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F9 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F10 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F11 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F12 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F13 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F14 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F16.this.toS(n); return "("+F16.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F15 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F17 extends F {
	 public final F17  E() { return this; }
	 public F17() { }
	 public F16 A(final V a1)  {
	  return new F16() {
	  public F15 A(final V a2)  {
	   return new F15() {
	   public F14 A(final V a3)  {
	    return new F14() {
	    public F13 A(final V a4)  {
	     return new F13() {
	     public F12 A(final V a5)  {
	      return new F12() {
	      public F11 A(final V a6)  {
	       return new F11() {
	       public F10 A(final V a7)  {
	        return new F10() {
	        public F9 A(final V a8)  {
	         return new F9() {
	         public F8 A(final V a9)  {
	          return new F8() {
	          public F7 A(final V a10)  {
	           return new F7() {
	           public F6 A(final V a11)  {
	            return new F6() {
	            public F5 A(final V a12)  {
	             return new F5() {
	             public F4 A(final V a13)  {
	              return new F4() {
	              public F3 A(final V a14)  {
	               return new F3() {
	               public F2 A(final V a15)  {
	                return new F2() {
	                public F1 A(final V a16)  {
	                 return new F1() {
	                 public V A(final V a17) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                  return F17.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17);
	                 }
	                 public L Z(final V a17) {
	                  return new L(this, a17);
	                 }
	                 public V eval(V a17) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F1 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F2 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F3 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F4 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F5 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F6 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F7 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F8 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F9 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F10 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F11 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F12 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F13 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F14 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F15 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F17.this.toS(n); return "("+F17.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F16 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F18 extends F {
	 public final F18  E() { return this; }
	 public F18() { }
	 public F17 A(final V a1)  {
	  return new F17() {
	  public F16 A(final V a2)  {
	   return new F16() {
	   public F15 A(final V a3)  {
	    return new F15() {
	    public F14 A(final V a4)  {
	     return new F14() {
	     public F13 A(final V a5)  {
	      return new F13() {
	      public F12 A(final V a6)  {
	       return new F12() {
	       public F11 A(final V a7)  {
	        return new F11() {
	        public F10 A(final V a8)  {
	         return new F10() {
	         public F9 A(final V a9)  {
	          return new F9() {
	          public F8 A(final V a10)  {
	           return new F8() {
	           public F7 A(final V a11)  {
	            return new F7() {
	            public F6 A(final V a12)  {
	             return new F6() {
	             public F5 A(final V a13)  {
	              return new F5() {
	              public F4 A(final V a14)  {
	               return new F4() {
	               public F3 A(final V a15)  {
	                return new F3() {
	                public F2 A(final V a16)  {
	                 return new F2() {
	                 public F1 A(final V a17)  {
	                  return new F1() {
	                  public V A(final V a18) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                   return F18.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18);
	                  }
	                  public L Z(final V a18) {
	                   return new L(this, a18);
	                  }
	                  public V eval(V a18) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F1 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F2 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F3 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F4 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F5 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F6 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F7 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F8 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F9 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F10 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F11 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F12 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F13 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F14 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F15 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F16 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F18.this.toS(n); return "("+F18.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F17 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F19 extends F {
	 public final F19  E() { return this; }
	 public F19() { }
	 public F18 A(final V a1)  {
	  return new F18() {
	  public F17 A(final V a2)  {
	   return new F17() {
	   public F16 A(final V a3)  {
	    return new F16() {
	    public F15 A(final V a4)  {
	     return new F15() {
	     public F14 A(final V a5)  {
	      return new F14() {
	      public F13 A(final V a6)  {
	       return new F13() {
	       public F12 A(final V a7)  {
	        return new F12() {
	        public F11 A(final V a8)  {
	         return new F11() {
	         public F10 A(final V a9)  {
	          return new F10() {
	          public F9 A(final V a10)  {
	           return new F9() {
	           public F8 A(final V a11)  {
	            return new F8() {
	            public F7 A(final V a12)  {
	             return new F7() {
	             public F6 A(final V a13)  {
	              return new F6() {
	              public F5 A(final V a14)  {
	               return new F5() {
	               public F4 A(final V a15)  {
	                return new F4() {
	                public F3 A(final V a16)  {
	                 return new F3() {
	                 public F2 A(final V a17)  {
	                  return new F2() {
	                  public F1 A(final V a18)  {
	                   return new F1() {
	                   public V A(final V a19) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                    return F19.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19);
	                   }
	                   public L Z(final V a19) {
	                    return new L(this, a19);
	                   }
	                   public V eval(V a19) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F1 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F2 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F3 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F4 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F5 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F6 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F7 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F8 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F9 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F10 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F11 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F12 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F13 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F14 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F15 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F16 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F17 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F19.this.toS(n); return "("+F19.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F18 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F20 extends F {
	 public final F20  E() { return this; }
	 public F20() { }
	 public F19 A(final V a1)  {
	  return new F19() {
	  public F18 A(final V a2)  {
	   return new F18() {
	   public F17 A(final V a3)  {
	    return new F17() {
	    public F16 A(final V a4)  {
	     return new F16() {
	     public F15 A(final V a5)  {
	      return new F15() {
	      public F14 A(final V a6)  {
	       return new F14() {
	       public F13 A(final V a7)  {
	        return new F13() {
	        public F12 A(final V a8)  {
	         return new F12() {
	         public F11 A(final V a9)  {
	          return new F11() {
	          public F10 A(final V a10)  {
	           return new F10() {
	           public F9 A(final V a11)  {
	            return new F9() {
	            public F8 A(final V a12)  {
	             return new F8() {
	             public F7 A(final V a13)  {
	              return new F7() {
	              public F6 A(final V a14)  {
	               return new F6() {
	               public F5 A(final V a15)  {
	                return new F5() {
	                public F4 A(final V a16)  {
	                 return new F4() {
	                 public F3 A(final V a17)  {
	                  return new F3() {
	                  public F2 A(final V a18)  {
	                   return new F2() {
	                   public F1 A(final V a19)  {
	                    return new F1() {
	                    public V A(final V a20) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                     return F20.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20);
	                    }
	                    public L Z(final V a20) {
	                     return new L(this, a20);
	                    }
	                    public V eval(V a20) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F1 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F2 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F3 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F4 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F5 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F6 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F7 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F8 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F9 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F10 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F11 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F12 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F13 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F14 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F15 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F16 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F17 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F18 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F20.this.toS(n); return "("+F20.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F19 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F21 extends F {
	 public final F21  E() { return this; }
	 public F21() { }
	 public F20 A(final V a1)  {
	  return new F20() {
	  public F19 A(final V a2)  {
	   return new F19() {
	   public F18 A(final V a3)  {
	    return new F18() {
	    public F17 A(final V a4)  {
	     return new F17() {
	     public F16 A(final V a5)  {
	      return new F16() {
	      public F15 A(final V a6)  {
	       return new F15() {
	       public F14 A(final V a7)  {
	        return new F14() {
	        public F13 A(final V a8)  {
	         return new F13() {
	         public F12 A(final V a9)  {
	          return new F12() {
	          public F11 A(final V a10)  {
	           return new F11() {
	           public F10 A(final V a11)  {
	            return new F10() {
	            public F9 A(final V a12)  {
	             return new F9() {
	             public F8 A(final V a13)  {
	              return new F8() {
	              public F7 A(final V a14)  {
	               return new F7() {
	               public F6 A(final V a15)  {
	                return new F6() {
	                public F5 A(final V a16)  {
	                 return new F5() {
	                 public F4 A(final V a17)  {
	                  return new F4() {
	                  public F3 A(final V a18)  {
	                   return new F3() {
	                   public F2 A(final V a19)  {
	                    return new F2() {
	                    public F1 A(final V a20)  {
	                     return new F1() {
	                     public V A(final V a21) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                      return F21.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21);
	                     }
	                     public L Z(final V a21) {
	                      return new L(this, a21);
	                     }
	                     public V eval(V a21) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F1 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F2 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F3 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F4 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F5 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F6 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F7 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F8 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F9 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F10 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F11 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F12 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F13 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F14 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F15 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F16 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F17 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F18 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F19 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F21.this.toS(n); return "("+F21.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F20 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F22 extends F {
	 public final F22  E() { return this; }
	 public F22() { }
	 public F21 A(final V a1)  {
	  return new F21() {
	  public F20 A(final V a2)  {
	   return new F20() {
	   public F19 A(final V a3)  {
	    return new F19() {
	    public F18 A(final V a4)  {
	     return new F18() {
	     public F17 A(final V a5)  {
	      return new F17() {
	      public F16 A(final V a6)  {
	       return new F16() {
	       public F15 A(final V a7)  {
	        return new F15() {
	        public F14 A(final V a8)  {
	         return new F14() {
	         public F13 A(final V a9)  {
	          return new F13() {
	          public F12 A(final V a10)  {
	           return new F12() {
	           public F11 A(final V a11)  {
	            return new F11() {
	            public F10 A(final V a12)  {
	             return new F10() {
	             public F9 A(final V a13)  {
	              return new F9() {
	              public F8 A(final V a14)  {
	               return new F8() {
	               public F7 A(final V a15)  {
	                return new F7() {
	                public F6 A(final V a16)  {
	                 return new F6() {
	                 public F5 A(final V a17)  {
	                  return new F5() {
	                  public F4 A(final V a18)  {
	                   return new F4() {
	                   public F3 A(final V a19)  {
	                    return new F3() {
	                    public F2 A(final V a20)  {
	                     return new F2() {
	                     public F1 A(final V a21)  {
	                      return new F1() {
	                      public V A(final V a22) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                       return F22.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22);
	                      }
	                      public L Z(final V a22) {
	                       return new L(this, a22);
	                      }
	                      public V eval(V a22) { throw new Error("inner eval 22"); }
	                      public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+")"; }
	                      };
	                     }
	                     public F1 Z(final V a21) { return this.A(a21); }
	                     public V eval(V a21, V a22) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F2 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21, V a22) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F3 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21, V a22) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F4 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F5 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F6 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F7 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F8 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F9 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F10 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F11 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F12 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F13 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F14 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F15 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F16 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F17 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F18 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F19 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F20 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F22.this.toS(n); return "("+F22.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F21 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21, final V a22) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F23 extends F {
	 public final F23  E() { return this; }
	 public F23() { }
	 public F22 A(final V a1)  {
	  return new F22() {
	  public F21 A(final V a2)  {
	   return new F21() {
	   public F20 A(final V a3)  {
	    return new F20() {
	    public F19 A(final V a4)  {
	     return new F19() {
	     public F18 A(final V a5)  {
	      return new F18() {
	      public F17 A(final V a6)  {
	       return new F17() {
	       public F16 A(final V a7)  {
	        return new F16() {
	        public F15 A(final V a8)  {
	         return new F15() {
	         public F14 A(final V a9)  {
	          return new F14() {
	          public F13 A(final V a10)  {
	           return new F13() {
	           public F12 A(final V a11)  {
	            return new F12() {
	            public F11 A(final V a12)  {
	             return new F11() {
	             public F10 A(final V a13)  {
	              return new F10() {
	              public F9 A(final V a14)  {
	               return new F9() {
	               public F8 A(final V a15)  {
	                return new F8() {
	                public F7 A(final V a16)  {
	                 return new F7() {
	                 public F6 A(final V a17)  {
	                  return new F6() {
	                  public F5 A(final V a18)  {
	                   return new F5() {
	                   public F4 A(final V a19)  {
	                    return new F4() {
	                    public F3 A(final V a20)  {
	                     return new F3() {
	                     public F2 A(final V a21)  {
	                      return new F2() {
	                      public F1 A(final V a22)  {
	                       return new F1() {
	                       public V A(final V a23) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                        return F23.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23);
	                       }
	                       public L Z(final V a23) {
	                        return new L(this, a23);
	                       }
	                       public V eval(V a23) { throw new Error("inner eval 23"); }
	                       public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+")"; }
	                       };
	                      }
	                      public F1 Z(final V a22) { return this.A(a22); }
	                      public V eval(V a22, V a23) { throw new Error("inner eval 22"); }
	                      public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+")"; }
	                      };
	                     }
	                     public F2 Z(final V a21) { return this.A(a21); }
	                     public V eval(V a21, V a22, V a23) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F3 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21, V a22, V a23) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F4 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F5 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F6 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F7 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F8 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F9 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F10 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F11 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F12 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F13 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F14 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F15 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F16 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F17 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F18 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F19 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F20 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F21 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F23.this.toS(n); return "("+F23.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F22 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21, final V a22, final V a23) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F24 extends F {
	 public final F24  E() { return this; }
	 public F24() { }
	 public F23 A(final V a1)  {
	  return new F23() {
	  public F22 A(final V a2)  {
	   return new F22() {
	   public F21 A(final V a3)  {
	    return new F21() {
	    public F20 A(final V a4)  {
	     return new F20() {
	     public F19 A(final V a5)  {
	      return new F19() {
	      public F18 A(final V a6)  {
	       return new F18() {
	       public F17 A(final V a7)  {
	        return new F17() {
	        public F16 A(final V a8)  {
	         return new F16() {
	         public F15 A(final V a9)  {
	          return new F15() {
	          public F14 A(final V a10)  {
	           return new F14() {
	           public F13 A(final V a11)  {
	            return new F13() {
	            public F12 A(final V a12)  {
	             return new F12() {
	             public F11 A(final V a13)  {
	              return new F11() {
	              public F10 A(final V a14)  {
	               return new F10() {
	               public F9 A(final V a15)  {
	                return new F9() {
	                public F8 A(final V a16)  {
	                 return new F8() {
	                 public F7 A(final V a17)  {
	                  return new F7() {
	                  public F6 A(final V a18)  {
	                   return new F6() {
	                   public F5 A(final V a19)  {
	                    return new F5() {
	                    public F4 A(final V a20)  {
	                     return new F4() {
	                     public F3 A(final V a21)  {
	                      return new F3() {
	                      public F2 A(final V a22)  {
	                       return new F2() {
	                       public F1 A(final V a23)  {
	                        return new F1() {
	                        public V A(final V a24) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                         return F24.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24);
	                        }
	                        public L Z(final V a24) {
	                         return new L(this, a24);
	                        }
	                        public V eval(V a24) { throw new Error("inner eval 24"); }
	                        public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+")"; }
	                        };
	                       }
	                       public F1 Z(final V a23) { return this.A(a23); }
	                       public V eval(V a23, V a24) { throw new Error("inner eval 23"); }
	                       public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+")"; }
	                       };
	                      }
	                      public F2 Z(final V a22) { return this.A(a22); }
	                      public V eval(V a22, V a23, V a24) { throw new Error("inner eval 22"); }
	                      public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+")"; }
	                      };
	                     }
	                     public F3 Z(final V a21) { return this.A(a21); }
	                     public V eval(V a21, V a22, V a23, V a24) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F4 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F5 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F6 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F7 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F8 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F9 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F10 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F11 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F12 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F13 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F14 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F15 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F16 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F17 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F18 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F19 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F20 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F21 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F22 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F24.this.toS(n); return "("+F24.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F23 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21, final V a22, final V a23, final V a24) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F25 extends F {
	 public final F25  E() { return this; }
	 public F25() { }
	 public F24 A(final V a1)  {
	  return new F24() {
	  public F23 A(final V a2)  {
	   return new F23() {
	   public F22 A(final V a3)  {
	    return new F22() {
	    public F21 A(final V a4)  {
	     return new F21() {
	     public F20 A(final V a5)  {
	      return new F20() {
	      public F19 A(final V a6)  {
	       return new F19() {
	       public F18 A(final V a7)  {
	        return new F18() {
	        public F17 A(final V a8)  {
	         return new F17() {
	         public F16 A(final V a9)  {
	          return new F16() {
	          public F15 A(final V a10)  {
	           return new F15() {
	           public F14 A(final V a11)  {
	            return new F14() {
	            public F13 A(final V a12)  {
	             return new F13() {
	             public F12 A(final V a13)  {
	              return new F12() {
	              public F11 A(final V a14)  {
	               return new F11() {
	               public F10 A(final V a15)  {
	                return new F10() {
	                public F9 A(final V a16)  {
	                 return new F9() {
	                 public F8 A(final V a17)  {
	                  return new F8() {
	                  public F7 A(final V a18)  {
	                   return new F7() {
	                   public F6 A(final V a19)  {
	                    return new F6() {
	                    public F5 A(final V a20)  {
	                     return new F5() {
	                     public F4 A(final V a21)  {
	                      return new F4() {
	                      public F3 A(final V a22)  {
	                       return new F3() {
	                       public F2 A(final V a23)  {
	                        return new F2() {
	                        public F1 A(final V a24)  {
	                         return new F1() {
	                         public V A(final V a25) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                          return F25.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25);
	                         }
	                         public L Z(final V a25) {
	                          return new L(this, a25);
	                         }
	                         public V eval(V a25) { throw new Error("inner eval 25"); }
	                         public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+' '+a24.toS(n-1)+")"; }
	                         };
	                        }
	                        public F1 Z(final V a24) { return this.A(a24); }
	                        public V eval(V a24, V a25) { throw new Error("inner eval 24"); }
	                        public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+")"; }
	                        };
	                       }
	                       public F2 Z(final V a23) { return this.A(a23); }
	                       public V eval(V a23, V a24, V a25) { throw new Error("inner eval 23"); }
	                       public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+")"; }
	                       };
	                      }
	                      public F3 Z(final V a22) { return this.A(a22); }
	                      public V eval(V a22, V a23, V a24, V a25) { throw new Error("inner eval 22"); }
	                      public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+")"; }
	                      };
	                     }
	                     public F4 Z(final V a21) { return this.A(a21); }
	                     public V eval(V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F5 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F6 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F7 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F8 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F9 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F10 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F11 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F12 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F13 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F14 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F15 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F16 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F17 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F18 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F19 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F20 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F21 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F22 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F23 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F25.this.toS(n); return "("+F25.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F24 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21, final V a22, final V a23, final V a24, final V a25) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

	abstract static public class F26 extends F {
	 public final F26  E() { return this; }
	 public F26() { }
	 public F25 A(final V a1)  {
	  return new F25() {
	  public F24 A(final V a2)  {
	   return new F24() {
	   public F23 A(final V a3)  {
	    return new F23() {
	    public F22 A(final V a4)  {
	     return new F22() {
	     public F21 A(final V a5)  {
	      return new F21() {
	      public F20 A(final V a6)  {
	       return new F20() {
	       public F19 A(final V a7)  {
	        return new F19() {
	        public F18 A(final V a8)  {
	         return new F18() {
	         public F17 A(final V a9)  {
	          return new F17() {
	          public F16 A(final V a10)  {
	           return new F16() {
	           public F15 A(final V a11)  {
	            return new F15() {
	            public F14 A(final V a12)  {
	             return new F14() {
	             public F13 A(final V a13)  {
	              return new F13() {
	              public F12 A(final V a14)  {
	               return new F12() {
	               public F11 A(final V a15)  {
	                return new F11() {
	                public F10 A(final V a16)  {
	                 return new F10() {
	                 public F9 A(final V a17)  {
	                  return new F9() {
	                  public F8 A(final V a18)  {
	                   return new F8() {
	                   public F7 A(final V a19)  {
	                    return new F7() {
	                    public F6 A(final V a20)  {
	                     return new F6() {
	                     public F5 A(final V a21)  {
	                      return new F5() {
	                      public F4 A(final V a22)  {
	                       return new F4() {
	                       public F3 A(final V a23)  {
	                        return new F3() {
	                        public F2 A(final V a24)  {
	                         return new F2() {
	                         public F1 A(final V a25)  {
	                          return new F1() {
	                          public V A(final V a26) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException  {
	                           return F26.this.eval(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26);
	                          }
	                          public L Z(final V a26) {
	                           return new L(this, a26);
	                          }
	                          public V eval(V a26) { throw new Error("inner eval 26"); }
	                          public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+' '+a24.toS(n-1)+' '+a25.toS(n-1)+")"; }
	                          };
	                         }
	                         public F1 Z(final V a25) { return this.A(a25); }
	                         public V eval(V a25, V a26) { throw new Error("inner eval 25"); }
	                         public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+' '+a24.toS(n-1)+")"; }
	                         };
	                        }
	                        public F2 Z(final V a24) { return this.A(a24); }
	                        public V eval(V a24, V a25, V a26) { throw new Error("inner eval 24"); }
	                        public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+' '+a23.toS(n-1)+")"; }
	                        };
	                       }
	                       public F3 Z(final V a23) { return this.A(a23); }
	                       public V eval(V a23, V a24, V a25, V a26) { throw new Error("inner eval 23"); }
	                       public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+' '+a22.toS(n-1)+")"; }
	                       };
	                      }
	                      public F4 Z(final V a22) { return this.A(a22); }
	                      public V eval(V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 22"); }
	                      public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+' '+a21.toS(n-1)+")"; }
	                      };
	                     }
	                     public F5 Z(final V a21) { return this.A(a21); }
	                     public V eval(V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 21"); }
	                     public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+' '+a20.toS(n-1)+")"; }
	                     };
	                    }
	                    public F6 Z(final V a20) { return this.A(a20); }
	                    public V eval(V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 20"); }
	                    public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+' '+a19.toS(n-1)+")"; }
	                    };
	                   }
	                   public F7 Z(final V a19) { return this.A(a19); }
	                   public V eval(V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 19"); }
	                   public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+' '+a18.toS(n-1)+")"; }
	                   };
	                  }
	                  public F8 Z(final V a18) { return this.A(a18); }
	                  public V eval(V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 18"); }
	                  public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+' '+a17.toS(n-1)+")"; }
	                  };
	                 }
	                 public F9 Z(final V a17) { return this.A(a17); }
	                 public V eval(V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 17"); }
	                 public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+' '+a16.toS(n-1)+")"; }
	                 };
	                }
	                public F10 Z(final V a16) { return this.A(a16); }
	                public V eval(V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 16"); }
	                public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+' '+a15.toS(n-1)+")"; }
	                };
	               }
	               public F11 Z(final V a15) { return this.A(a15); }
	               public V eval(V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 15"); }
	               public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+' '+a14.toS(n-1)+")"; }
	               };
	              }
	              public F12 Z(final V a14) { return this.A(a14); }
	              public V eval(V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 14"); }
	              public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+' '+a13.toS(n-1)+")"; }
	              };
	             }
	             public F13 Z(final V a13) { return this.A(a13); }
	             public V eval(V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 13"); }
	             public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+' '+a12.toS(n-1)+")"; }
	             };
	            }
	            public F14 Z(final V a12) { return this.A(a12); }
	            public V eval(V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 12"); }
	            public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+' '+a11.toS(n-1)+")"; }
	            };
	           }
	           public F15 Z(final V a11) { return this.A(a11); }
	           public V eval(V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 11"); }
	           public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+' '+a10.toS(n-1)+")"; }
	           };
	          }
	          public F16 Z(final V a10) { return this.A(a10); }
	          public V eval(V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 10"); }
	          public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+' '+a9.toS(n-1)+")"; }
	          };
	         }
	         public F17 Z(final V a9) { return this.A(a9); }
	         public V eval(V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 9"); }
	         public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+' '+a8.toS(n-1)+")"; }
	         };
	        }
	        public F18 Z(final V a8) { return this.A(a8); }
	        public V eval(V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 8"); }
	        public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+' '+a7.toS(n-1)+")"; }
	        };
	       }
	       public F19 Z(final V a7) { return this.A(a7); }
	       public V eval(V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 7"); }
	       public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+' '+a6.toS(n-1)+")"; }
	       };
	      }
	      public F20 Z(final V a6) { return this.A(a6); }
	      public V eval(V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 6"); }
	      public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+' '+a5.toS(n-1)+")"; }
	      };
	     }
	     public F21 Z(final V a5) { return this.A(a5); }
	     public V eval(V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 5"); }
	     public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+' '+a4.toS(n-1)+")"; }
	     };
	    }
	    public F22 Z(final V a4) { return this.A(a4); }
	    public V eval(V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 4"); }
	    public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+' '+a3.toS(n-1)+")"; }
	    };
	   }
	   public F23 Z(final V a3) { return this.A(a3); }
	   public V eval(V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 3"); }
	   public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+' '+a2.toS(n-1)+")"; }
	   };
	  }
	  public F24 Z(final V a2) { return this.A(a2); }
	  public V eval(V a2, V a3, V a4, V a5, V a6, V a7, V a8, V a9, V a10, V a11, V a12, V a13, V a14, V a15, V a16, V a17, V a18, V a19, V a20, V a21, V a22, V a23, V a24, V a25, V a26) { throw new Error("inner eval 2"); }
	  public String toS(int n) { if (n < 1) return F26.this.toS(n); return "("+F26.this.toS(n)+' '+a1.toS(n-1)+")"; }
	  };
	 }
	 public F25 Z(final V a1) { return this.A(a1); }
	 public abstract V eval(final V a1, final V a2, final V a3, final V a4, final V a5, final V a6, final V a7, final V a8, final V a9, final V a10, final V a11, final V a12, final V a13, final V a14, final V a15, final V a16, final V a17, final V a18, final V a19, final V a20, final V a21, final V a22, final V a23, final V a24, final V a25, final V a26) throws Trampolin, InterruptedException, java.util.concurrent.ExecutionException;
	}

    // a exit function that appears to return any type
    public static V exitFrege(int code) {
        System.exit(code);
        return new C();
    }
    // native stringOut frege.Run.stringOut :: String -> IO.PrintStream -> IO ()
    // We need this in YYGen.fr to write a UTF-8 byte stream to stdout
    public static void stringOut(final String in, java.io.PrintStream out) {
        try {
            byte[] arr = in.getBytes("UTF-8");
            out.write(arr, 0, arr.length);
            out.flush();
        }
        catch (java.io.UnsupportedEncodingException ex) {
            System.err.println("UTF-8 not supported? " + ex.getMessage());
        }
    }
    // wrapper functions for Matchers
    // this depends on the fact that toMatchResult effectively makes a
    // copy (yet an incomplete one) of the current matcher.
    // Since some internal arrays are not yet set up, we do usePattern(),
    // which sets up the arrays according to pattern
    // The copy can not be used to retrieve information about the last match.
    public static class Matcher {
        public static java.util.regex.Matcher clone(final java.util.regex.Matcher m) {
            return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(m.pattern());
        }
        public static java.util.regex.Matcher usePattern(final java.util.regex.Matcher m,
                                                         final java.util.regex.Pattern p) {
            return ((java.util.regex.Matcher)m.toMatchResult()).usePattern(p);
        }
        public static java.util.regex.Matcher useAnchoringBounds(final java.util.regex.Matcher m,
                                                                 final boolean b) {
            final java.util.regex.Matcher c = clone(m);
            c.useAnchoringBounds(b);
            return c;
        }
        public static java.util.regex.Matcher matches(final java.util.regex.Matcher m) {
            final java.util.regex.Matcher c = clone(m);
            if (c.matches()) return c;
            return null;
        }
        public static java.util.regex.Matcher find(final java.util.regex.Matcher m) {
            final java.util.regex.Matcher c = clone(m);
            if (c.find()) return c;
            return null;
        }
        public static java.util.regex.Matcher usePatternAndFind(final java.util.regex.Matcher m,
                                                                final java.util.regex.Pattern p) {
            final java.util.regex.Matcher c = usePattern(m, p);
            boolean emptymatch = m.start(0) == m.end(0);
            if (emptymatch)
                if (c.find(m.end(0))) return c; else return null;
            if (c.find()) return c;
            return null;
        }
        public static java.lang.String replaceFirst(final java.util.regex.Matcher m,
                                                    final java.lang.String r) {
            return clone(m).replaceFirst(r);
        }
        public static java.lang.String replaceAll(final java.util.regex.Matcher m,
                                                    final java.lang.String r) {
            return clone(m).replaceAll(r);
        }
    }

    // IORef a
    public static final class Ref {
        private V data = null;
        private Ref() {}
        public       Ref(final V it) { data = it; }
        public V     get()           { return data; }
        public void  put(final V it) { data = it; }
    }


		// helper functions for the Prelude
		public final static boolean isNull(Object x) { return x == null; }
		public final static int long2int(long l) { return (int) l; }
		public final static int die(Undefined msg) {
			if (true) throw msg;
			return 42;
		}
		public final static boolean refeq(frege.Run.V a, frege.Run.V b) {
			return a==b;
		}
		public final static boolean refne(frege.Run.V a, frege.Run.V b) {
			return a!=b;
		}
		final public static java.lang.String quoteStr(java.lang.String a) {
			java.lang.StringBuilder sr = new java.lang.StringBuilder();
			sr.append('"');
			int i = 0;
			char c;
			while (i<a.length()) {
				c = a.charAt(i++);
				if (c<' ' || c == '\177') {
					sr.append('\\');
					sr.append(java.lang.String.format("%o", (int) c));
				}
				else if (c == '\\' || c == '"') {
					sr.append('\\');
					sr.append(c);
				}
				else sr.append(c);
			}
			sr.append('"');
			return sr.toString();
		}
		public final static int ordChar(char a) {
			return (int) a;
		}
		public final static char fromChar(int a) {
			return (char) a;
		}
		public final static java.lang.String ctos(char a) {
			return ""+a;
		}
		final public static java.lang.String quoteChr(char c) {
			java.lang.StringBuilder sr = new java.lang.StringBuilder();
			sr.append("'");
			if (c<' ' || c == '\177') {
				sr.append('\\');
				sr.append(java.lang.String.format("%o", (int) c));
			}
			else if (c == '\\' || c == '\'') {
				sr.append('\\');
				sr.append(c);
			}
			else sr.append(c);
			sr.append("'");
			return sr.toString();
		}
		public final static frege.Run.V assign(frege.Run.V v, frege.Run.V a) {
			Var var = (Var) v.E();
			return (var.mem$a = a);
		}

	/* operations for arrays with primitive elements */

    public static boolean[] arrbooleannew(final int size) {return new boolean[size];}
	public static boolean   arrbooleanget(final boolean[] arr, final int inx) {return arr[inx];}
	public static boolean[] arrbooleanupd(final boolean[] arr, final int inx, final boolean upd) {
		boolean[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrbooleanset(final boolean[] arr, final int inx, final boolean set) {arr[inx] = set;}
	public static int  arrbooleanlen(final boolean[] arr) { return arr.length; }

    public static char[] arrcharnew(final int size) {return new char[size];}
	public static char   arrcharget(final char[] arr, final int inx) {return arr[inx];}
	public static char[] arrcharupd(final char[] arr, final int inx, final char upd) {
		char[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrcharset(final char[] arr, final int inx, final char set) {arr[inx] = set;}
	public static int  arrcharlen(final char[] arr) { return arr.length; }

    public static byte[] arrbytenew(final int size) {return new byte[size];}
	public static byte arrbyteget(final byte[] arr, final int inx) {return arr[inx];}
	public static byte[] arrbyteupd(final byte[] arr, final int inx, final byte upd) {
		byte[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrbyteset(final byte[] arr, final int inx, final byte set) {arr[inx] = set;}
	public static int arrbytelen(final byte[] arr) { return arr.length; }

    public static short[] arrshortnew(final int size) {return new short[size];}
	public static short   arrshortget(final short[] arr, final int inx) {return arr[inx];}
	public static short[] arrshortupd(final short[] arr, final int inx, final short upd) {
		short[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrshortset(final short[] arr, final int inx, final short set) {arr[inx] = set;}
	public static int  arrshortlen(final short[] arr) { return arr.length; }

    public static int[] arrintnew(final int size) {return new int[size];}
	public static int arrintget(final int[] arr, final int inx) {return arr[inx];}
	public static int[] arrintupd(final int[] arr, final int inx, final int upd) {
		int[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrintset(final int[] arr, final int inx, final int set) {arr[inx] = set;}
	public static int arrintlen(final int[] arr) { return arr.length; }

    public static long[] arrlongnew(final int size) {return new long[size];}
	public static long arrlongget(final long[] arr, final int inx) {return arr[inx];}
	public static long[] arrlongupd(final long[] arr, final int inx, final long upd) {
		long[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrlongset(final long[] arr, final int inx, final long set) {arr[inx] = set;}
	public static int arrlonglen(final long[] arr) { return arr.length; }

    public static float[] arrfloatnew(final int size) {return new float[size];}
	public static float   arrfloatget(final float[] arr, final int inx) {return arr[inx];}
	public static float[] arrfloatupd(final float[] arr, final int inx, final float upd) {
		float[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrfloatset(final float[] arr, final int inx, final float set) {arr[inx] = set;}
	public static int  arrfloatlen(final float[] arr) { return arr.length; }

    public static double[] arrdoublenew(final int size) {return new double[size];}
	public static double   arrdoubleget(final double[] arr, final int inx) {return arr[inx];}
	public static double[] arrdoubleupd(final double[] arr, final int inx, final double upd) {
		double[] r = arr.clone();
		r[inx] = upd;
		return r;
	}
	public static void arrdoubleset(final double[] arr, final int inx, final double set) {arr[inx] = set;}
	public static int  arrdoublelen(final double[] arr) { return arr.length; }

    /**
     * this is a means to start frege-Programs with more stack space under Java 5:
     *  java -Xss1m -cp .... frege.Run your.frege.Class arg1 ... argn
     */
    public static void main(final String args[]) {
            int runindex = 0;
            int stacksz = 0;
            if (args.length > 0 && args[0].equals("-p")) {
                async = java.util.concurrent.Executors.newCachedThreadPool();
                // System.err.println(async.getClass().getName());
                runindex = 1;
            }

            if (runindex < args.length && args[runindex].equals("-d")) {
                runindex++;
                if (runindex < args.length) {
                    String num = args[runindex++];
                    try {
                        V.tosDepth = Integer.parseInt(num);
                    } catch (NumberFormatException exc) {
                        runindex = args.length;
                    }
                }
            }

            if (runindex < args.length && args[runindex].equals("-s")) {
                runindex++;
                if (runindex < args.length) {
                    String num = args[runindex++];
                    try {
                        stacksz = Integer.parseInt(num) * 1024 * 1024;
                    } catch (NumberFormatException exc) {
                        runindex = args.length;
                    }
                }
            }

            if (args.length <= runindex) {
                System.err.println("usage: java frege.Run [-p] [-d n] [-s stacksizemb ] classname arg1 arg2 ... argn");
                System.exit(1);
            }

            final Class<?> it;
            final java.lang.reflect.Method main;
            try {
                it = Class.forName(args[runindex]);
                final String arr[] = new String[args.length-runindex-1];
                for (int i=runindex+1; i < args.length; i++) arr[i-runindex-1] = args[i];
                main = it.getMethod("main",
                        new Class<?>[] {args.getClass()});
                Thread t = new Thread(null, null, "fregemain", stacksz) {
                    public void run() {
                            try {
                                    main.invoke(null, new Object[] { arr });
                                    // System.exit(0);
                            } catch (IllegalAccessException ex) {
                                    System.err.println(ex);
                                    System.exit(1);
                            } catch (java.lang.reflect.InvocationTargetException ex) {
                                    System.err.println(ex.getCause());
                                    ex.getCause().printStackTrace(System.err);
                                    System.exit(1);
                            }
                    }
                };
                t.start();
                t.join();
                if (async != null) {
                    if (async instanceof java.util.concurrent.ThreadPoolExecutor)
                        System.err.println(((java.util.concurrent.ThreadPoolExecutor)async).getCompletedTaskCount() + " subtasks");
                    async.shutdown();
                }
                System.exit(0);
            }
            catch (ClassNotFoundException ex) {
                    System.err.println("class " + args[runindex] + " not found.");
            }
            catch (NoSuchMethodException ex) {
                    System.err.println("class " + args[runindex] + " has no main function.");
            }
            catch (InterruptedException ex) { System.exit(0); }
            if (async != null) {
                if (async instanceof java.util.concurrent.ThreadPoolExecutor)
                    System.err.println(((java.util.concurrent.ThreadPoolExecutor)async).getCompletedTaskCount() + " subtasks");
                else System.err.println(async.getClass().getName());
                async.shutdown();
            }
            System.exit(1);
        }
}
