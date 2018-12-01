
public class Vehicle {
	private int year;
	private String model;
	
	private Accelerator accelerator;
	private Brake brake;
	private Speedometer speedometer;
	private Dashboard dashboard;
	
	private SimpleCruise simpleCruise;
	private FollowingDistanceManagement followingDistanceManagement;
	
	public int speed = 0;
	protected Vehicle(int y, String m)
	{
		this.year = y;
		this.model = m;
		
		speedometer = new Speedometer();
		accelerator = new Accelerator(speedometer);
		brake = new Brake(speedometer);
		dashboard = new Dashboard();
		
		
		simpleCruise = new SimpleCruise();
		followingDistanceManagement = new FollowingDistanceManagement();
	}
	
	protected void setSpeed(double input)
	{
		speedometer.setSpeed(input);
	}
	
	/**
	 * methods increasing speed of the vehicle
	 */
	protected void incrementSpeed()
	{
		speedometer.increment();
	}
	
	/**
	 * method decreasing the speed of the vehicle
	 */
	protected void decrementSpeed()
	{
		speedometer.decrement();
	}
	
	public double getSpeed()
	{
		return speedometer.getSpeed();
	}
	
	
	//////////////////////                Functions to simple cruise system                  ////////////////////
	
	/*
	 * resumes cruise if in suspend state, else starts
	 */
	protected void cruiseActive()
	{
		if(simpleCruise.getStatus() == "Suspended")
		{
			this.cruiseResume();
			return;
		}
		this.cruiseEnable();
	}
	
	protected void cruiseEnable()
	{
		simpleCruise.cruiseEnable(speedometer.getSpeed());
	}
	
	protected void cruiseDisable()
	{
		simpleCruise.cruiseDisable();
		followingDistanceManagement.reset();
	}
	
	protected void cruiseSuspend()
	{
		simpleCruise.cruiseSuspend();
		followingDistanceManagement.reset();
	}
	
	protected void cruiseResume()
	{
		simpleCruise.cruiseResume();
	}

	protected String getCruiseInfo()
	{
		if(simpleCruise.getStatus() == "On")
		{
			return "Cruise On: " + (int) simpleCruise.getCruiseSpeed();
		} else if(simpleCruise.getStatus() == "Suspended")
		{
			return "Cruise Suspended: " + (int) simpleCruise.getCruiseSpeed();
		}
		
		return "Cruise Off";
	}
	
	protected boolean isCruiseActive()
	{
		return (simpleCruise.getStatus() == "On") ? true : false;
	}
	
	
	///////////////////////           Function for FollowingDistanceManagement          /////////////////////
	protected void increaseDistance()
	{
		if(isCruiseActive())
		{
			followingDistanceManagement.increment();
		}
	}
	
	protected void decreaseDistance()
	{
		followingDistanceManagement.decrement();
	}
	
	protected String getDistanceInfo()
	{
		return (followingDistanceManagement.getDistance() == 0) ? "Off" : "" + followingDistanceManagement.getDistance();
	}
	
	protected int getDistance()
	{
		return followingDistanceManagement.getDistance();
	}
}
