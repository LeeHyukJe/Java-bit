package ch15;
import java.io.*;

public class BufferedOutputStreamEx1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos=new FileOutputStream("ooh you are a holiday.txt");
			
			//buffered size 5
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			for(int i='1'; i<='9';i++) {
				bos.write(i);
			}
			//fos.close(); 출력 버퍼가 도착하기도 전에 file스트림이 닫힘
			bos.close();
		}catch(IOException e) {}
	}

}
