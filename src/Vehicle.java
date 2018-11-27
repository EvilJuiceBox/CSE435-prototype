
public class Vehicle {
	private int year;
	private String model;
	
	private Accelerator accelerator;
	private Brake brake;
	private Speedometer speedometer;
	private Dashboard dashboard;
	
	protected Vehicle(int y, String m)
	{
		this.year = y;
		this.model = m;
		
		speedometer = new Speedometer();
		accelerator = new Accelerator(speedometer);
		brake = new Brake(speedometer);
		dashboard = new Dashboard();
	}
	
	protected double getSpeed()
	{
		return speedometer.getSpeed();
	}
}
