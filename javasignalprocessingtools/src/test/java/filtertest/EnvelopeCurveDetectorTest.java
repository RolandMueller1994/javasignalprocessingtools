package filtertest;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

import filter.EnvelopeCurveDetector;

public class EnvelopeCurveDetectorTest {

	@Test
	public void test() {
		double[] x = new double[1000];
		for(int n=1; n < x.length; n++) {
			x[n] = x[n-1]+1e-5; 
		}
		double[] y = new double[x.length];
		for(int n=0; n < y.length; n++) {
			y[n] = Math.sin(x[n]*Math.PI)*Math.sin(1*x[n]*Math.PI); 
		}
		
		EnvelopeCurveDetector Detector1 = new EnvelopeCurveDetector(1e-6, 1000, 1e-6, 1000, 1, 50);
		double[] y_filterd = new double[y.length];
		y_filterd = Detector1.process(y);
		String userdir = System.getProperty("user.dir");
		try {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(userdir + File.separator
					+ "src" + File.separator + "matlab_files" + File.separator + "EnvelopeTest.txt"), "utf-8"))) {
				for (int n = 0; n < x.length; n++) {
					String myString = "";
					try {
						myString = Double.toString(x[n]) + " " + Double.toString(y[n]) + " "
								+ Double.toString(y_filterd[n]) + "\n";
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
