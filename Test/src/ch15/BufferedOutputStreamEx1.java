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
			//fos.close(); ��� ���۰� �����ϱ⵵ ���� file��Ʈ���� ����
			bos.close();
		}catch(IOException e) {}
	}

}
