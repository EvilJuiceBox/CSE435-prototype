import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;

public class Window {

	protected Shell shell;
	private Label cruise;
	private int speed;
	private Label speedLabel;
	private Display display;
	
	private Label lblTrailingDistanceOff;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault(); //Display
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		speed = 0;
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN );
		shell.setSize(450, 300);
		shell.setText("SCC2 PrototypeV1");
		
		Button cruiseActive = new Button(shell, SWT.NONE);
		cruiseActive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("CRUISE");
			}
		});
		cruiseActive.setBounds(47, 100, 87, 25);
		cruiseActive.setText("Cruise On");
		
		speedLabel = new Label(shell, SWT.NONE);
		speedLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		speedLabel.setBounds(190, 27, 57, 25);
		speedLabel.setText("Speed: ");
		
		Button cruiseSuspend = new Button(shell, SWT.NONE);
		cruiseSuspend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("");
			}
		});
		cruiseSuspend.setBounds(47, 142, 87, 25);
		cruiseSuspend.setText("Cruise Suspend");
		
		Button cruiseOff = new Button(shell, SWT.NONE);
		cruiseOff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("Cruise Cleared");
			}
		});
		cruiseOff.setBounds(47, 180, 87, 25);
		cruiseOff.setText("Cruise Off");
		
		Button brakeButton = new Button(shell, SWT.NONE);
		final Image brakeImg = new Image(display, SWTResourceManager.getImage(Window.class, "/resources/brake.png").getImageData().scaledTo(40, 20));
		brakeButton.setImage(brakeImg);
		brakeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				decrementSpeed();
			}
		});
		brakeButton.setBounds(186, 215, 43, 25);
		
		Button gasPedal = new Button(shell, SWT.NONE);
		final Image temp = new Image(display, SWTResourceManager.getImage(Window.class, "/resources/gas_pedal.png").getImageData().scaledTo(20, 40));
		gasPedal.setImage(temp);
		gasPedal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				incrementSpeed();
			}
		});
		gasPedal.setBounds(235, 204, 32, 47);
		//btnGas.setImage(new ImageIcon(this.getClass().getResource("./gas_pedal.png")).getImage());
		
		lblTrailingDistanceOff = new Label(shell, SWT.NONE);
		lblTrailingDistanceOff.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTrailingDistanceOff.setBounds(156, 70, 129, 53);
		lblTrailingDistanceOff.setText("Trailing Distance: Off");
		
		Button btnTrailingDistance = new Button(shell, SWT.NONE);
		btnTrailingDistance.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblTrailingDistanceOff.setText("Trailing Distance: 1");
			}
		});
		btnTrailingDistance.setBounds(312, 122, 112, 25);
		btnTrailingDistance.setText("Trailing Distance +");
		
		Button reduceTrailing = new Button(shell, SWT.NONE);
		reduceTrailing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblTrailingDistanceOff.setText("Trailing distnace subtracted!");
			}
		});
		reduceTrailing.setBounds(312, 163, 112, 25);
		reduceTrailing.setText("Trailing Distance -");
		
		cruise = new Label(shell, SWT.BORDER);
		cruise.setBounds(177, 146, 76, 21);

	}
	
	
	private void incrementSpeed()
	{
		speed++;
		speedLabel.setText("Speed: " + speed);
	}
	
	private void decrementSpeed()
	{
		if(speed > 0)
		{
			speed--;
		}
		speedLabel.setText("Speed: " + speed);
	}
}
