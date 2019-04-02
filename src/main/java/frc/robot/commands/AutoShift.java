/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShiftingSubsystem;

public class AutoShift extends Command {
  
  private ShiftingSubsystem shifter;
  private DriveSubsystem drive;
  public static final double maxDiff = Constants.DRIVE_MAX_VEL - Constants.LOW_AUTOSHIFT;
  private double lastTime;
  private OI oi;


  public AutoShift() {
    requires(shifter = ShiftingSubsystem.getInstance());
    drive = DriveSubsystem.getInstance();
    oi = OI.getInstance();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    lastTime = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Timer.getFPGATimestamp() - lastTime >= 0.05) {
      double desired = oi.getY_Left() * Constants.DRIVE_MAX_VEL;
      double diff = Math.abs(desired - drive.getSpeed()); 
      
      if(desired >= Constants.HIGH_AUTOSHIFT && diff <= maxDiff) {
        shifter.shiftUp();
      } else if (desired <= Constants.LOW_AUTOSHIFT || diff >= maxDiff) {
        shifter.shiftDown();
      } else {
        shifter.shiftOff();
      }
      lastTime = Timer.getFPGATimestamp();
    }
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

