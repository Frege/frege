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
		/** Subtypes to play with */
		public interface XorY extends RealWorld {}
		public interface XorZ extends RealWorld {}
		public interface XXX extends XorY, XorZ {}
		public interface YYY extends XorY {}
		public interface ZZZ extends XorZ {}
	/**
	 *	this should not be exposed, but ... 
	 */
	public final static RealWorld theRealWorld = new RealWorld () {};
}
