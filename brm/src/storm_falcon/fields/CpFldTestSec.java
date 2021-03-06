package storm_falcon.fields;

import com.portal.pcm.IntField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Mon Nov 19 10:37:53 CST 2007
 * @author Autogenerated
 */


public class CpFldTestSec extends IntField {

	/**
	 * Constructs an instance of <code>CpFldTestSec</code>
	 */
	public CpFldTestSec() { super(19999, 1); }

	/**
	 * Returns an instance of <code>CpFldTestSec</code>
	 * @return An instance of <code>CpFldTestSec</code>
	 */
	public static synchronized CpFldTestSec getInst() { 
		if( me == null ) me = new CpFldTestSec();
		return me;
	}
	private static CpFldTestSec me;
}
