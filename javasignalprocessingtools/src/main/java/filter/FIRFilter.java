package filter;

/**
 * Implements a class for simple FIR filter in 2. canonic direct structure
 * @author Andre
 *
 */

public class FIRFilter {
	private double[] coefs;
	private double[] state;

	/**
	 * Creates FIR filter out of coefficients. Order of the filter is equal to the
	 * number of coefficients.
	 * 
	 * @param coefs
	 *            {@linkplain double[]} coefficients of FIRFilter.
	 */
	public FIRFilter(double[] coefs) {
		this.coefs = coefs;
		this.state = new double[coefs.length];
	}

	/**
	 * Process Filter function with integer input array.
	 * 
	 * @param input
	 *            {@linkplain int[]}
	 * @return {@linkplain double[]}
	 */
	public double[] process(int[] input) {
		double[] inDouble = new double[input.length];
		for (int i = 0; i < input.length; i++) {
			inDouble[i] = input[i];
		}
		return this.process(inDouble);
	}

	/**
	 * Process Filter function with double input array
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] process(double[] input) {
		double[] output = new double[input.length];
		for (int n = 0; n < input.length; n++) {
			state = this.shiftArray(state, input[n]);
			output[n] = this.matrixMultArray(state, coefs);
		}
		return output;
	}

	/**
	 * Multiplies two arrays.
	 * 
	 * @param in1
	 *            {@linkplain double[]}
	 * @param in2
	 *            {@linkplain double[]}
	 * @return
	 */
	protected double[] multArray(double[] in1, double[] in2) {
		if (in1.length != in2.length) {
			double[] output = { 0 };
			return output;
		} else {
			double[] output = new double[in1.length];
			for (int i = 0; i < in1.length; i++) {
				output[i] = in1[i] * in2[i];
			}
			return output;
		}
	}

	/**
	 * Right shift inValue in Array.
	 * 
	 * @param inArray
	 *            {@linkplain double[]}
	 * @param inValue
	 *            {@linkplain double[]}
	 * @return
	 */
	protected double[] shiftArray(double[] inArray, double inValue) {
		double[] output = new double[inArray.length];
		for (int i = inArray.length; i >= 2; i--) {
			output[i - 1] = inArray[i - 2];
		}
		output[0] = inValue;
		return output;
	}

	/**
	 * Generates matrix multiplication of two array. Output is a single value.
	 * 
	 * @param in1
	 *            {@linkplain double[]}
	 * @param in2
	 *            {@linkplain double[]}
	 * @return
	 */
	protected double matrixMultArray(double[] in1, double[] in2) {
		if (in1.length != in2.length) {
			return 0;
		} else {
			double output = 0;
			for (int i = 0; i < in1.length; i++) {
				output += in1[i] * in2[i];
			}
			return output;
		}
	}

	/**
	 * Reset of the internal filter state memory.
	 */
	public void resetFilterState() {
		for (int n = 0; n < state.length; n++) {
			state[n] = 0.0;
		}
	}

}
