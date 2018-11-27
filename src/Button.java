/**
 * Button controlling an input
 */
public class Button extends IOComponent{
	private String name; //name of the button
	private boolean latched;
	private int time;
	
	protected Button(String n)
	{
		this.name = n;
		latched = false;
	}
	
	protected void press()
	{
		time++;
	}
	
	protected void latch()
	{
		latched = true;
	}
	
	protected void delatch()
	{
		latched = false;
	}
	
	protected int getTimeDepressed()
	{
		return this.time;
	}
	
	protected boolean getState()
	{
		return this.latched;
	}
}
