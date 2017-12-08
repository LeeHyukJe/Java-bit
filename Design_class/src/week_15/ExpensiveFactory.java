package week_15;

public class ExpensiveFactory extends NaviFactory{

	@Override
	public GPS createGPSFactory() {
		// TODO Auto-generated method stub
		return new ExpensiveGPS();
	}

	@Override
	public Map createMapFactory() {
		// TODO Auto-generated method stub
		return new LargeMap();
	}

	@Override
	public PathFinder createPathFinderFactory() {
		// TODO Auto-generated method stub
		return new FastPathFinder();
	}

	@Override
	public Screen createScreenFactory() {
		// TODO Auto-generated method stub
		return new HDScreen();
	}

}
