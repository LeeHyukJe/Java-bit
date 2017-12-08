package week_15;

public class BasicNaviFactory extends NaviFactory{

	@Override
	public GPS createGPSFactory() {
		// TODO Auto-generated method stub
		return new CheapGPS();
	}

	@Override
	public Map createMapFactory() {
		// TODO Auto-generated method stub
		return new SmallMap();
	}

	@Override
	public PathFinder createPathFinderFactory() {
		// TODO Auto-generated method stub
		return new SlowPathFinder();
	}

	@Override
	public Screen createScreenFactory() {
		// TODO Auto-generated method stub
		return new SDScreen();
	}
	
}
