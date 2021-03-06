/*
 * Speedometer class, a real vehicle will only have getdata function
 * for prototype, speedometer controls the speed of the vehicle as well
 */
public class Speedometer extends SensorComponent{

	private final double MAX;
	private final double MIN;
	private double speed;
	
	protected Speedometer(double max, double min) {
		speed = 0;
		this.MAX = max;
		this.MIN = min;
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
	
	protected void reduce(int input)
	{
		speed -= input;
		if(speed < 0)
		{
			speed = 0;
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
