package storm_falcon.fields;

import com.portal.pcm.BufField;

/**
 * Specific Field subclasses. This subclasses of <code>Field</code>
 * is used with the FList class to specifiy which field is being
 * accessed, and its type.  The type information is used to provide
 * compile time type checking.  These classes are auto generated.
 * @version 1.0 Wed Nov 28 13:54:46 CST 2007
 * @author Autogenerated
 */


public class CpFldThirdIniFile extends BufField {

	/**
	 * Constructs an instance of <code>CpFldThirdIniFile</code>
	 */
	public CpFldThirdIniFile() { super(10113, 6); }

	/**
	 * Returns an instance of <code>CpFldThirdIniFile</code>
	 * @return An instance of <code>CpFldThirdIniFile</code>
	 */
	public static synchronized CpFldThirdIniFile getInst() { 
		if( me == null ) me = new CpFldThirdIniFile();
		return me;
	}
	private static CpFldThirdIniFile me;
}
