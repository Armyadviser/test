package storm_falcon.fields;

import com.portal.pcm.IntField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Tue Nov 19 03:37:41 GMT 2013
 * @author Autogenerated
 */


public class PinFldIpv6PacketsIn extends IntField {

	/**
	 * Constructs an instance of <code>PinFldIpv6PacketsIn</code>
	 */
	public PinFldIpv6PacketsIn() { super(10149, 1); }

	/**
	 * Returns an instance of <code>PinFldIpv6PacketsIn</code>
	 * @return An instance of <code>PinFldIpv6PacketsIn</code>
	 */
	public static synchronized PinFldIpv6PacketsIn getInst() { 
		if( me == null ) me = new PinFldIpv6PacketsIn();
		return me;
	}
	private static PinFldIpv6PacketsIn me;
}