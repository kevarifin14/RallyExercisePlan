public class Location{
	public int x, y;
	public String name;
	public Boolean visited;

	public Location(int x_pos, int y_pos, String locationName){
		x = x_pos;
		y = y_pos;
		name = locationName;
		visited = false;

	}

	public double distance(Location l){
		/* 	Calculates the distance between this location and
			location l. */
		double distance = Math.sqrt((this.x - l.x)*(this.x - l.x) + (this.y - l.y)*(this.y - l.y));
		return distance;
	}
}