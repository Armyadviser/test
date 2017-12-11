package storm_falcon.fields;

import com.portal.pcm.BinStrField;

public class PinFldIpv6addr extends BinStrField {

	/**
	 * Constructs an instance of <code>PinFldIpv6addr</code>
	 */
	public PinFldIpv6addr() { super(10132, 12); }

	/**
	 * Returns an instance of <code>PinFldIpv6addr</code>
	 * @return An instance of <code>PinFldIpv6addr</code>
	 */
	public static synchronized PinFldIpv6addr getInst() { 
		if( me == null ) me = new PinFldIpv6addr();
		return me;
	}
	private static PinFldIpv6addr me;
}
