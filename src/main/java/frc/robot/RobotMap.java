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

  public enum ArmDirection {
    MOVE_UP, MOVE_DOWN
  }

  public static volatile DriveMode driveMode = DriveMode.ARCADE;

  /* OI */
  public static final int DRIVER_CONTROLLER_PORT = 0;
  public static final int OPERATOR_CONTROLLER_PORT = 1;

  /* DriveSide */
  public static final boolean ENCODER_INV_1 = false;
  public static final boolean ENCODER_INV_2 = false;

  /* DriveSubsystem */
  public static final int LEFT_TALON = 1;
  public static final int LEFT_VICTOR = 2;
  public static final int RIGHT_TALON = 3;
  public static final int RIGHT_VICTOR = 4;   

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
  public static final int LEFT_CARGO_ARM_MOTOR_ID =  7;
  public static final boolean LEFT_CARGO_MOTOR_INV = true;
  public static final int RIGHT_CARGO_ARM_MOTOR_ID = 8;
  public static final boolean RIGHT_CARGO_MOTOR_INV = false;
  public static final int INTAKE_MOTOR_ID = 9;
  public static final boolean INTAKE_MOTOR_INV = false;

  public static final int ULTRA_SENSOR_PING_ID = 6; // sends ping
  public static final int ULTRA_SENSOR_ECHO_ID = 5; // receives the echo

  public static final int UP_LIMIT_SWITCH_ID = 7;
  public static final int DOWN_LIMIT_SWITCH_ID = 4;

  public static final int LEFT_EFFECT_SENSOR_ID = 2;
  public static final int RIGHT_EFFECT_SENSOR_ID = 3;

  //HatchMechanismSubsystem
  public static final int RELEASING_HATCH_SOLENOID_ID = 2;
  public static final int CLAMPING_HATCH_SOLENOID_ID = 3;
  //public static final Value CLAMP_VALUE = true;
  //public static final Value RELEASING_VALUE = true;
  public static final int HATCH_EXTEND_A = 4;
  public static final int HATCH_EXTEND_B = 5;

  

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
