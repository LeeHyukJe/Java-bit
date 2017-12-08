package week_15.연습문제;

public class File extends AbstractFile {
	private int size;

	public File(String name,int size) {
		super(name);
		// TODO Auto-generated constructor stub
		this.size=size;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		for(int i=0;i<getDepth();i++) {
			System.out.println(i);
		}
		
		System.out.println("file "+getName()+"Size "+size);
	}

}
