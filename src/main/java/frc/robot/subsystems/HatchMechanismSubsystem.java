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

  private DoubleSolenoid VertPistSolenoid, horizPistSolenoid;
  private static HatchMechanismSubsystem instance;
  //This is where we allocate the space for the Solenoids used later

  private HatchMechanismSubsystem() {
    VertPistSolenoid = new DoubleSolenoid(RobotMap.RELEASING_HATCH_SOLENOID_ID, RobotMap.CLAMPING_HATCH_SOLENOID_ID);
    horizPistSolenoid = new DoubleSolenoid(RobotMap.HATCH_EXTEND_A, RobotMap.HATCH_EXTEND_B);
  }

  public void retractVertPistSolenoid() {
    VertPistSolenoid.set(Value.kReverse);
  }

  public void closeVertHatchSolenoid() {
    //closes both solenoid valves for vertical solnoid
    VertPistSolenoid.set(Value.kOff);
  }
  
  public void extendVertPistSolenoid() {
    VertPistSolenoid.set(Value.kForward);
  }

  public void extendHorizPistSolenoid() {
    horizPistSolenoid.set(Value.kReverse);
 }

  public void closeHorizSolenoid() {
    //closes both solenoid valves for horizontal solnoid
    horizPistSolenoid.set(Value.kOff);
  }

  public void retractHorizSolenoid() {
    horizPistSolenoid.set(Value.kForward);

  }

  
  public static HatchMechanismSubsystem getInstance() {
    if (instance == null) {
      instance = new HatchMechanismSubsystem();
    }
    return instance;
  } 


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
