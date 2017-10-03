package filter;
/**
 * Implements a Class for an envelope curve detector. 
 * Two constructors are possible to use. 
 * One with RC-Filter behind the detection and one without it.
 * @author Andre
 *
 */
public class EnvelopeCurveDetector {
	private double C1; 
	private double R1; 
	private double C2;
	private double R2; 
	private double integratorState = 0; 
	private boolean typeFlag = false; 
	/**
	 * Constructor of envelope detector without RC-Filter behind the detection.
	 * @param C1
	 * @param R1
	 */
	public EnvelopeCurveDetector(double C1, double R1) {
		typeFlag = false; 
		this.C1 = C1; 
		this.R1 = R1; 
	}
	/**
	 * Constructor fo envelope detector with RC-Filter behind the detection. 
	 * @param C1
	 * @param R1
	 * @param C2
	 * @param R2
	 */
	public EnvelopeCurveDetector(double C1, double R1, double C2, double R2) {
		typeFlag = true; 
		this.C1 = C1;
		this.R1 = R1;
		this.C2 = C2;
		this.R2 = R2; 
	}
	
	/**
	 * Implements time discrete integrator
	 * 		   1
	 * STF = ------
	 * 		 z - 1 
	 * @param input
	 * @return
	 */
	private double integrator(double input) {
		double output = integratorState; 
		integratorState = integratorState + input; 
		return output; 
	}
	/**
	 * Returns the envelope curve of the input array.
	 * If C2 and R2 are given to constructor a output RC-Filter is also 
	 * processed.
	 * @param input
	 * @return
	 */
	public double[] porcess(double[] input) {
		double[] output = new double[input.length];
		if(typeFlag) {
			//Calculate envelope with RC-Filter(R2,C2).
		}
		else {
			//Calculate envelope without RC-Filter(R2,C2).
			for(int n = 0; n<input.length; n++) {
				output[n] = integrator(input[n])*(1/(R1*C1));
			}
		}
		return output; 
	}
	public double getC1() {
		return C1;
	}
	public void setC1(double c1) {
		C1 = c1;
	}
	public double getR1() {
		return R1;
	}
	public void setR1(double r1) {
		R1 = r1;
	}
	public double getC2() {
		return C2;
	}
	public void setC2(double c2) {
		C2 = c2;
	}
	public double getR2() {
		return R2;
	}
	public void setR2(double r2) {
		R2 = r2;
	}
	public void setIntegratorState(double integratorState) {
		this.integratorState = integratorState;
	}
	
	
}
