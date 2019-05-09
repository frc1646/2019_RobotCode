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

public class ExtendedStartGrabHatch extends Command {
  HatchMechanismSubsystem hatch;
  public ExtendedStartGrabHatch() {
    requires(hatch = HatchMechanismSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    hatch.extendVertPistSolenoid();
    hatch.extendVertPistSolenoid();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    hatch.retractVertPistSolenoid();
    double startTime = Timer.getFPGATimestamp();
    while(startTime + 0.25 > Timer.getFPGATimestamp()){

    }
    hatch.retractVertPistSolenoid();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
