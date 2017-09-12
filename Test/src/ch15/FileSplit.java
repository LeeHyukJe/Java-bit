package ch15;
import java.io.*;

public class FileSplit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length<2) {
			System.out.println("Usage : java FileSplit fileName SiZE_KB");
			System.exit(0);
		}
		
		final int VOLUME=Integer.parseInt(args[1])*1000;
		
		try {
			String filename=args[0];
			FileInputStream fis=new FileInputStream(filename);
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			FileOutputStream fos=null;
			BufferedOutputStream bos=null;
			
			int data=0;
			int i=0;
			int number=0;
			
			while((data=bis.read())!=-1) {
				if(i%VOLUME==0) {
					if(i!=0) {
						bos.close();
					}
					
					fos=new FileOutputStream(filename+"_."+number);
					bos= new BufferedOutputStream(fos);
				}
				bos.write(data);
				i++;
			}
			bis.close();
			bos.close();
		}catch(IOException e) {}
	}

}
