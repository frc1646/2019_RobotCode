/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.DriveMode;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveWithJoy extends Command {
  private DriveSubsystem drive;
  private double lastPos;
  private double lastAng;
  private double lastTime;
  private double lastPow_drive;
  private double lastPow_turn;

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
    double dt = Timer.getFPGATimestamp() - lastTime;
    double d_vel = (drive.getDistance() - lastPos) / dt; 
    double a_vel = (drive.getAngle() - lastAng) / dt;


    SmartDashboard.putNumber("d_vel", d_vel);
    SmartDashboard.putNumber("a_vel", a_vel);
    SmartDashboard.putNumber("current", drive.getCurrent()); 

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

    // if (leftPow == 0 && rightPow == 0) {
    //   drive.shiftDown();
    // } else if (drive.getCurrent() < Constants.LOW_AUTOSHIFT) {
    //   drive.shiftUp();
    // } else if (drive.getCurrent() > Constants.HIGH_AUTOSHIFT) {
    //   drive.shiftDown();
    // }

    lastTime = Timer.getFPGATimestamp();
    lastPos = drive.getDistance();
    lastAng = drive.getAngle();
    lastPow_drive = OI.getInstance().getY_Left();
    lastPow_turn = OI.getInstance().getX_Right();
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
