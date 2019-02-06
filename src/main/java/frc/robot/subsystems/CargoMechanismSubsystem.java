/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.sun.org.apache.xalan.internal.templates.Constants;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.utils.CheesyPID;

/**
 * Add your docs here.
 */
public class CargoMechanismSubsystem extends Subsystem {

  public Talon leftArmMotor;
  public Talon rightArmMotor;
  public Talon intakeMotor;
 

  public static CargoMechanismSubsystem instance;



  private CargoMechanismSubsystem() {
    leftArmMotor = new Talon(RobotMap.LEFT_CARGO_ARM_MOTOR_ID);
    rightArmMotor = new Talon(RobotMap.RIGHT_CARGO_ARM_MOTOR_ID);
    intakeMotor = new Talon(RobotMap.INTAKE_MOTOR_ID);
    

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
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


}


