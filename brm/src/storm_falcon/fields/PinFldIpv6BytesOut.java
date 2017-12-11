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


public class PinFldIpv6BytesOut extends IntField {

	/**
	 * Constructs an instance of <code>PinFldIpv6BytesOut</code>
	 */
	public PinFldIpv6BytesOut() { super(10148, 1); }

	/**
	 * Returns an instance of <code>PinFldIpv6BytesOut</code>
	 * @return An instance of <code>PinFldIpv6BytesOut</code>
	 */
	public static synchronized PinFldIpv6BytesOut getInst() { 
		if( me == null ) me = new PinFldIpv6BytesOut();
		return me;
	}
	private static PinFldIpv6BytesOut me;
}