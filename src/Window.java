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
import org.eclipse.swt.widgets.Text;

public class Window {
	protected Shell shlSccPrototypev;
	private Label cruise;
	private Label speedLabel;
	private Display display;
	
	private Label trailingDistanceDisplay;

	private Vehicle vehicle;
	private Label blueCar;
	
	private ImageData blackTick;
	private ImageData alarmData;
	
	private Label black_tick;
	private Label black_tick1;
	private Label black_tick2;
	private Label black_tick3;
	private Label black_tick4;
	private Label blueCarSpeed;
	
	private BlueCar bCar;
	private Label myCar;
	
	private Text txtEnterBlueCar;
	private Label distanceBetweenCars;
	private Button resetBlue;
	private Button cruiseInc;
	private Button cruiseDecrement;
	
	private boolean gasPressed = false;
	private boolean brakesPressed = false;
	private Button syncSpeed;
	
	private boolean syncSpeedGate = true;
	private Label alarm;
	
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
		shlSccPrototypev.setToolTipText("Blue speed");
		shlSccPrototypev.setText("SCC2 PrototypeV2");
		
		ImageData backgroundImgData = SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData();
		shlSccPrototypev.setSize(backgroundImgData.width, backgroundImgData.height);
		shlSccPrototypev.setBackgroundImage(new Image(display, SWTResourceManager.getImage(Window.class, "/resources/background.png").getImageData()));
		
