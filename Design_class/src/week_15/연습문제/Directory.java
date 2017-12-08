package week_15.연습문제;

import java.util.*;

public class Directory extends AbstractFile {
	private List<AbstractFile> files=new ArrayList<>();
	
	public Directory(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void addEntry(AbstractFile entry) {
		entry.setDepth(getDepth()+1);
		files.add(entry);
	}
	
	public void removeEntry(AbstractFile entry) {
		files.remove(entry);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		int totalSize=0;
		for(AbstractFile entry:files) {
			totalSize+=entry.getSize();
		}
		return totalSize;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<getDepth();i++) {
			System.out.println("\t");
		}
		
		System.out.println("Directory"+getName()+" Size"+getSize());
		
		for(AbstractFile entry:files)
			entry.print();
	}

}
