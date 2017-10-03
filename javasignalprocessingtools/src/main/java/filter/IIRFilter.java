package filter;
/**
 * 
 * @author Andre
 *
 */
public class IIRFilter {
	private double[] bCoefs; 
	private double[] aCoefs; 
	private double[] state; 
	/**
	 * 
	 * @param aCoefs
	 * @param bCoefs
	 */
	public IIRFilter(double[] aCoefs, double[] bCoefs) {
		if(aCoefs.length == bCoefs.length) {
			this.aCoefs = aCoefs;
			this.bCoefs = bCoefs;
			this.state = new double[bCoefs.length-1];
		}
		else {
			//exception
		}
	}
	/**
	 * 
	 * @param input
	 * @return
	 */
	public double[] process(double [] input) {
		double[] output = new double[input.length]; 
		for(int n = 0; n < input.length; n++) {
			output[n] = bCoefs[0]*input[n] + state[state.length-1] + aCoefs[0]*output[n];
			for(int k = state.length; k >= 2; k--) { 
				state[k-1] = state[k-2] + bCoefs[bCoefs.length-k+1]*input[n] + aCoefs[aCoefs.length-k+1]*output[n];
			} 
			state[0] = output[n]*aCoefs[aCoefs.length-1] + input[n]*bCoefs[bCoefs.length-1];
		}
		return output; 
	}
	
	/**
	 * Reset of the internal filter state memory.
	 */
	public void resetFilterState() {
		for(int n = 0; n < state.length; n++) {
			state[n]=0.0;
		}
	}

}
