
public class Vehicle {
	private static final double MAX = 150;
	private static final double MIN = 0;
	private static final double CRUISE_MIN = 25;
	
	private int year;
	private String model;
	
	private Accelerator accelerator;
	private Brake brake;
	private Speedometer speedometer;
	private Dashboard dashboard;
	
	private SimpleCruise simpleCruise;
	private FollowingDistanceManagement followingDistanceManagement;
	private AutomaticEmergencyBrake aeb;
	
	public int speed = 0;
	protected Vehicle(int y, String m)
	{
		this.year = y;
		this.model = m;
		
		speedometer = new Speedometer(MAX, MIN);
		accelerator = new Accelerator(speedometer);
		brake = new Brake(speedometer);
		dashboard = new Dashboard();
		
		
		simpleCruise = new SimpleCruise(MAX, CRUISE_MIN);
		followingDistanceManagement = new FollowingDistanceManagement();
		aeb = new AutomaticEmergencyBrake();
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
	
	protected void reduceSpeed(int input)
	{
		speedometer.reduce(input);
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
	
	protected double getCruiseSpeed()
	{
		return simpleCruise.getCruiseSpeed();
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
	
	protected void cruiseIncrement()
	{
		simpleCruise.increment();
	}
	
	protected void cruiseDecrement()
	{
		simpleCruise.decrement();
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
	
	protected boolean isFDMActive()
	{
		return followingDistanceManagement.isActive();
	}
	
	protected int getFollowingDistance()
	{
		return followingDistanceManagement.getDistance();
	}
}
