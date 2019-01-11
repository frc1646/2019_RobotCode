/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public enum DriveMode {
    ARCADE, TANK
  }

  public static volatile DriveMode driveMode = DriveMode.ARCADE;

  /* OI */
  public static final int DRIVER_CONTROLLER_PORT = 0;

  /* DriveSubsystem */
  public static final int FRONT_LEFT = 2;
  public static final int BACK_LEFT = 1;
  public static final int FRONT_RIGHT = 3;
  public static final int BACK_RIGHT = 4;   

  public static final boolean INV_1 = false;
  public static final boolean INV_2 = false;
  public static final boolean INV_3 = true;
  public static final boolean INV_4 = true;


  /* PneumaticSubsystem */
  public static final int DS_PORT_A = 0;
  public static final int DS_PORT_B = 1;


  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
