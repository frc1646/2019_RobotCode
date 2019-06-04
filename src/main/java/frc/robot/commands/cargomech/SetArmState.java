/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargomech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoMechanismSubsystem;

public class SetArmState extends Command {
  
  private CargoMechanismSubsystem cargoMech;
  private CargoMechanismSubsystem.ArmState desiredState;

  public SetArmState() {
    this(CargoMechanismSubsystem.ArmState.MANUAL);
  }
  
  public SetArmState(CargoMechanismSubsystem.ArmState desiredState) {
    requires(cargoMech = CargoMechanismSubsystem.getInstance());
    this.desiredState = desiredState;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    cargoMech.setArmState(desiredState);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
