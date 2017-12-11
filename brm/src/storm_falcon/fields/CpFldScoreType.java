package storm_falcon.fields;

import com.portal.pcm.StrField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldScoreType extends StrField {

	/**
	 * Constructs an instance of <code>CpFldScoreType</code>
	 */
	public CpFldScoreType() { super(10089, 5); }

	/**
	 * Returns an instance of <code>CpFldScoreType</code>
	 * @return An instance of <code>CpFldScoreType</code>
	 */
	public static synchronized CpFldScoreType getInst() { 
		if( me == null ) me = new CpFldScoreType();
		return me;
	}
	private static CpFldScoreType me;
}