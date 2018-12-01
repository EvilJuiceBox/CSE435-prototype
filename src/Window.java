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

	protected Shell shlSccPrototypev;
	private Label cruise;
	private int speed;
	private Label speedLabel;
	private Display display;
	
	private Label trailingDistanceDisplay;

	private Vehicle vehicle;
	
	private ImageData fdm0;
	private ImageData fdm1;
	private ImageData fdm2;
	private ImageData fdm3;
	private ImageData fdm4;
	
	private Label fdmImg;
	
	
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
		fdm0 =  SWTResourceManager.getImage(Window.class, "/resources/fdm0.png").getImageData();
		fdm1 =  SWTResourceManager.getImage(Window.class, "/resources/fdm1.png").getImageData();
		fdm2 =  SWTResourceManager.getImage(Window.class, "/resources/fdm2.png").getImageData();
		fdm3 =  SWTResourceManager.getImage(Window.class, "/resources/fdm3.png").getImageData();
		fdm4 =  SWTResourceManager.getImage(Window.class, "/resources/fdm4.png").getImageData();
		
		display = Display.getDefault(); //Display
		createContents();
		shlSccPrototypev.open();
		shlSccPrototypev.layout();
		while (!shlSccPrototypev.isDisposed()) {
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
		shlSccPrototypev = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN );
		shlSccPrototypev.setText("SCC2 PrototypeV2");
		
		ImageData backgroundImgData = SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData();
		shlSccPrototypev.setSize(backgroundImgData.width, backgroundImgData.height);
		shlSccPrototypev.setBackgroundImage(new Image(display, SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData()));
		
		Button cruiseActive = new Button(shlSccPrototypev, SWT.NONE);
		cruiseActive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseActive();
				updateDisplay();
			}
		});
		cruiseActive.setBounds(180, 491, 140, 40);
		cruiseActive.setText("Cruise On");
	
		Button cruiseSuspend = new Button(shlSccPrototypev, SWT.NONE);
		cruiseSuspend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseSuspend();
				updateDisplay();
			}
		});
		cruiseSuspend.setBounds(201, 583, 140, 40);
		cruiseSuspend.setText("Cruise Suspend");
		
		Button cruiseOff = new Button(shlSccPrototypev, SWT.NONE);
		cruiseOff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseDisable();
				updateDisplay();
			}
		});
		cruiseOff.setBounds(251, 673, 140, 40);
		cruiseOff.setText("Cruise Off");
		
		
		////////////           Speed label          /////////////
		speedLabel = new Label(shlSccPrototypev, SWT.NONE);
		speedLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		speedLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		speedLabel.setBounds(373, 152, 130, 34);
		speedLabel.setText("Speed: 0");
		
		
		Button brakeButton = new Button(shlSccPrototypev, SWT.NONE);
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
		
		Button gasPedal = new Button(shlSccPrototypev, SWT.NONE);
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
		
		trailingDistanceDisplay = new Label(shlSccPrototypev, SWT.NONE);
		trailingDistanceDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		trailingDistanceDisplay.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		trailingDistanceDisplay.setBounds(408, 223, 84, 31);
		trailingDistanceDisplay.setText("Off");
		
		Button btnTrailingDistance = new Button(shlSccPrototypev, SWT.NONE);
		btnTrailingDistance.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.increaseDistance();
				updateDisplay();
			}
		});
		btnTrailingDistance.setBounds(580, 544, 130, 50);
		btnTrailingDistance.setText("Trailing Distance +");
		
		Button reduceTrailing = new Button(shlSccPrototypev, SWT.NONE);
		reduceTrailing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.decreaseDistance();
				updateDisplay();
			}
		});
		reduceTrailing.setBounds(553, 642, 130, 50);
		reduceTrailing.setText("Trailing Distance -");
		
		cruise = new Label(shlSccPrototypev, SWT.NONE);
		cruise.setText("Cruise Off");
		cruise.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		cruise.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		cruise.setBounds(373, 272, 147, 45);
		
		Label lblTrailingDistance = new Label(shlSccPrototypev, SWT.NONE);
		lblTrailingDistance.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTrailingDistance.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblTrailingDistance.setBounds(373, 192, 119, 25);
		lblTrailingDistance.setText("Trailing Distance:");
		
		Button setSpeed25 = new Button(shlSccPrototypev, SWT.NONE);
		setSpeed25.setBounds(97, 784, 155, 66);
		setSpeed25.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				vehicle.setSpeed(25);
				updateDisplay();
			}
		});
		setSpeed25.setText("SetSpeedTo25");
		
		Label lblScc = new Label(shlSccPrototypev, SWT.NONE);
		lblScc.setBounds(10, 10, 119, 99);
		lblScc.setText("SCC2 \nKira Chan \nIan Murray \nPrudhvi Kuchipudi \nBrandon Brooks \nZebin Liang");
		
		fdmImg = new Label(shlSccPrototypev, SWT.NONE);
		fdmImg.setLocation(680, 58);
		fdmImg.setSize(fdm0.width, fdm0.height);
		fdmImg.setBackgroundImage(new Image(display, fdm0));
	}
	
	private void updateDisplay()
	{
		speedLabel.setText("Speed: " + (int) vehicle.getSpeed());
		cruise.setText(vehicle.getCruiseInfo());
		trailingDistanceDisplay.setText(vehicle.getDistanceInfo());
		
		switch(vehicle.getDistance())
		{
		case 0 : 
			fdmImg.setBackgroundImage(new Image(display, fdm0));
			break;
		case 1 : 
			fdmImg.setBackgroundImage(new Image(display, fdm1));
			break;
		case 2 : 
			fdmImg.setBackgroundImage(new Image(display, fdm2));
			break;
		case 3 : 
			fdmImg.setBackgroundImage(new Image(display, fdm3));
			break;
		case 4 : 
			fdmImg.setBackgroundImage(new Image(display, fdm4));
			break;
		default:
			fdmImg.setBackgroundImage(new Image(display, fdm0));
		}
	}
}
