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


public class CpFldIcpFlag extends EnumField {

	/**
	 * Constructs an instance of <code>CpFldIcpFlag</code>
	 */
	public CpFldIcpFlag() { super(10086, 3); }

	/**
	 * Returns an instance of <code>CpFldIcpFlag</code>
	 * @return An instance of <code>CpFldIcpFlag</code>
	 */
	public static synchronized CpFldIcpFlag getInst() { 
		if( me == null ) me = new CpFldIcpFlag();
		return me;
	}
	private static CpFldIcpFlag me;
}
