/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchMechanismSubsystem extends Subsystem {

  private DoubleSolenoid clampingSolenoid;
  private static HatchMechanismSubsystem instance;
  //This is where we allocate the space for the Solenoids used later

  private HatchMechanismSubsystem() {
    clampingSolenoid = new DoubleSolenoid(RobotMap.RELEASING_HATCH_SOLENOID_ID, RobotMap.CLAMPING_HATCH_SOLENOID_ID);
    
  }

  public void clampHatch() {
    clampingSolenoid.set(Value.kForward);
  }

  public void unclampHatch() {
    clampingSolenoid.set(Value.kReverse);

  }

  public void extendReleasingPistons() {
    //clampingSolenoid.set(RobotMap.RELEASING_VALUE);
 }

  public void retractReleasingPistons() {
    //clampingSolenoid.set(!RobotMap.RELEASING_VALUE);

  }

  public boolean hasHatch() {
    return false;
  }
  // change this later!

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
