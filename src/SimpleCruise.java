
public class SimpleCruise extends Module {
	private double speed; //the speed of the vehicle to maintain
	private boolean status; //false = off, true = on
	
	protected SimpleCruise()
	{
		speed = 0;
	}

	protected void setSpeed(int input)
	{
		this.speed = input;
		this.status = false;
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
		status = false;
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
			this.status = true;
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
		this.status = false;
	}
	
	/**
	 * resumes the system
	 */
	protected void cruiseResume()
	{
		this.status = true;
	}
}
