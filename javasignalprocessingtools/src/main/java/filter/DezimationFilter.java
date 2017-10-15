package filter;

import filter.FIRFilter;

/**
 * Implements a class for a simple dezimation filter with integrated FIR filter.
 * 
 * @author Andre
 *
 */
public class DezimationFilter extends FIRFilter {
	private int order;

	/**
	 * Creates dezimation filter with order tabs and a FIR filter with the given
	 * FIRcoefs.
	 * 
	 * @param order
	 *            {@linkplain int} downsampling rate.
	 * @param FIRcoefs
	 *            {@linkplain double[]} coefizients for FIRFilter.
	 */
	public DezimationFilter(int order, double[] FIRcoefs) {
		super(FIRcoefs);
		this.order = order;
	}

	/**
	 * Process dezimation filter functionality. Retruns array with factor 1/order
	 * reduced sample count.
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] process(double[] input) {
		double[] average = new double[input.length];
		double[] state = new double[order];
		for (int n = 0; n < input.length; n++) {
			state = super.shiftArray(state, input[n]);
			// Calculate Average
			average[n] = this.sumArrayComponents(state) / order;
		}
		// Zuerst Filtern und dann Dezimation oder umgekehrt ??
		// Filter
		average = super.process(average);
		// Dezimation
		double[] output = new double[average.length / order];
		for (int n = order; n < average.length; n += order) {
			output[n] = average[n];
		}
		return output;
	}

	/**
	 * Returns the sum of all array entrys.
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double}
	 */
	private double sumArrayComponents(double[] input) {
		double output = 0;
		for (int n = 0; n < input.length; n++) {
			output += input[n];
		}
		return output;
	}
}
