package ch14;

import java.io.*;
import java.util.stream.*;

public class StreamEx2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File[] fileArr = { new File("Ex1.java"), new File("Ex1.bak"), new File("Ex2.java"), new File("Ex1"),
				new File("Ex1.txt") };

		Stream<File> fileStream = Stream.of(fileArr);

		// map으로 stream<file>을 stream<String>으로 변환

		Stream<String> fileNameStream = fileStream.map(File::getName);
		fileNameStream.forEach(System.out::println);

		fileStream = Stream.of(fileArr);

		fileStream.map(File::getName).filter(s -> s.indexOf('.') != -1).map(s -> s.substring(s.indexOf('.') + 1))
				.map(String::toUpperCase).distinct().forEach(System.out::print);
		System.out.println();
	}

}
