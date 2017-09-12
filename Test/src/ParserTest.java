
interface Parseable{
	public abstract void parse(String fileName);
}

class ParserManager{
	public static Parseable getParser(String type) {
		if(type.equals("XML")) {
			return new XMLParser();
		}
		else {
//			Parseable p=new HTMLParser();
//			return p;
			return new HTMLParser();
		}
	}
}

class XMLParser implements Parseable{
	public void parse(String fileName) {
		System.out.println(fileName+"- XML parsing completed.");
	}
}

class HTMLParser implements Parseable{
	public void parse(String fileName) {
		System.out.println(fileName+"- HTML parsing completed.");
	}
}
public class ParserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parseable Parser=ParserManager.getParser("XML");
		Parser.parse("document.xml"); //인터페이스를 구현한 클래스를 리턴하기 때문에 인터페이스에서 구현한 클래스의 메소드를 구현 할 수 있다. 
		Parser=ParserManager.getParser("HTML");
		Parser.parse("document.html");
	}

}