		Button cruiseActive = new Button(shlSccPrototypev, SWT.NONE);
		cruiseActive.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseActive();
				gasPressed = false;
				brakesPressed = false;
			}
		});
		cruiseActive.setBounds(180, 491, 140, 40);
		cruiseActive.setText("Cruise On");
	
		Button cruiseSuspend = new Button(shlSccPrototypev, SWT.NONE);
		cruiseSuspend.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseSuspend();
			}
		});
		cruiseSuspend.setBounds(200, 627, 140, 40);
		cruiseSuspend.setText("Cruise Suspend");
		
		Button cruiseOff = new Button(shlSccPrototypev, SWT.NONE);
		cruiseOff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseDisable();
			}
		});
		cruiseOff.setBounds(261, 689, 140, 40);
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
				//vehicle.decrementSpeed();
				brakesPressed = !brakesPressed;
				gasPressed = false;
				vehicle.cruiseSuspend();
			}
		});
		brakeButton.setBounds(403, 795, 43, 25);
		Button gasPedal = new Button(shlSccPrototypev, SWT.NONE);
		final Image temp = new Image(display, SWTResourceManager.getImage(Window.class, "/resources/gas_pedal.png").getImageData().scaledTo(20, 40));
		gasPedal.setImage(temp);
		gasPedal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//vehicle.incrementSpeed();
				gasPressed = !gasPressed;
				brakesPressed = false;
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
			}
		});
		btnTrailingDistance.setBounds(569, 533, 155, 75);
		btnTrailingDistance.setText("Trailing Distance +");
		
		Button reduceTrailing = new Button(shlSccPrototypev, SWT.NONE);
		reduceTrailing.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.decreaseDistance();
			}
		});
		reduceTrailing.setBounds(535, 639, 155, 75);
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
			}
		});
		setSpeed25.setText("SetSpeedTo25");
		
		Label lblScc = new Label(shlSccPrototypev, SWT.NONE);
		lblScc.setBounds(10, 10, 119, 99);
		lblScc.setText("SCC2 \nKira Chan \nIan Murray \nPrudhvi Kuchipudi \nBrandon Brooks \nZebin Liang");
		
		/////////////////////                       TICK MARKS                         ////////////////////////////
		blackTick = SWTResourceManager.getImage(Window.class, "/resources/black_line.png").getImageData();
		
		black_tick = new Label(shlSccPrototypev, SWT.NONE);
		black_tick.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		black_tick.setLocation(1035, 0);
		black_tick.setSize(blackTick.width, blackTick.height);
		black_tick.setImage(new Image(display, blackTick));
		
		black_tick1 = new Label(shlSccPrototypev, SWT.NONE);
		black_tick1.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		black_tick1.setLocation(1035, 225);
		black_tick1.setSize(blackTick.width, blackTick.height);
		black_tick1.setImage(new Image(display, blackTick));

		black_tick2 = new Label(shlSccPrototypev, SWT.NONE);
		black_tick2.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		black_tick2.setLocation(1035, 450);
		black_tick2.setSize(blackTick.width, blackTick.height);
		black_tick2.setImage(new Image(display, blackTick));
		
		black_tick3 = new Label(shlSccPrototypev, SWT.NONE);
		black_tick3.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		black_tick3.setLocation(1035, 675);
		black_tick3.setSize(blackTick.width, blackTick.height);
		black_tick3.setImage(new Image(display, blackTick));
		
		black_tick4 = new Label(shlSccPrototypev, SWT.NONE);
		black_tick4.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		black_tick4.setLocation(1035, -225);
		black_tick4.setSize(blackTick.width, blackTick.height);
		black_tick4.setImage(new Image(display, blackTick));
		
		myCar = new Label(shlSccPrototypev, SWT.NONE);
		myCar.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		myCar.setLocation(1094, 673);
		ImageData redCar =  SWTResourceManager.getImage(Window.class, "/resources/red_car.png").getImageData();
		myCar.setSize(redCar.width, redCar.height);
		myCar.setImage(new Image(display, redCar));
		
		blueCar = new Label(shlSccPrototypev, SWT.NONE);
		blueCar.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		blueCar.setLocation(1094, 100);
		ImageData blueCarData =  SWTResourceManager.getImage(Window.class, "/resources/blue_car.png").getImageData();
		blueCar.setSize(blueCarData.width, blueCarData.height);
		blueCar.setImage(new Image(display, blueCarData));
		
		blueCarSpeed = new Label(shlSccPrototypev, SWT.NONE);
		blueCarSpeed.setBounds(751, 22, 140, 25);
		blueCarSpeed.setText("Blue speed:");
		
		Button setBlueSpeedButton = new Button(shlSccPrototypev, SWT.NONE);
		setBlueSpeedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int tempInput = Integer.parseInt(txtEnterBlueCar.getText());
					if(tempInput >= 0 && tempInput <= 150)
					{
						bCar.setSpeed(tempInput);
						syncSpeedGate = false;
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		setBlueSpeedButton.setBounds(844, 51, 47, 25);
		setBlueSpeedButton.setText("Set");
		
		txtEnterBlueCar = new Text(shlSccPrototypev, SWT.BORDER);
		txtEnterBlueCar.setToolTipText("Blue speed");
		txtEnterBlueCar.setBounds(751, 53, 76, 21);
		
		distanceBetweenCars = new Label(shlSccPrototypev, SWT.NONE);
		distanceBetweenCars.setBounds(751, 123, 119, 25);
		distanceBetweenCars.setText("Distance:");
		
		resetBlue = new Button(shlSccPrototypev, SWT.NONE);
		resetBlue.setBounds(751, 80, 141, 29);
		resetBlue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				syncSpeedGate = true;
				bCar.resetLocation();
				
			}
		});
		resetBlue.setText("Reset Blue");
		
		cruiseInc = new Button(shlSccPrototypev, SWT.NONE);
		cruiseInc.setBounds(161, 562, 84, 46);
		cruiseInc.setText("Cruise +");
		cruiseInc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseIncrement();
			}
		});
		
		cruiseDecrement = new Button(shlSccPrototypev, SWT.NONE);
		cruiseDecrement.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vehicle.cruiseDecrement();
			}
		});
		cruiseDecrement.setBounds(261, 562, 84, 46);
		cruiseDecrement.setText("Cruise -");
		
		syncSpeed = new Button(shlSccPrototypev, SWT.NONE);
		syncSpeed.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				syncSpeedGate = !syncSpeedGate;
			}
		});
		syncSpeed.setBounds(751, 171, 119, 25);
		syncSpeed.setText("Sync Speed: On");
		
		alarm = new Label(shlSccPrototypev, SWT.NONE);
		alarm.setBounds(373, 435, 47, 66);
		alarm.setText("AEB");
		alarmData =  SWTResourceManager.getImage(Window.class, "/resources/alarm.png").getImageData();
		alarm.setSize(alarmData.width, alarmData.height);
		alarm.setImage(new Image(display, alarmData));
		
		bCar = new BlueCar(blueCar.getLocation().y);
		
		new Thread(new Runnable() {
		      public void run() {
		         while (true) {
		            try { Thread.sleep(50); } catch (Exception e) { }
		            Display.getDefault().asyncExec(new Runnable() {
		               public void run() {
		                  update();
		               }
		            });
		         }
		      }
		   }).start();
		
	}
	
	private void update()
	{
		updateDisplay();
		
		int distanceDifference = ((bCar.getLocation() - myCar.getLocation().y) + 130) *-1; //70 //DISTANCE
		distanceDifference += 50;

		int velocityDifference = (int) (vehicle.getSpeed() - bCar.getSpeed()); //relativeVelocity
		
		int res = AutomaticEmergencyBrake.run(velocityDifference, distanceDifference);
		
		if(res != -1) //5frame time to stop, AEB active
		{
			brakesPressed = false;
			gasPressed = false;

			alarm.setSize(alarmData.width, alarmData.height);
			
			System.out.println("relativeV + " + velocityDifference);
			int cnt = (int) (res);
			System.out.println(cnt);
			vehicle.reduceSpeed((int) Math.ceil(velocityDifference / Math.max(res, 1))); //relativeSpeed reduced by coefficient of distance
		} else { //regular car inputs

			alarm.setSize(0,0);
			if(brakesPressed)
			{
				vehicle.decrementSpeed();
			}else if(gasPressed)
			{
				vehicle.incrementSpeed();
			}

			updateCar();
		}
		
		
		
		
		if(syncSpeedGate)
		{
			bCar.speed = vehicle.getSpeed();
		}
		String result = (syncSpeedGate) ? "Sync Speed: On": "Sync Speed: Off";
		syncSpeed.setText(result);
		blueCarSpeed.setText("Blue speed: " + (int) bCar.getSpeed());
		bCar.update(vehicle.getSpeed());
		blueCar.setLocation(blueCar.getLocation().x, bCar.getLocation());
		
		distanceBetweenCars.setText("Distance: " + distanceDifference);
		int screenLimit = shlSccPrototypev.getSize().y;
		
		//blackticks
		if(black_tick.getLocation().y >= screenLimit)
		{
			//black_tick.setLocation(black_tick.getLocation().x, -225);
			//reset all tick locations
			black_tick.setLocation(black_tick.getLocation().x, -225);
			black_tick1.setLocation(black_tick.getLocation().x, 0);
			black_tick2.setLocation(black_tick.getLocation().x, 225);
			black_tick3.setLocation(black_tick.getLocation().x, 450);
			black_tick4.setLocation(black_tick.getLocation().x, 675);
		}else {
			black_tick.setLocation(black_tick.getLocation().x, (int) (black_tick.getLocation().y + vehicle.getSpeed()));
		}
		
		if(black_tick1.getLocation().y >= screenLimit)
		{
			black_tick1.setLocation(black_tick.getLocation().x, -225);
		}else {
			black_tick1.setLocation(black_tick.getLocation().x, (int) (black_tick1.getLocation().y + vehicle.getSpeed()));
		}
		
		if(black_tick2.getLocation().y >= screenLimit)
		{
			black_tick2.setLocation(black_tick.getLocation().x, -225);
		}else {
			black_tick2.setLocation(black_tick.getLocation().x, (int) (black_tick2.getLocation().y + vehicle.getSpeed()));
		}
		
		if(black_tick3.getLocation().y >= screenLimit)
		{
			black_tick3.setLocation(black_tick.getLocation().x, -225);
		}else {
			black_tick3.setLocation(black_tick.getLocation().x, (int) (black_tick3.getLocation().y + vehicle.getSpeed()));
		}
		
		if(black_tick4.getLocation().y >= screenLimit)
		{
			black_tick4.setLocation(black_tick.getLocation().x, -225);
		}else {
			black_tick4.setLocation(black_tick.getLocation().x, (int) (black_tick4.getLocation().y + vehicle.getSpeed()));
		}
	}
	
	private void updateCar()
	{
		if(vehicle.isCruiseActive() && gasPressed == false) //cruiseactive
		{
			if(vehicle.getSpeed() > vehicle.getCruiseSpeed())
			{
				vehicle.decrementSpeed();
			} else if (vehicle.getSpeed() < vehicle.getCruiseSpeed())
			{
				vehicle.incrementSpeed();
			}
		} 
	}
	
	private void updateDisplay()
	{
		speedLabel.setText("Speed: " + (int) vehicle.getSpeed());
		cruise.setText(vehicle.getCruiseInfo());
		trailingDistanceDisplay.setText(vehicle.getDistanceInfo());
	}
	
	private class BlueCar 
	{
		private double speed;
		private int location;
		private int origin;
		
		protected BlueCar(int myLocation)
		{
			this.speed = 0;
			this.location = myLocation;
			this.origin = myLocation;
		}
		
		protected void resetLocation()
		{
			this.location = this.origin;
		}
		
		protected int getLocation()
		{
			return this.location;
		}
		
		protected void setSpeed(double input)
		{
			this.speed = input;
		}
		
		protected double getSpeed()
		{
			return this.speed;
		}
		
		protected void update(double relativeSpeed)
		{
			//speed to move:mySpeed - relativeSpeed
			this.location += -(speed - relativeSpeed);
		}
	}
}
