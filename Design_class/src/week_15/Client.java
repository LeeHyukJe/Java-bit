package week_15;

public class Client {
	BasicNaviFactory basic=new BasicNaviFactory();
	GPS gps=basic.createGPSFactory();
	Screen mapScreen=basic.createScreenFactory();
	PathFinder pathFinder=basic.createPathFinderFactory();
	
	Map map=basic.createMapFactory();
	
}
