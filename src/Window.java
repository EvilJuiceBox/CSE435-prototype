import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class Window {

	protected Shell shell;
	private Label cruise;
	private int speed;
	private Label speedLabel;
	private Display display;
	
	private Label trailingDistanceDisplay;

	private Vehicle vehicle;
	
	
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
		vehicle = new Vehicle(2018, "focus");
		
		
		display = Display.getDefault(); //Display
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN );
		shell.setText("SCC2 PrototypeV1");
		
		ImageData backgroundImgData = SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData();
		shell.setSize(backgroundImgData.width, backgroundImgData.height);
		shell.setBackgroundImage(new Image(display, SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData()));
		
		Button cruiseActive = new Button(shell, SWT.NONE);
		cruiseActive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("CRUISE");
			}
		});
		cruiseActive.setBounds(180, 491, 100, 40);
		cruiseActive.setText("Cruise On");
		
		speedLabel = new Label(shell, SWT.NONE);
		speedLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		speedLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		speedLabel.setBounds(389, 150, 128, 25);
		speedLabel.setText("Speed: 0");
		
		Button cruiseSuspend = new Button(shell, SWT.NONE);
		cruiseSuspend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("");
			}
		});
		cruiseSuspend.setBounds(215, 581, 100, 40);
		cruiseSuspend.setText("Cruise Suspend");
		
		Button cruiseOff = new Button(shell, SWT.NONE);
		cruiseOff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cruise.setText("Cruise Cleared");
			}
		});
		cruiseOff.setBounds(260, 663, 100, 40);
		cruiseOff.setText("Cruise Off");
		
		Button brakeButton = new Button(shell, SWT.NONE);
		final Image brakeImg = new Image(display, SWTResourceManager.getImage(Window.class, "/resources/brake.png").getImageData().scaledTo(40, 20));
		brakeButton.setImage(brakeImg);
		brakeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.decrementSpeed();
				updateDisplay();
			}
		});
		brakeButton.setBounds(403, 795, 43, 25);
		
		Button gasPedal = new Button(shell, SWT.NONE);
		final Image temp = new Image(display, SWTResourceManager.getImage(Window.class, "/resources/gas_pedal.png").getImageData().scaledTo(20, 40));
		gasPedal.setImage(temp);
		gasPedal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.incrementSpeed();
				updateDisplay();
			}
		});
		gasPedal.setBounds(485, 784, 32, 47);
		//btnGas.setImage(new ImageIcon(this.getClass().getResource("./gas_pedal.png")).getImage());
		
		trailingDistanceDisplay = new Label(shell, SWT.NONE);
		trailingDistanceDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		trailingDistanceDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		trailingDistanceDisplay.setBounds(389, 223, 119, 31);
		trailingDistanceDisplay.setText("Off");
		
		Button btnTrailingDistance = new Button(shell, SWT.NONE);
		btnTrailingDistance.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trailingDistanceDisplay.setText("Trailing Distance: 1");
			}
		});
		btnTrailingDistance.setBounds(580, 544, 130, 50);
		btnTrailingDistance.setText("Trailing Distance +");
		
		Button reduceTrailing = new Button(shell, SWT.NONE);
		reduceTrailing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				trailingDistanceDisplay.setText("Trailing distnace subtracted!");
			}
		});
		reduceTrailing.setBounds(553, 642, 130, 50);
		reduceTrailing.setText("Trailing Distance -");
		
		cruise = new Label(shell, SWT.NONE);
		cruise.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cruise.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		cruise.setBounds(389, 260, 100, 40);
		
		Label lblTrailingDistance = new Label(shell, SWT.NONE);
		lblTrailingDistance.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTrailingDistance.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblTrailingDistance.setBounds(389, 192, 119, 25);
		lblTrailingDistance.setText("Trailing Distance:");

	}
	
	private void updateDisplay()
	{
		speedLabel.setText("Speed: " + (int) vehicle.getSpeed());
	}
}
