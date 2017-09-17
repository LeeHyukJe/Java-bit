package Director;

class Actor {
	protected String name;

	Actor(String name) {
		this.name = name;
	}

	public void acting(String name) {
		System.out.println("배우 " + name + " 연기 합니다.");
	}
}

class ActionActor extends Actor {
	String expert;

	ActionActor(String actor, String expert) {
		super(actor);
		this.expert = expert;
	}

	public void prctice() {
		System.out.println("액션 배우 " + name + "은 " + expert + "를 연습합니다.");
	}

	@Override
	public void acting(String name) {
		System.out.println("배우 " + name + "은 액션 연기를 합니다.");
	}
}

public class Director {
	int numOfactors;
	Actor actors[]=new Actor[10];

	public void cast(Actor actor) {

		actors[numOfactors] = actor;
		numOfactors++;
	}
	
	public void cast(String name) {
		actors[numOfactors]=new Actor(name);
		numOfactors++;
	}

	public void cast(String actor, String expert) {
		actors[numOfactors] = new ActionActor(actor,expert);
		numOfactors++;
	}
	
	public void direct() {
		for(int i=0;i<actors.length;i++) {
			if(actors[i] instanceof ActionActor) {
				((ActionActor)actors[i]).prctice();
				actors[i].acting(actors[i].name);
			}
			else if(actors[i] instanceof Actor)
				actors[i].acting(actors[i].name);
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Director d = new Director();
		Actor a = new Actor("정우성");
		Actor b = new ActionActor("류승범", "돌려차기");

		d.cast(a);
		d.cast(b);
		d.cast("고현정");
		d.cast("공효진","발차기");
		d.direct();
	}

}
