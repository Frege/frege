package frege.runtime;

/**
 * <p>Container for ST phantom types.</p>
 * 
 * <p>When BBB is a subtype of AAA, then a ST BBB action can be embedded in a ST AAA action
 * but not the other way around.</p>
 * 
 * <p>(This is experimental)</p>
 * 
 * @author ingo
 *
 */
public class Phantom {

	/** the root of the phantom hierarchy, this is used for IO actions */
	public interface RealWorld {}
		/** Graphic with JavaFX */
		public interface JavaFX extends RealWorld {}
		/** Software transactional memory */
		public interface STM extends RealWorld {}
		public interface XXX extends RealWorld {}
		public interface YYY extends RealWorld {}
		public interface AAA extends XXX, YYY {}
	
	/**
	 *	this should not be exposed, but ... 
	 */
	public final static RealWorld theRealWorld = new RealWorld () {};
}
