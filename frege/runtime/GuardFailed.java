package frege.runtime;

/**
 * <p> Exception thrown when the guard of the last case alternative fails. </p>
 * <p> <code>throw new GuardFailed(...)</code> is inserted by the compiler 
 * at appropriate places and helps identify the
 * source line number of the guard.</p>
 * @author ingo
 *
 */
public class GuardFailed extends Undefined {
	
	static final long serialVersionUID = 1L;

	public GuardFailed(String qname, int line) {
        super(qname + " at line " + line + ": guard failed.");
    }
    
}
