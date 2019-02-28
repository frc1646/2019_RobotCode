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
  public static final int OPERATOR_CONTROLLER_PORT = 1;

  /* DriveSide */
  public static final boolean ENCODER_INV_1 = false;
  public static final boolean ENCODER_INV_2 = false;

  /* DriveSubsystem */
  public static final int FRONT_LEFT = 1;
  public static final int BACK_LEFT = 2;
  public static final int FRONT_RIGHT = 3;
  public static final int BACK_RIGHT = 4;   

  public static final boolean INV_1 = false;
  public static final boolean INV_2 = false;
  public static final boolean INV_3 = true;
  public static final boolean INV_4 = true;

  public static final int LEFT_ENCODER_A = 1;
  public static final int LEFT_ENCODER_B = 0;
  public static final int RIGHT_ENCODER_A = 8;
  public static final int RIGHT_ENCODER_B = 9;

  /* PneumaticSubsystem */
  public static final int SHIFTER_PORT_A = 0;
  public static final int SHIFTER_PORT_B = 1;

  //CargoMechanismSubsystem
  public static final int LEFT_CARGO_ARM_MOTOR_ID =  0;
  public static final int RIGHT_CARGO_ARM_MOTOR_ID = 0;
  public static final int INTAKE_MOTOR_ID = 0;
  public static final int ANALOG_INPUT_FR_ID = 0;
  public static final int ANALOG_INPUT_FL_ID = 0;
  public static final int TEST_HALL_EFFECT = 2;
  // analog inputs must be changed later.

  //HatchMechanismSubsystem
  public static final int RELEASING_HATCH_SOLENOID_ID = -1;
  public static final int CLAMPING_HATCH_SOLENOID_ID = -1;
  public static final boolean CLAMP_VALUE = true;
  public static final boolean RELEASING_VALUE = true;

  //StatusLightsSubsystem
  public static final int BLINKIN_ID = 0;

  

  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
