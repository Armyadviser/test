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


public class CpFldPortType extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldPortType</code>
	 */
	public CpFldPortType() { super(10076, 3); }

	/**
	 * Returns an instance of <code>CpFldPortType</code>
	 * @return An instance of <code>CpFldPortType</code>
	 */
	public static synchronized CpFldPortType getInst() { 
		if( me == null ) me = new CpFldPortType();
		return me;
	}
	private static CpFldPortType me;
}
