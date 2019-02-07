/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.CargoMechanismSubsystem;
import frc.robot.utils.CheesyPID;

public class ChangeCargoAngle extends Command {

  private CheesyPID cargoArmPID;
  private CargoMechanismSubsystem cargo;
  private double lastTime;
  private double setPoint;

  public ChangeCargoAngle() {
    requires(cargo = CargoMechanismSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  
  
  
  
    cargoArmPID = new CheesyPID(Constants.CARGO_ARM_ANGLE_P,
                                  Constants.CARGO_ARM_ANGLE_I,
                                  Constants.CARGO_ARM_ANGLE_D,
                                  Constants.CARGO_ARM_ANGLE_F); //cargoArmPID = positional PID
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    cargoArmPID.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double dt = Timer.getFPGATimestamp() - lastTime;


    cargoArmPID.setSetpoint(setPoint);
    cargo.setArmPivotPower(cargoArmPID.calculate(cargo.getAvgCount(), dt));

    SmartDashboard.putNumber("Encoder Raw Count", cargo.getAvgCount());
    lastTime = Timer.getFPGATimestamp();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return cargo.isUpSwitchPressed();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    cargo.setArmPivotPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
