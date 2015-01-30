import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlan{

	@Test
	public void TestDistance(){
		Location one = new Location(0,0,"one");
		Location two = new Location(3,4, "two");
		double distance = one.distance(two);
		double expected = 5.0;
		assertEquals(distance, expected, 0.0);
	}

	@Test
	public void TestGetOfficeLocation(){
		Location[] locations = new Location[2];
		Location office = new Location(0,0,"office");
		Location home = new Location(1,1,"home");
		locations[0] = office;
		locations[1] = home;
		Location result = rallyExercisePlan.getOfficeLocation(locations);
		assertEquals(office, result);
		assertEquals(true, office.visited);
	}

	@Test
	public void TestClosest(){
		Location[] locations = new Location[4];
		Location office = new Location(0,0,"office");
		Location mrSmiths = new Location(1,1,"mr-smiths");
		Location bakedAndWired = new Location(0,1,"baked-and-wired");
		Location amcGeorgetown = new Location(1,0,"amc-georgetown");
		locations[0] = office;
		locations[1] = mrSmiths;
		locations[2] = bakedAndWired;
		locations[3] = amcGeorgetown;
		Location closest = rallyExercisePlan.findClosest(office, locations);
		assertEquals(bakedAndWired, closest);
	}

	public static void main(String[] args){
		jh61b.junit.textui.runClasses(TestPlan.class);
	}

}