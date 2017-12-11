package storm_falcon.fields;

import com.portal.pcm.IntField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldIsSucc extends IntField {

	/**
	 * Constructs an instance of <code>CpFldIsSucc</code>
	 */
	public CpFldIsSucc() { super(10117, 1); }

	/**
	 * Returns an instance of <code>CpFldIsSucc</code>
	 * @return An instance of <code>CpFldIsSucc</code>
	 */
	public static synchronized CpFldIsSucc getInst() { 
		if( me == null ) me = new CpFldIsSucc();
		return me;
	}
	private static CpFldIsSucc me;
}
