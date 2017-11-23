package dft;

import javax.annotation.Nonnull;

/**
 * This Class implements a method for calculating the discrete fourier
 * transformation (DFT) of input values.
 * 
 * @author Andre
 *
 */
public class DFT {
	/**
	 * This Class implements a method for calculating the discrete fourier
	 * transformation (DFT) of input values.
	 */
	public DFT() {

	}

	/**
	 * Calculates the DFT of the input array.
	 * 
	 * @param input
	 * 
	 * @return Array of {@linkplain ComplexNumber}
	 */
	public @Nonnull ComplexNumber[] calcDFT(int[] input) {
		return calcDFT(ComplexNumber.intToComplex(input), false);
	}

	/**
	 * Calculates the DFT of the input array.
	 * 
	 * @param input
	 *            of input values of type {@linkplain ComplexNumber}
	 * @param inverse
	 *            Select DFT (False) or Invers-DFT (True)
	 * @return Array of {@linkplain ComplexNumber}
	 */

	public @Nonnull ComplexNumber[] calcDFT(ComplexNumber[] input, boolean inverse) {
		ComplexNumber[] output = new ComplexNumber[input.length];
		try {
			int n = input.length;
			double q = 2 * Math.PI / n;
			double c, s, si, at, bt, t, w;
			for (int j = 0; j < n; j++) {
				w = q * j;
				s = Math.sin(w);
				c = Math.cos(w);
				if (inverse) {
					si = -s;
				} else {
					si = s;
				}
				at = input[n - 1].getReal();
				bt = input[n - 1].getImag();
				for (int i = 0; i < n - 2; i++) {
					t = c * at + si * bt + input[n - i - 2].getReal();
					bt = c * bt - s * at + input[n - i - 2].getImag();
					at = t;
				}
				output[j] = new ComplexNumber(c * at + s * bt + input[0].getReal(),
						c * bt - s * at + input[0].getImag());
			}
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			output = new ComplexNumber[1];
			output[0] = new ComplexNumber(0, 0);
			return output;

		} finally {
		}
	}
}
