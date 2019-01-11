/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class PneumaticSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private DoubleSolenoid ds;
	private static PneumaticSubsystem instance;
	
	private PneumaticSubsystem() {
		ds = new DoubleSolenoid(RobotMap.DS_PORT_A, RobotMap.DS_PORT_B);
	}
	
	public void extend() {
		ds.set(Value.kForward);
	}
	
	public void retract() {
    ds.set(Value.kReverse);
	}
	
	public void off() {
		ds.set(Value.kOff);
	}
	

	public static PneumaticSubsystem getInstance() {
		if (instance == null) {
			instance = new PneumaticSubsystem();
		}
		return instance;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
