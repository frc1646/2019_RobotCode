/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchMechanismSubsystem;

public class ExtendHatchMech extends Command {

  private HatchMechanismSubsystem hatchSub;
  private double endTime;

  public ExtendHatchMech() {

    requires(hatchSub = HatchMechanismSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    endTime = Timer.getFPGATimestamp() + 0.1;
    //hatchSub.unclampHatch();
    hatchSub.extendHorizPistSolenoid();
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
    //Might change this to true
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatchSub.closeHorizSolenoid();
    //hatchSub.retractReleasingPistons();
  }

  // Called when another command which requires one or more of the same
  // ssubsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
