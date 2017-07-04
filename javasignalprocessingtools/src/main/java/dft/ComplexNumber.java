package dft;
/**
 * Implements an small Class for handling complex numbers
 * @author Andre
 *
 */
public class ComplexNumber {
	private double real; 
	private double imag; 
	/**
	 * 
	 * @param real
	 * @param imag
	 */
	public ComplexNumber(double real, double imag){
		this.real = real; 
		this.imag = imag;
	}
	/**
	 * 
	 * @return
	 */
	public double getReal() {
		return real;
	}
	/**
	 * 
	 * @param real
	 */
	public void setReal(double real) {
		this.real = real;
	}
	/**
	 * 
	 * @return
	 */
	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
	}
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static ComplexNumber[] intToComplex(int[] input){
		ComplexNumber[] output = new ComplexNumber[input.length];
		for(int i=0; i<input.length; i++){
			output[i]=intToComplex(input[i]);
		}
		return output; 
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static ComplexNumber intToComplex(int input){
		return new ComplexNumber(input, 0);
	}
}
