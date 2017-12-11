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


public class CpFldOnlineTime extends IntField {

	/**
	 * Constructs an instance of <code>CpFldOnlineTime</code>
	 */
	public CpFldOnlineTime() { super(10090, 1); }

	/**
	 * Returns an instance of <code>CpFldOnlineTime</code>
	 * @return An instance of <code>CpFldOnlineTime</code>
	 */
	public static synchronized CpFldOnlineTime getInst() { 
		if( me == null ) me = new CpFldOnlineTime();
		return me;
	}
	private static CpFldOnlineTime me;
}