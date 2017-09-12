package ch15;

import java.io.*;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		try {
//			FileInputStream fis=new FileInputStream(args[0]);
//			FileOutputStream fos=new FileOutputStream(args[1]);
//			
//			int data=0;
//			while((data=fis.read())!=-1) {
//				fos.write(data);
//			}
//			
//			fis.close();
//			fos.close();
//		}catch(IOException e) {}
		

			FileInputStream fis=new FileInputStream(args[0]);
			FileOutputStream fos=new FileOutputStream(args[1]);
			
			int data=0;
			while((data=fis.read())!=-1) {
				fos.write(data);
			}
			
			fis.close();
			fos.close();
	}

}
