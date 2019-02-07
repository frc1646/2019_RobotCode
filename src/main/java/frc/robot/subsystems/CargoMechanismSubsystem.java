/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoMechanismSubsystem extends Subsystem {

  public Talon leftArmMotor;
  public Talon rightArmMotor;
  public Talon intakeMotor;
  public AnalogInput frSwitch;
  public AnalogInput flSwitch;

  public static CargoMechanismSubsystem instance;

  private CargoMechanismSubsystem() {
    leftArmMotor = new Talon(RobotMap.LEFT_CARGO_ARM_MOTOR_ID);
    rightArmMotor = new Talon(RobotMap.RIGHT_CARGO_ARM_MOTOR_ID);
    intakeMotor = new Talon(RobotMap.INTAKE_MOTOR_ID);
    frSwitch = new AnalogInput(RobotMap.ANALOG_INPUT_FR_ID);
    flSwitch = new AnalogInput(RobotMap.ANALOG_INPUT_FL_ID);

   }

  public static CargoMechanismSubsystem getInstance() {
    if (instance == null) {
      instance = new CargoMechanismSubsystem();
    }
    return instance;
  }

  public void setIntakeRollerPower(double power) {
    intakeMotor.set(power);
  }

  public void setArmPivotPower(double power) {
    rightArmMotor.set(power);
    leftArmMotor.set(power);
  }

  public boolean isSwitchPushed() {
    SmartDashboard.getNumber("FRlimitSwitch", frSwitch.getValue()); 
    SmartDashboard.getNumber("FLlimitSwitch", flSwitch.getValue());
  
    if (frSwitch.getValue() <= 10|| flSwitch.getValue() <= 10) {
      return true;
    } else {
      return false;
    }
  } 


    
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


}


