
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.CheesyPID;
import frc.robot.utils.controller.Xbox;


public class DrivePID extends Command {

  private DriveSubsystem drive; 
  private CheesyPID d_pid; // translational pid 
  private CheesyPID a_pid;
  private double lastTime;
  private double lastPos;
  private double lastAng;
  private double a_vel;

  public DrivePID() {
    requires(drive = DriveSubsystem.getInstance());
    d_pid = new CheesyPID(Constants.DRIVE_VEL_P,
                          Constants.DRIVE_VEL_I,
                          Constants.DRIVE_VEL_D,
                          Constants.DRIVE_VEL_F);

    a_pid = new CheesyPID(Constants.ANGLE_VEL_P,
                          Constants.ANGLE_VEL_I,
                          Constants.ANGLE_VEL_D,
                          Constants.ANGLE_VEL_F);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    d_pid.reset();
    a_pid.reset();

    lastPos = drive.getDistance();
    lastAng = drive.getGyro().getAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    a_vel = (drive.getGyro().getRawGyroZ() * 2 * (Math.PI / 360)) * 0.2 + 0.8 * a_vel;
    double dt = Timer.getFPGATimestamp() - lastTime;
    double d_vel = 0;//(drive.getDistance() - lastPos) / dt;

    SmartDashboard.putNumber("d_vel", d_vel);
    SmartDashboard.putNumber("a_vel", a_vel);
    SmartDashboard.putNumber("gyro angle", drive.getGyro().getAngle());
  //                  SmartDashboard.putNumber("left vel", drive.getLeftSpeed());
   // SmartDashboard.putNumber("right vel", drive.getRightSpeed());

    double driveJoy = OI.getInstance().getDriver().getAxis(Xbox.LEFT_VERTICAL);
    double angleJoy = -OI.getInstance().getDriver().getAxis(Xbox.RIGHT_HORIZONTAL); 

    if (driveJoy < 0.05 && driveJoy > -0.05) {
      driveJoy = 0;  
    } 
    if (angleJoy < 0.05 && angleJoy > -0.05) {
      angleJoy = 0;
    }

    d_pid.setSetpoint(driveJoy * Constants.DRIVE_MAX_VEL);
    a_pid.setSetpoint(angleJoy * Constants.ANGLE_MAX_VEL);
  
    //d_pid.setSetpoint(0);
    //a_pid.setSetpoint(0);
    //SmartDashboard.putNumber("driveSetpoint", driveJoy * Constants.DRIVE_MAX_VEL);
  
    SmartDashboard.putNumber("a_error", a_pid.getError());
    SmartDashboard.putNumber("f_output", d_pid.getSetpoint());
  
    drive.arcadeDrive(d_pid.calculate(-d_vel, dt), a_pid.calculate(a_vel, dt));

    lastTime = Timer.getFPGATimestamp();
    lastPos = drive.getDistance();
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
