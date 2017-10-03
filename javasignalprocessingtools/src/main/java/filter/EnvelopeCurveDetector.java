package filter;
/**
 * Implements a Class for an envelope curve detector. 
 * Circiut Diagramm:
 * 		   -------							   -----
 * O------| Diode |--------o-------o------O---|		|----o-----O
 * 		   -------         |	   |		   -----	 |
 * 		  rectifier 	  ---	   |            R2       |
 * 					  R1 |   |   ----- C1              ----- C2
 * 						 |   |   -----				   -----
 * 						  ---      |					 |
 * 						   |       |                     |
 * O-----------------------o-------o------O--------------o-----O
 *                         
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
	private double integratorState1 = 0; 
	private double integratorState2 = 0; 
	private boolean typeFlag = false; 
	/**
	 * Constructor of envelope detector without RC-Filter behind the detection.
	 * @param C1 {@linkplain double}
	 * @param R1 {@linkplain double}
	 */
	public EnvelopeCurveDetector(double C1, double R1) {
		typeFlag = false; 
		this.C1 = C1; 
		this.R1 = R1; 
	}
	/**
	 * Constructor fo envelope detector with RC-Filter behind the detection. 
	 * @param C1 {@linkplain double}
	 * @param R1 {@linkplain double}
	 * @param C2 {@linkplain double}
	 * @param R2 {@linkplain double}
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
	 * @param input {@linkplain double[]}
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
	 * Returns the envelope curve of the input array.
	 * If C2 and R2 are given to constructor a output RC-Filter is also 
	 * processed.
	 * @param input {@linkplain double[]}
	 * @return {@linkplain double[]}
	 */
	public double[] porcess(double[] input) {
		double[] output = new double[input.length];
		if(typeFlag) {
			//Calculate envelope with RC-Filter(R2,C2).
			double int1 = 0;
			double const1 = ((R2*C2)/R1)+C1+C2;
			for(int n = 0; n< input.length; n++) {
				int1 = integrator1((1/R1)*output[n]-input[n]);
				output[n]=(-1/(C1*C2*R2))*integrator2(int1+const1*output[n]);
			}
			
		}
		else {
			//Calculate envelope without RC-Filter(R2,C2).
			for(int n = 0; n<input.length; n++) {
				output[n] = integrator1(Math.abs(input[n]))*(1/(R1*C1));
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
	public void setintegratorState1(double integratorState1) {
		this.integratorState1 = integratorState1;
	}
	public void setIntegratorState2(double integratorState2) {
		this.integratorState2 = integratorState2;
	}
	
	
}
