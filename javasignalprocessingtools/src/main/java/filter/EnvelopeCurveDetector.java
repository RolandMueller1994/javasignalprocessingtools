package filter;
/**
 * Implements a Class for an envelope curve detector.  <br>
 * Circiut Diagramm: <br>
 *       -----      -------                            -----<br>
 * O----|     |----| Diode |-------o-------o------O---|     |----o-----O-----o <br>
 *       -----      -------        |       |           -----     |           | <br>
 *        RL        rectifier     ---	   |             R2      |          --- <br>
 * 					          R1 |   |   ----- C1              ----- C2    |   | RL<br>
 * 						         |   |   -----				   -----       |   |<br>
 * 						          ---      |					 |          ---<br>
 * 						           |       |                     |			 |<br>
 * O-------------------------------o-------o------O--------------o-----O-----o<br>
 *  <br>                       
 * Two constructors are possible to use. <br>
 * One with RC-Filter behind the detection and one without it.<br>
 * @author Andre
 *
 */
public class EnvelopeCurveDetector {
	private double RV;
	private double RL;
	private double C1;
	private double R1;
	private double C2;
	private double R2;
	private double integratorState1 = 0;
	private double integratorState2 = 0;
	private boolean typeFlag = false;
	private double Const1 = 0;
	private double Const2 = 0;
	private double Const3 = 0;

	/**
	 * Constructor of envelope detector without RC-Filter behind the detection.
	 * @param C1
	 * @param R1
	 * @param RV
	 * @param RL
	 */
	public EnvelopeCurveDetector(double C1, double R1, double RV, double RL) {
		typeFlag = false;
		this.RL = RL;
		this.RV = RV; 
		this.C1 = C1;
		this.R1 = R1;
	}

	/**
	 * Constructor fo envelope detector with RC-Filter behind the detection.
	 * @param C1
	 * @param R1
	 * @param C2
	 * @param R2
	 * @param RV
	 * @param RL
	 */
	public EnvelopeCurveDetector(double C1, double R1, double C2, double R2, double RV, double RL) {
		typeFlag = true;
		this.RV = RV;
		this.RL = RL;
		this.C1 = C1;
		this.R1 = R1;
		this.C2 = C2;
		this.R2 = R2;
		Const1 = RV/RL + (RV*R2)/(RL*R1) + RV/R1 + R2/RL + 1.0;
		Const2 = C2*RV + (R2*C2*RV)/R1 + (R2*C1*RV)/RL + C1*RV;
		Const3 = -1.0/(RV*R2*C2*C1);
	}
	
	/**
	 * Implements time discrete integrator<br> 
	 * 		   1 <br>
	 * STF = ------ <br>
	 * 		 z - 1<br>
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	private double integrator1(double input) {
		double output = integratorState1; 
		integratorState1 = integratorState1 + input; 
		return output; 
	}
	/**
	 * Implements time discrete integrator
	 * 		   z
	 * STF = ------
	 * 		 z - 1 
	 * @param input {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	private double integrator2(double input) {
		double output = integratorState2 + input;
		integratorState2 = output;
		return output;
	}

	/**
	 * Returns the envelope curve of the input array. If C2 and R2 are given to
	 * constructor a output RC-Filter is also processed.
	 * 
	 * @param input
	 *            {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] process(double[] input) {
		double[] output = new double[input.length];
		double buffer = 0.0;
		if (typeFlag) {
			// Calculate envelope with RC-Filter(R2,C2).
			for (int n = 1; n < input.length; n++) {
				buffer = integrator1(output[n-1] * Const1 * Const3 - Const3 * Math.abs(input[n]));
				output[n] = integrator2(output[n-1] * Const2 * Const3+ buffer);
			}

		} else {
			// Calculate envelope without RC-Filter(R2,C2).

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

	public void setIntegratorState2(double integratorState2) {
		this.integratorState2 = integratorState2;
	}

	public double getRV() {
		return RV;
	}

	public void setRV(double rV) {
		RV = rV;
	}

	public double getRL() {
		return RL;
	}

	public void setRL(double rL) {
		RL = rL;
	}

	public void setIntegratorState1(double integratorState1) {
		this.integratorState1 = integratorState1;
	}

}
