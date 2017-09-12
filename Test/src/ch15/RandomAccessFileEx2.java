package ch15;
import java.io.*;
public class RandomAccessFileEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] score= {1,100,90,90,
					  2,70, 80,34,
					  3,55,99,45,
					  4,100,33,45};
		
		try {
			RandomAccessFile raf=new RandomAccessFile("score2.txt","rw");
			for(int i=0;i<score.length;i++) {
				raf.writeInt(score[i]);
			}
			raf.seek(0);
			while(true) {
				System.out.print(raf.readInt()+" ");
			}
		}catch(EOFException eof) {
			System.out.println("파일 읽기를 마쳤습니다.");

		}catch(IOException e) {}
	}
}