
public class AutomaticEmergencyBrake {
	private static final int THRESHOLD = 5;
	
	public static void checkEnviroment()
	{
		//this method is involved every 1/10 of a second, checks the vehicle's surround and brakes after sounding alarm to prevent a crash
		//gets data from surrounding environment
		String data = ""; //getdatafromenv()
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
			 * 
			 */
		}
	}
	
	public static int run(int relativeVelocity, int distance) //returns true if aeb active
	{
		//EMERGENCY BRAKE, TAKES OVER CONTROL
		int reactionFrames;
		try {
			reactionFrames = distance/relativeVelocity;
		} catch (Exception e)
		{
			reactionFrames = THRESHOLD+1; //THRESHOLD + 1
		}
		
		if(reactionFrames < THRESHOLD && relativeVelocity > 0)
		{
			return reactionFrames;
		}
		return -1;
	}
}
