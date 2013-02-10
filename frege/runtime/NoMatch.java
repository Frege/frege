package frege.runtime;



/**
 * <p> Exception thrown when all matches of a case or lambda fail. </p>
 * <p> 
 * <code>throw new NoMatch(...)</code> is 
 * inserted by the compiler at appropriate places. 
 * </p> 
 */


public class NoMatch extends Undefined {
	static final long serialVersionUID = 1L;

	public NoMatch(String qname, int line, Object x) {
        super(qname + " at line " + line + " no match for value " + x);
    }
}

