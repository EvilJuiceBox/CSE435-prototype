/*
 * Speedometer class, a real vehicle will only have getdata function
 * for prototype, speedometer controls the speed of the vehicle as well
 */
public class Speedometer extends SensorComponent{
	private static final double MAX = 150;
	private static final double MIN = 0;
	
	private double speed;
	
	protected Speedometer() {
		speed = 0;
	}
	
	/*
	 * this method is for prototype only. Real version will not allow you to set speed
	 */
	protected void setSpeed(double input) {
		this.speed = input;
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
	
	protected double getSpeed()
	{
		return this.speed;
	}

	@Override
	protected double getData() {
		//RETURNS RAW DATA OF THE VEHICLE
		return 0;
	}
}
