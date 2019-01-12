/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.DriveMode;
import frc.robot.subsystems.DriveSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveWithJoy extends Command {
  private DriveSubsystem drive;
  public DriveWithJoy() {
    requires(drive = DriveSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
 
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double leftPow = OI.getInstance().getY_Left();
    double rightPow = OI.getInstance().getX_Right();

    SmartDashboard.putNumber("leftPow", leftPow);
    SmartDashboard.putNumber("rightPow", rightPow);
    
    if (leftPow < 0.05 && leftPow > -0.05) {
      leftPow = 0;
    } 
    if (rightPow < 0.05 && rightPow > -0.05) {
      rightPow = 0;
    }

    if (RobotMap.driveMode == DriveMode.ARCADE) {
      drive.arcadeDrive(leftPow, rightPow);  
    } else if (RobotMap.driveMode == DriveMode.TANK) {
      drive.tankDrive(leftPow, rightPow);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
