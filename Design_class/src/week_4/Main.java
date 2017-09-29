package week_4;
import java.util.*;

class Student{
	private Vector<Transcript> transcripts;
	private String name;
	
	public Student(String name) {
		this.name=name;
		transcripts=new Vector<Transcript>();
	}
	
	public void addTranscript(Transcript transcript) {
		transcripts.add(transcript);
	}
}

class Transcript{
	private Student student;
	private Course course;
	private String date;
	private String grade;
	
	public Transcript(Student student, Course course) {
		this.student=student;
		this.student.addTranscript(this);
		this.course=course;
		this.course.addTranscript(this);
	}
	
	public Student getStudent() {
		return student;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setGrade(String grade) {
		this.grade=grade;
	}
	
	public String getGrade() {
		return grade;
	}
}

class Course{
	private Vector<Transcript> transcripts;
	private String name;
	
	public Course(String name) {
		this.name=name;
		transcripts=new Vector<Transcript>();
	}
	
	public void addTranscript(Transcript transcript) {
		transcripts.add(transcript);
	}
	
	public String name() {
		return name;
	}
	
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
