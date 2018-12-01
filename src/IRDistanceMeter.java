

/**
 * This class interacts with the infrared distance hardware to measure distance between this vehicle and next
 * For purposes, we assume hardware updates every fixed interval
 */
public class IRDistanceMeter extends SensorComponent{
	protected double getData() {
		//returns the current distance
		return 0;
	}
}
