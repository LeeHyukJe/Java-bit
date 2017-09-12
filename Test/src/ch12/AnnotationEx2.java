package ch12;

import java.util.ArrayList;

class NewClass{
	int newField;
	int getNewField() {
		return newField;
	}
	
	@Deprecated
	int oldField;
	
	@Deprecated
	int getOldField() {
		return oldField;
	}
}

public class AnnotationEx2 {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewClass nc=new NewClass();
		
		@SuppressWarnings("rawtypes")
		ArrayList list=new ArrayList();
		nc.oldField=10;
		System.out.println(nc.getOldField());
	}

}
