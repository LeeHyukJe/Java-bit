package week_15;

public abstract class NaviFactory {
	public abstract GPS createGPSFactory();
	public abstract Map createMapFactory();
	public abstract PathFinder createPathFinderFactory();
	public abstract Screen createScreenFactory();
}
