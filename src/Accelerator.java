

/**
 * 
 * Acceleration module of the vehicle
 *
 */
public class Accelerator extends ControlComponent {
	private boolean state;
	private Speedometer speedometer;
	
	protected Accelerator(Speedometer s)
	{
		this.state = false;
		this.speedometer = s;
	}
	
	@Override
	protected void activate() {
		state = true;
		speedometer.increment();
	}

	@Override
	protected void deactivate() {
		state = false;
		
	}

	@Override
	protected boolean getState() {
		return state;
	}
}
