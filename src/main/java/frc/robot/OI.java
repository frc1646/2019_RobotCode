/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ChangeCargoAngle;
import frc.robot.commands.ClampHatch;
import frc.robot.commands.CycleDriveMode;
import frc.robot.commands.DriveToBall;
import frc.robot.commands.DriveToHatchTarget;
import frc.robot.commands.ResetGyro;
import frc.robot.commands.SetStatusLights;
import frc.robot.commands.ShiftDown;
import frc.robot.commands.ShiftUp;
import frc.robot.commands.TankPIDTest;
import frc.robot.commands.ToggleShift;
import frc.robot.commands.UnclampHatch;
import frc.robot.utils.controller.Logitech;
import frc.robot.utils.controller.Xbox;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  private static OI instance;
  private Xbox driver_controller, operator_controller;
  private JoystickButton yButton, aButton, startButton, xButton, bButton;
  private JoystickButton yOpButton, aOpButton, xOpButton, bOpButton;
  private JoystickButton right_bumper;
  private JoystickButton backButton;

  private OI() {
    driver_controller = new Xbox(RobotMap.DRIVER_CONTROLLER_PORT);
    operator_controller = new Xbox(RobotMap.OPERATOR_CONTROLLER_PORT);

    createDriver();
    createOperator();
  }

  public void createDriver() {
  }

  public void createOperator() {
    operator_controller.getButton(Xbox.LB).whileHeld(new ChangeCargoAngle());
    operator_controller.getButton(Xbox.Y).whenPressed(new ClampHatch());
    operator_controller.getButton(Xbox.A).whenPressed(new UnclampHatch());
    operator_controller.getButton(Xbox.X).whenPressed(new )

  }
  public boolean getIntakeButton() {
    return operator_controller.getButton(Xbox.LT).get();
  }

  public Xbox getDriver() {
    return driver_controller;
  }

  public Xbox getOperator() {
    return operator_controller;
  }
  public static OI getInstance() {
    if (instance == null) {
      instance = new OI();
    }
    return instance;
  }

  

}
