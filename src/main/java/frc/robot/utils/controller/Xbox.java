package frc.robot.utils.controller;

import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.utils.controller.button.JoystickButton;

public class Xbox extends Controller{
	
	public static final int A = 1, B = 2, X = 3, Y = 4, BACK = 7, START = 8;
	public static final int LT = 2, RT = 3;
	public static final int RIGHT_HORIZONTAL = 4, RIGHT_VERTICAL = 5;
	
	public Xbox(int port) {
		super(port);

		buttons[X] = new JoystickButton(getStick(), X);
		buttons[A] = new JoystickButton(getStick(), A);
		buttons[B] = new JoystickButton(getStick(), B);
		buttons[Y] = new JoystickButton(getStick(), Y);
		buttons[START] = new JoystickButton(getStick(), START);
		buttons[BACK] = new JoystickButton(getStick(), BACK);
		
	}
	
	public double getLTAngle(){
		return getStick().getRawAxis(LT);
	}
	
	public Button getLTButton() {
		return new Button() {

			@Override
			public boolean get() {
				// TODO Auto-generated method stub
				return getStick().getRawAxis(LT) > 0.5;
			}
			
		};
	}
	
	public double getRTAngle(){
		return getStick().getRawAxis(RT);
	}
	
	public Button getRTButton() {
		return new Button() {

			@Override
			public boolean get() {
				// TODO Auto-generated method stub
				return getStick().getRawAxis(RT) > 0.5;
			}
			
		};
	}	
	
	public double getAxis(int axisName) {
		
		double stickVal = getStick().getRawAxis(axisName);
		
		if(stickVal < DEADZONE && stickVal > -DEADZONE) {
			stickVal = 0;
		}
		
		if(axisName == LEFT_VERTICAL || axisName == RIGHT_VERTICAL) {
			stickVal *= -1;
		}
		
		return stickVal;
	}

}
