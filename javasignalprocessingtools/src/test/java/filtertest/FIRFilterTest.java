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
			x[n] = n; 
		}
		double[] y = new double[1000];
		for(int n=0; n< x.length; n++) {
			y[n] = Math.sin(10*x[n]) + Math.sin(100*x[n]) + Math.sin(500*x[n]) + Math.sin(3000*x[n]); 
		}
		double[] coefs = new double[80]; 
		double fg = 0.3; 
		for(int n=0; n < coefs.length/2; n++) {
			coefs[n] = -(coefs.length-1.0)/2.0 + n;
		}
		for(int n=coefs.length/2; n < coefs.length; n++) {
			coefs[n] = n - ((coefs.length-1)/2.0);
		}
		
		for(int n = 0; n < coefs.length; n++) {	
			coefs[n]= this.sinc((2.0*fg*coefs[n]));
		}
		
		FIRFilter filter = new FIRFilter(coefs);
		double[] y_filterd = new double[y.length];
		y_filterd = filter.process(y);
		String userdir = System.getProperty("user.dir");
		try {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(userdir + File.separator + "src" + File.separator + "matlab_files"
							+ File.separator + "FIRFilterTest.txt"),
					"utf-8"))) {
				for (int n = 0; n < x.length; n++) {
					String myString = "";
					try {
						myString = Double.toString(x[n]) + " " + Double.toString(y[n]) + " " + Double.toString(y_filterd[n] )
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
			double output = Math.sin(input)/input;
			return  output; 
		}
	}
}
