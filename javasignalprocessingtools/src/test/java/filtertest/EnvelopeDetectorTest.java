package filtertest;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

import exceptions.CheckedArithmeticException;

public class EnvelopeDetectorTest {

	@Test
	public void test() {
		double[] x = new double[100000];
		double[] y = new double[x.length];
		double[] hk = new double[x.length];
		double deltaT = 1.0/44100.0; 
		for(int n = 0; n< x.length; n++) {
			x[n] = n*deltaT; 
			y[n] = Math.sin(2*Math.PI*50*x[n])*Math.sin(2*Math.PI*5*x[n]); 
		}
		filter.EnvelopeDetector dect = new filter.EnvelopeDetector(25e3, 1e-6,  100, deltaT);
		try {
			hk = dect.process_simple(y);
		} catch (CheckedArithmeticException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String userdir = System.getProperty("user.dir");
		try {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userdir + File.separator
					+ "src" + File.separator + "matlab_files" + File.separator + "EnvelopeTest.txt"), "utf-8"))) {
				for (int n = 0; n < x.length; n++) {
					String myString = "";
					try {
						myString = Double.toString(x[n]) + " " + Double.toString(y[n]) + " "
								+ Double.toString(hk[n]) + "\n";
						writer.write(myString);
					} catch (Exception e) {
						e.printStackTrace();

					} finally {
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
