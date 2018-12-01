
public class AutomaticEmergencyBrake {
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
}
