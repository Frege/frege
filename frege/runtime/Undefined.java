
package frege.runtime;

/**
 * <p> Exception thrown when the undefined value is evaluated. </p>
 * @author ingo
 *
 */
public class Undefined extends IllegalArgumentException {
	private int two = 2;			// used to fool the compiler, see die() below
	static final long serialVersionUID = 1L;
	/**
	 * Construct an undefined value from a string, e.g.
	 * <pre>
	 * <code>
	 * Prelude.error "you blew it"
	 * </code>
	 * </pre>
	 * @param err description of the error
	 */
	public Undefined(String err) {
        super(err);
    }
	
	/**
	 * Construct an undefined value from a string and another exception.
	 * 
	 * <p> This is used by Frege code to throw any exception catched from
	 * invokation of native functions.<br>
	 * This mechanism avoids <b>throws</b> clauses on frege functions.</p> 
	 * @param err
	 * @param cause
	 */
    public Undefined(String err, Throwable cause) {
        super(err, cause);
    }
    
    /**
     * Method used by Frege code to throw exceptions.
     *
     * <p>An exception from some native method, wrapped in {@link Undefined}
     * can be thrown everywhere without <code>throws</code> clauses or <code>try ... catch ...</code>
     * blocks.
     * 
     * <p>This function actually never returns, but we need some return type to make 
     * the Frege type checker happy and also to give the code generator a reason 
     * to actually invoke this method.</p> 
     **/
    public final boolean die()  {
    	if (two+two == 4) throw this;
    	// never reached (except when someone resets 'two' by means of reflection)
    	return false; 
    }
}
