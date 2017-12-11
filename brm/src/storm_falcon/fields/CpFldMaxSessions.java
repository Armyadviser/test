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


public class CpFldMaxSessions extends IntField {

	/**
	 * Constructs an instance of <code>CpFldMaxSessions</code>
	 */
	public CpFldMaxSessions() { super(10061, 1); }

	/**
	 * Returns an instance of <code>CpFldMaxSessions</code>
	 * @return An instance of <code>CpFldMaxSessions</code>
	 */
	public static synchronized CpFldMaxSessions getInst() { 
		if( me == null ) me = new CpFldMaxSessions();
		return me;
	}
	private static CpFldMaxSessions me;
}
