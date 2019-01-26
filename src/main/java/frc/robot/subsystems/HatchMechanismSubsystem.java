/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchMechanismSubsystem extends Subsystem {

  private Solenoid clampingSolenoid;
  private Solenoid releasingSolenoid;
  private static HatchMechanismSubsystem instance;
  //This is where we allocate the space for the Solenoids used later

  private HatchMechanismSubsystem() {
    clampingSolenoid = new Solenoid(RobotMap.RELEASING_HATCH_SOLENOID_ID);
    releasingSolenoid = new Solenoid(RobotMap.CLAMPING_HATCH_SOLENOID_ID);
    
  }

  public void clampHatch() {
    clampingSolenoid.set(RobotMap.CLAMP_VALUE);
  }

  public void unclampHatch() {
    clampingSolenoid.set(!RobotMap.CLAMP_VALUE);

  }

  public void extendReleasingPistons() {
    clampingSolenoid.set(RobotMap.RELEASING_VALUE);
 }

  public void retractReleasingPistons() {
    clampingSolenoid.set(!RobotMap.RELEASING_VALUE);

  }

  public static HatchMechanismSubsystem getInstance() {
    if (instance == null) {
      instance = new HatchMechanismSubsystem();

    }

    return instance;

  } 

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
