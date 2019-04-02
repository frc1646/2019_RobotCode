/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.AutoShift;

/**
 * Add your docs here.
 */
public class ShiftingSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static ShiftingSubsystem instance;
  private DoubleSolenoid shifter;
  private boolean isHighGear;

  private ShiftingSubsystem() {
    shifter = new DoubleSolenoid(RobotMap.SHIFTER_PORT_A, RobotMap.SHIFTER_PORT_B);
  }

  public void shiftUp() {
    shifter.set(Value.kForward);
    isHighGear = true;
  }

  
  public void shiftDown() {
    shifter.set(Value.kReverse);
    isHighGear = false;
  }
  
  public void shiftOff() {
    shifter.set(Value.kOff);
  }

  public boolean isHighGear() {
    return isHighGear;
  }

  public static ShiftingSubsystem getInstance() {
    if (instance == null) {
      instance = new ShiftingSubsystem();
    }
    return instance;
  }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new AutoShift());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
