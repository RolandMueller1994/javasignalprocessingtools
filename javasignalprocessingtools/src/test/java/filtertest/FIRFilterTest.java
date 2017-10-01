package filtertest;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import filter.FIRFilter;

public class FIRFilterTest {

	@Test
	public void test() {
		double[] x = new double[1000];
		for(int n=0; n < x.length; n++) {
			x[n] = n/(2*Math.PI); 
		}
		double[] y = new double[1000];
		for(int n=0; n< x.length; n++) {
			y[n] = Math.sin(10*x[n]) + Math.sin(100*x[n]) + Math.sin(500*x[n]) + Math.sin(3000*x[n]); 
		}
		double[] coefs = new double[80]; 
		int k = 0; 
		double fg = 0.2; 
		for(int n=0; n < coefs.length/2 - 1; n++) {
			coefs[n] = 40 - n;
		}
		for(int n=coefs.length/2; n < coefs.length; n++) {
			coefs[n] = n - coefs.length/2;
		}
		
		for(int n = 0; n < coefs.length; n++) {	
			coefs[n]= sinc(2*fg*coefs[n]);
		}
		
		FIRFilter filter = new FIRFilter(coefs);
		y = filter.process(y);
		String userdir = System.getProperty("user.dir");
		try {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(userdir + File.separator + "src" + File.separator + "matlab_files"
							+ File.separator + "FIRFilterTest.txt"),
					"utf-8"))) {
				for (int n = 0; n < x.length; n++) {
					String myString = "";
					try {
						myString = Double.toString(x[n]) + " " + Double.toString(x[n])
								+ "\n";
						writer.write(myString);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {}
				}
			}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	double sinc(double input) {
		if (input == 0.0) {
			return 1.0;
		}
		else {
			return Math.sin(input)/input; 
		}
	}
}
