package filtertest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dft.ComplexNumber;
import dft.DFT;

public class DFTTest {

	@Test
	public void test() {
		String everything = null;
		List<Double> myDoubleList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		System.out.println("Test of DFT Class");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\Andre\\Documents\\MATLAB\\TestFFT_Matlab_Out.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				myDoubleList.add(Double.parseDouble(line));
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(everything);
		ComplexNumber[] samples = new ComplexNumber[myDoubleList.size()];
		for (int i = 0; i < myDoubleList.size(); i++) {
			samples[i] = new ComplexNumber(myDoubleList.get(i), 0);
		}
		ComplexNumber[] dftResult = new ComplexNumber[myDoubleList.size()];
		DFT myDFT = new DFT();
		dftResult = myDFT.calcDFT(samples, false);
		//System.out.println(dftResult[0].getReal());

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\Users\\Andre\\Documents\\MATLAB\\TestFFT_Matlab_in.txt"), "utf-8"))) {
			for (int i = 0; i < myDoubleList.size(); i++) {
				String myString = "";
				try{
				myString = Double.toString(dftResult[i].getReal()) + " "
						+ Double.toString(dftResult[i].getImag()) + "\n";
				writer.write(myString);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally {
					
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");

	}

}
