/**
 * Abstract class for control components
 */
public abstract class ControlComponent extends Component {
	abstract protected void activate();
	abstract protected void deactivate();
	abstract protected boolean getState();
}
