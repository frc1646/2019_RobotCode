/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.UpdateShift;

/**
 * Add your docs here.
 */
public class ShiftingSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private static ShiftingSubsystem instance;
private DoubleSolenoid shifter;
private boolean highGear;

private ShiftingSubsystem() {
  shifter = new DoubleSolenoid(RobotMap.SHIFTER_PORT_A, RobotMap.SHIFTER_PORT_B);
}

public void shiftUp() {
  shifter.set(Value.kForward);
  highGear = true;
}

public void shiftDown() {
  shifter.set(Value.kReverse);
  highGear = false;
}

public boolean isHighGear() {
  return highGear;
}

public void shiftOff() {
  shifter.set(Value.kOff);
}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new UpdateShift());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  

public static ShiftingSubsystem getInstance() { 
  if (instance == null) { 
    instance = new ShiftingSubsystem(); 
  } 
  return instance; 
} 

}
