package filter;
import filter.FIRFilter;
/**
 * Implements a Class for an envelope curve detector. 
 * Circiut Diagramm:
 * 		   -------							  -----------
 * O------| Diode |--------o-------o------O--|			 |--O
 * 		   -------         |	   |		 | 	         |
 * 						  ---	   |         |           |
 * 					  R1 |   |   ----- C1    | FIRFilter |
 * 						 |   |   -----		 |	         |
 * 						  ---      |		 |		     |
 * 						   |       |         |           |
 * O-----------------------o-------o------O--|           |--O
 * 											  -----------
 * @author Andre
 *
 */
public class EnvelopeDetectorFIR extends FIRFilter {
	private double R1;
	private double C1;
	private double integratorState = 0.0;

	/**
	 * Constructor of envelope detectore with internal FIRFilter.
	 * 
	 * @param coefs
	 *            {@linkplain double}
	 * @param R1
	 *            {@linkplain double}
	 * @param C1
	 *            {@linkplain double}
	 */
	public EnvelopeDetectorFIR(double[] coefs, double R1, double C1) {
		super(coefs);
		this.R1 = R1;
		this.C1 = C1;
	}

	/**
	 * Implements time discrete integrator 
	 * 			1 
	 * STF = ------ 
	 * 		  z - 1
	 * 
	 * @param input
	 *            {@linkplain double}
	 * @return {@linkplain double}
	 */
	private double integrator(double input) {
		double output = integratorState;
		integratorState = integratorState + input;
		return output;
	}

	/**
	 * Returns the envelope curve of the input array. The output curve is internally
	 * filtered with FIRfilter.
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] processFiltered(double[] input) {
		double[] output = new double[input.length];
		// Detection of envelope curve
		for (int n = 0; n < input.length; n++) {
			output[n] = integrator(Math.abs(input[n])) * (1 / (R1 * C1));
		}
		// Filter of ripple on envelope curve
		output = super.process(output);
		return output;
	}

	/**
	 * Returns the envelope curve of the input array.
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] processUnfiltered(double[] input) {
		double[] output = new double[input.length];
		// Detection of envelope curve
		for (int n = 0; n < input.length; n++) {
			output[n] = integrator(Math.abs(input[n])) * (1 / (R1 * C1));
		}
		return output;
	}

	public double getR1() {
		return R1;
	}

	public void setR1(double r1) {
		R1 = r1;
	}

	public double getC1() {
		return C1;
	}

	public void setC1(double c1) {
		C1 = c1;
	}

	public void setIntegratorState(double integratorState) {
		this.integratorState = integratorState;
	}

}
