package ch15;

import java.io.*;

public class PipedReaderWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputThread inThread=new InputThread("InputThread");
		OutputThread outThread=new OutputThread("OutThread");
		
		inThread.connect(outThread.getOutput());
		
		inThread.start();
		outThread.start();
		
	}

}

class InputThread extends Thread{
	PipedReader input=new PipedReader();
	StringWriter sw=new StringWriter();
	
	InputThread(String name){
		super(name);
	}
	
	public void run() {
		int data=0;
		try {
			while((data=input.read())!=-1) {
				sw.write(data);
			}
			System.out.println(getName()+" receive "+sw.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//run
	
	public PipedReader getInput() {
		return input;
	}
	
	public void connect(PipedWriter output) {
		try {
			input.connect(output);
		}catch(IOException e) {}
	}

}

class OutputThread extends Thread{
	PipedWriter output=new PipedWriter();
	
	OutputThread(String name){
		super(name);
	}
	
	public void run() {
		try {
			String msg="hello";
			System.out.println(getName()+" sent "+ msg);
			output.write(msg);
			output.close();
			
		}catch(IOException e) {}
	}
	
	public PipedWriter getOutput() {
		return output;
	}
	
	public void connect(PipedReader input) {
		try {
			output.connect(input);
		}catch(IOException e) {}
	}
}