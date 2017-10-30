package filter;

import exceptions.CheckedArithmeticException;

public class EnvelopeDetector {
	private double R1;
	private double C1;
	private double RV;
	private double UC;
	private double deltaT;

	public EnvelopeDetector(double R1, double C1, double RV, double deltaT) {
		setR1(R1);
		setC1(C1);
		setRV(RV);
		setDeltaT(deltaT);
	}

	public double[] process_simple(double[] input) throws CheckedArithmeticException {
		double deltaUC = 0;
		double i1 = 0; 
		double iR = 0;
		double[] output = new double[input.length];
		for (int n = 0; n < input.length; n++) {
			if (UC <= input[n]) {
				i1 = (input[n]-UC)/RV;
				iR = UC/R1;
				deltaUC = ((i1 - iR)*deltaT)/C1;
				UC = UC + deltaUC;
				if(Double.isNaN(UC)){
					throw new CheckedArithmeticException("Output is NaN");
				}
				else if (Double.isInfinite(UC)){
					throw new CheckedArithmeticException("Output is Infinity");
				} 
				else {
					output[n] = UC;
				}
				
			} else {
				UC = UC*(1-(UC/(R1*C1))*deltaT);
				output[n] = UC;
			}
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

	public double getRV() {
		return RV;
	}

	public void setRV(double rV) {
		RV = rV;
	}

	public double getUC() {
		return UC;
	}

	public void setUC(double uC) {
		UC = uC;
	}

	public double getDeltaT() {
		return deltaT;
	}

	public void setDeltaT(double deltaT) {
		this.deltaT = deltaT;
	}

}
