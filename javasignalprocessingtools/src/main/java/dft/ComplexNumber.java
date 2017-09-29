package dft;
/**
 * Implements a small Class for handling complex numbers
 * @author Andre
 *
 */
public class ComplexNumber {
	private double real; 
	private double imag; 
	/**
	 * Creates new Complex Number
	 * @param real of {@linkplain double}
	 * @param imag of {@linkplain double}
	 */
	public ComplexNumber(double real, double imag){
		this.real = real; 
		this.imag = imag;
	}
	/**
	 * Returns real part of complex number
	 * @return of {@linkplain double}
	 */
	public double getReal() {
		return real;
	}
	/**
	 * Set real part of complex number
	 * @param real of {@linkplain double}
	 */
	public void setReal(double real) {
		this.real = real;
	}
	/**
	 * Returns imaginary part of complex number
	 * @return of {@linkplain double}
	 */
	public double getImag() {
		return imag;
	}
	/**
	 * Set imaginary part of complex number
	 * @param imag of {@linkplain double}
	 */
	public void setImag(double imag) {
		this.imag = imag;
	}
	/**
	 * Convert array of int to array of complex numbers (only real part)
	 * @param input of {@linkplain int}
	 * @return of {@linkplain ComplexNumber}
	 */
	public static ComplexNumber[] intToComplex(int[] input){
		ComplexNumber[] output = new ComplexNumber[input.length];
		for(int i=0; i<input.length; i++){
			output[i]=intToComplex(input[i]);
		}
		return output; 
	}
	
	/**
	 * Convert int to ComplexNumber (only real part)
	 * @param input of {@linkplain int}
	 * @return of {@linkplain ComplexNumber}
	 */
	public static ComplexNumber intToComplex(int input){
		return new ComplexNumber(input, 0);
	}
}
