package week_12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface Observer{
	public abstract void update();
}

abstract class Subject{
	private List<Observer> observers=new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	//통보대상 목록, 즉 observer의 각 옵저버에게 변경을 통보
	public void notifyObservers() {
		for(Observer o:observers) {
			o.update(); //통보
		}
	}
}

class ScoreRecord extends Subject{
	private List<Integer> scores=new ArrayList<>();
	
	public void addScore(int score) {
		scores.add(score); //점수를 넣음
		
		//데이터가 변경되면 Subject의 noti메소드를 이용해 변경을 통보함
		notifyObservers();
	}
	
	public List<Integer> getScoreRecord(){
		return scores;
	}
}

class DataSheetView implements Observer{
	private ScoreRecord scoreRecord;
	private int viewCount; //출력갯수
	
	public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
		this.scoreRecord=scoreRecord;
		this.viewCount=viewCount;
	}
	
	@Override
	public void update() { //변경된 점수를 통보받음
		// TODO Auto-generated method stub
		List<Integer> record=scoreRecord.getScoreRecord(); //점수를 조회
		displayScores(record,viewCount);
	}
	
	private void displayScores(List<Integer> record, int viewCount) {
		System.out.println("List of"+viewCount+"entries");
		for(int i=0;i<viewCount && i<record.size();i++) {
			System.out.println(record.get(i)+" ");
		}
		System.out.println();
	}
	
}

class MinMaxView implements Observer{
	private ScoreRecord scoreRecord;
	
	public MinMaxView(ScoreRecord scoreRecord) {
		this.scoreRecord=scoreRecord;
	}

	@Override
	public void update() { //변경점수 통보받음
		// TODO Auto-generated method stub
		List<Integer> record=scoreRecord.getScoreRecord(); //변경 점수 조회
		displayMinMax(record);
	}
	
	private void displayMinMax(List<Integer> record) {
		int min=Collections.min(record,null);
		int max=Collections.max(record,null);
		System.out.println("Min"+min+" "+"Max"+max);
	}
}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreRecord scoreRecord=new ScoreRecord();
		DataSheetView dataSheetView3=new DataSheetView(scoreRecord,3);
		DataSheetView dataSheetView5=new DataSheetView(scoreRecord,5);
		MinMaxView minMaxView=new MinMaxView(scoreRecord);
		
		scoreRecord.attach(dataSheetView3); //3개 목록 출력 옵저버 등록
		scoreRecord.attach(dataSheetView5); //5개 목록 출력 옵저버 등록
		
		scoreRecord.attach(minMaxView); //최대최소 목록 출력 옵저버 등록
		
		for(int index=1;index<=5;index++) {
			int score=index*10;
			System.out.println("Adding"+score);
			scoreRecord.addScore(score);
		}
		
	}

}
