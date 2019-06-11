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

public class RetractHatch extends Command {
  private HatchMechanismSubsystem hatch;
  private double startTime;
  private double endTime;

  public RetractHatch() {
    requires(hatch = HatchMechanismSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startTime = Timer.getFPGATimestamp();
   // hatch.clampHatch();
    hatch.retractHorizSolenoid();
    endTime = startTime + 0.1;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Timer.getFPGATimestamp() >= endTime;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Retract Finished");
    hatch.closeHorizSolenoid();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
