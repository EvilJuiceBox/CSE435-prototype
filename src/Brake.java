/**
 * 
 * instance of brakes
 *
 */
public class Brake extends ControlComponent {
	private boolean state;
	private Speedometer speedometer;
	
	protected Brake(Speedometer s)
	{
		this.state = false;
		this.speedometer = s;
	}
	
	@Override
	protected void activate() {
		state = true;
		speedometer.decrement();
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
