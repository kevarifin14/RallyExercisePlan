public class rallyExercisePlan{

	public static void main(String[] args){
		
		// Read file to get number of locations, list of locations, and goal
		String filename = args[0];
		In in = new In(filename);
		int index = in.readInt();
		Location[] locations = createLocationList(index, in);
		double goal = in.readDouble();

		// Establish start location.
		Location start = getOfficeLocation(locations);
		Location current = start;

		// Initialize the path.
		Location[] path = new Location[index+1];
		path[0] = start;
		path = makePath(start, current, locations, path, 1);

		// Get path distance.
		double pathDistance = pathDistance(path);
		
		double percentCompleted = goal/pathDistance * 100;
		// Percentage cannot be greater than 100%
		if (percentCompleted > 100){
			percentCompleted = 100;
		}

		// Output.
		for(Location l:path){
			StdOut.println(l.name);
		}
		StdOut.printf("%.1f%%\n",percentCompleted);
		StdOut.close();
	}

	private static Location findClosest(Location current, Location[] locations){
		/* 	first guess, making sure it does not compare distance with same location
			or a place that has been visited before */
		int index = 0;
		double shortest = current.distance(locations[index]);
		Location closest = locations[index];
		
		while(locations[index].visited){
			index += 1;
			shortest = current.distance(locations[index]);
			closest = locations[index];
		}
		
		// iteratively find the closest point that has not been visited
		for (Location l: locations){
			if (l.visited == false){
				double currentDistance = current.distance(l);
				if (currentDistance < shortest){
					shortest = currentDistance;
					closest = l;
				}
			}
		}
		return closest;
	}

	//Travelling Salesman Problem
	private static Location[] makePath(Location start, Location current, Location[] locations, Location[] path, int index){
		/* 	Recursively computes the path to take. Implement the solution 
			to the travelling salesman problem which computes the closest location 
			that has not yet been visited. */

		// base case when you have traveled each point once
		if (index == path.length - 1){
			path[index] = start;
			return path;
		}

		/* 	Recursively find the next path by calling function again with an updated path
			and moving the current position. */
			
		current = findClosest(current, locations);
		current.visited = true;
		path[index] = current;
		return makePath(start, current, locations, path, index+1);

	}

	private static double pathDistance(Location[] path){
		double distance = 0.0;
		for (int i=0; i<path.length-1; i++){
			distance += path[i].distance(path[i+1]);
		}
		return distance;
	}

	private static Location getOfficeLocation(Location[] locations){
		/* 	Takes in the list of locations and sets the office locations
			to as the starting point and returns it. */
		for (Location l: locations){
			if (l.name.equals("office")){
				l.visited = true;
				return l;
			}
		}	
		locations[0].visited = true;
		return locations[0];
	}

	private static Location[] createLocationList(int index, In in){
		/* 	Take in file input and how many locations, creating a
			a list of locations. Outputs this list. */
		Location[] locations = new Location[index];
		for (int i=0; i < index; i++){
			int x = in.readInt();
			int y = in.readInt();
			String name = in.readString();
			locations[i] = new Location(x,y,name);
		}
		return locations;
	}

}