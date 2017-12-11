package storm_falcon.fields;

import com.portal.pcm.TStampField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldExpireT2 extends TStampField {

	/**
	 * Constructs an instance of <code>CpFldExpireT2</code>
	 */
	public CpFldExpireT2() { super(10042, 8); }

	/**
	 * Returns an instance of <code>CpFldExpireT2</code>
	 * @return An instance of <code>CpFldExpireT2</code>
	 */
	public static synchronized CpFldExpireT2 getInst() { 
		if( me == null ) me = new CpFldExpireT2();
		return me;
	}
	private static CpFldExpireT2 me;
}