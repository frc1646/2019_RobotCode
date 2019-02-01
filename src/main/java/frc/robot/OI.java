/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CycleDriveMode;
import frc.robot.commands.DriveToBall;
import frc.robot.commands.DriveToHatchTarget;
import frc.robot.commands.ResetGyro;
import frc.robot.commands.ShiftDown;
import frc.robot.commands.ShiftUp;
import frc.robot.commands.TankPIDTest;
import frc.robot.commands.ToggleShift;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private static OI instance;
  private Joystick driver_controller, operator_controller;
  private JoystickButton yButton, aButton, startButton, xButton, bButton;
  private JoystickButton yOpButton, aOpButton, xOpButton, bOpButton;
  private JoystickButton right_bumper;
  private JoystickButton backButton;

  private OI() {
    driver_controller = new Joystick(RobotMap.DRIVER_CONTROLLER_PORT);
    operator_controller = new Joystick(RobotMap.OPERATOR_CONTROLLER_PORT);

    yButton = new JoystickButton(driver_controller, 4);
    aButton = new JoystickButton(driver_controller, 1);
    startButton = new JoystickButton(driver_controller, 8);
    xButton = new JoystickButton(driver_controller, 3);
    //right_bumper = new JoystickButton(driver_controller, 6);
    bButton = new JoystickButton(driver_controller, 2);
    backButton = new JoystickButton(driver_controller, 7);


    yOpButton = new JoystickButton(operator_controller, 4);
    aOpButton = new JoystickButton(operator_controller, 1);
    xOpButton = new JoystickButton(operator_controller, 3);
    bOpButton = new JoystickButton(operator_controller, 2);
    


    yButton.whenPressed(new ShiftUp());
    aButton.whenPressed(new ShiftDown());
    startButton.whenPressed(new CycleDriveMode());
    bButton.whenActive(new ResetGyro());
    //bButton.whileHeld(new DriveToBall());
    //right_bumper.whenPressed(new ToggleShift());
    xButton.whileHeld(new DriveToHatchTarget());
    backButton.whileHeld(new TankPIDTest(0.2));
  
    yOpButton.whileHeld(new TankPIDTest(0.2));
    aOpButton.whileHeld(new TankPIDTest(0.4));
    bOpButton.whileHeld(new TankPIDTest(0.6));
    xOpButton.whileHeld(new TankPIDTest(1.0));
  }

  public double getY_Left() {
    return driver_controller.getRawAxis(1);
  }

  public double getY_Right() {
    return driver_controller.getRawAxis(5);
  }

  public double getX_Right() {
    return driver_controller.getRawAxis(4);
  }

  public static OI getInstance() {
    if (instance == null) {
      instance = new OI();
    }
    return instance;
  }



  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  

}
