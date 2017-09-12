package ch15;

import java.io.*;
import java.util.Arrays;

public class IOEx3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] inSrc= {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc=null;
		byte[] temp=new byte[4];
		
		ByteArrayInputStream input=null;
		ByteArrayOutputStream output=null;
		
		input=new ByteArrayInputStream(inSrc);
		output=new ByteArrayOutputStream();
		
		System.out.println("input source: "+Arrays.toString(inSrc));
		
		try {
			while(input.available()>0) {
				int len=input.read(temp); //temp 배열 길이만큼 읽어서 temp를 채운다.
				output.write(temp,0,len);
				System.out.println("temp: "+Arrays.toString(temp));
				
				outSrc=output.toByteArray();
				printArrays(temp,outSrc);
			}
		}catch(IOException io) {}
	}
	static void printArrays(byte[] temp, byte[] outSrc) {
		System.out.println("temp: "+Arrays.toString(temp));
		System.out.println("outer source: "+Arrays.toString(outSrc));
	}
}
