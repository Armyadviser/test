package storm_falcon.fields;

import com.portal.pcm.EnumField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldPppoeStatus extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldPppoeStatus</code>
	 */
	public CpFldPppoeStatus() { super(10127, 3); }

	/**
	 * Returns an instance of <code>CpFldPppoeStatus</code>
	 * @return An instance of <code>CpFldPppoeStatus</code>
	 */
	public static synchronized CpFldPppoeStatus getInst() { 
		if( me == null ) me = new CpFldPppoeStatus();
		return me;
	}
	private static CpFldPppoeStatus me;
}
