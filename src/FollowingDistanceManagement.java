
public class FollowingDistanceManagement {
	private int followingDistance;
	
	private static final int MAX = 4;
	private static final int MIN = 0;
	
	protected FollowingDistanceManagement()
	{
		followingDistance = 0;
	}
	
	//increments distance
	protected void increment()
	{
		if(followingDistance < MAX)
		{
			followingDistance++;
		}
	}
	
	protected void decrement() {
		if(followingDistance > MIN)
		{
			followingDistance--;
		}
	}
	
	protected int getDistance()
	{
		return this.followingDistance;
	}
	
	protected void reset()
	{
		followingDistance = 0;
	}
	
	//Real implementation functions
	protected void updateRawData()
	{
		//gets data from surrounding environment
		String data = "";
		boolean safe = Firewall.checkInput(data);
		if(safe)
		{
			//vehicle response
			/**
			 * if(distance < emergencyThreshold)
			 * {
			 * 		alarmFlash();
			 * 		brake();
			 * }
			 * if(data.distance > threshold)
			 * {
			 * 		speed++;
			 * } else if(distance < threshold)
			 * {
			 * 		speed--;
			 * }
			 * 
			 */
			
		}
	}
}
