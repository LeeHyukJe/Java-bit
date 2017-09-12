package ch15;

import java.io.*;
import java.util.*;

public class SequenceInputStreamEx {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		byte[] arr1 = { 0, 1, 2 };
		byte[] arr2 = { 3, 4, 5 };
		byte[] arr3 = { 6, 7, 8 };
		byte[] outSrc = null;
		Vector v = new Vector();
		Vector vs=new Vector();
		v.add(new ByteArrayInputStream(arr1));
		v.add(new ByteArrayInputStream(arr2));
		v.add(new ByteArrayInputStream(arr3));
		vs.add(new FileInputStream("please.txt"));
		vs.add(new FileInputStream("123.txt"));

		SequenceInputStream input = new SequenceInputStream(v.elements());
		SequenceInputStream input2=new SequenceInputStream(vs.elements());
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		int data = 0;
		int data2=0;
		
		try {
			while ((data = input.read()) != -1) {
				output.write(data);
			}
			
			while((data2=input2.read())!=-1) {
				
			}
		} catch (IOException e) {
		}

		outSrc = output.toByteArray();

		System.out.println("src1: " + Arrays.toString(arr1));
		System.out.println("src2: " + Arrays.toString(arr1));
		System.out.println("src3: " + Arrays.toString(arr1));

		System.out.println(Arrays.toString(outSrc));

	}

}
