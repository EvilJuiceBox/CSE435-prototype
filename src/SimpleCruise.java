
public class SimpleCruise extends Module {
	private double speed; //the speed of the vehicle to maintain
	private String status; //false = off, true = on
	
	private final double MAX;
	private final double MIN;
	protected SimpleCruise(double max, double min)
	{
		speed = 0;
		this.MAX = max;
		this.MIN = min;
	}

	protected void setSpeed(int input)
	{
		this.speed = input;
		this.status = "Disabled";
	}
	
	protected void increment()
	{
		if(speed < MAX)
		{
			speed++;
		}
	}
	
	protected void decrement()
	{
		if(speed > MIN)
		{
			speed--;
		}
	}
	
	protected double getCruiseSpeed()
	{
		return this.speed;
	}
	
	protected String getStatus()
	{
		return this.status;
	}
	
	protected void maintainSpeed()
	{
		//this function is called every second in real-time to maintain set speed of the vehicle
	}
	
	/***
	 * resets the system and turns it off
	 */
	protected void cruiseDisable()
	{
		speed = 0;
		status = "Disabled";
	}
	
	/***
	 * 
	 * @param currentSpeed
	 * @return true if set active, false if not > 25 or failure
	 */
	protected boolean cruiseEnable(double currentSpeed)
	{
		if(currentSpeed >= 25)
		{
			this.speed = currentSpeed;
			this.status = "On";
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * sets cruise inactive, keeps speed for resume
	 */
	protected void cruiseSuspend()
	{
		if(this.status == "On")
		{
			this.status = "Suspended";
		}
	}
	
	/**
	 * resumes the system
	 */
	protected void cruiseResume()
	{
		this.status = "On";
	}
}
