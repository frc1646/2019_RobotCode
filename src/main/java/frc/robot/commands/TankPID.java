
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

public class TankPID extends Command {

  private DriveSubsystem drive; 
  private CheesyPID left_pid; // translational pid 
  private CheesyPID right_pid;
  private double lastPos;
  private double lastLeft;
  private double lastRight;
  private double lastTime;
  
  public TankPID() {
    requires(drive = DriveSubsystem.getInstance());
    left_pid = new CheesyPID(Constants.TANK_LEFT_VEL_P,
                          Constants.TANK_LEFT_VEL_I,
                          Constants.TANK_LEFT_VEL_D,
                          Constants.TANK_LEFT_KF);

    right_pid = new CheesyPID(Constants.TANK_RIGHT_VEL_P,
                          Constants.TANK_RIGHT_VEL_I,
                          Constants.TANK_RIGHT_VEL_D,
                          Constants.TANK_RIGHT_KF);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    left_pid.reset();
    right_pid.reset();

    lastPos = drive.getDistance();
    //lastAng = drive.getAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double dt = Timer.getFPGATimestamp() - lastTime;
    double d_vel = (drive.getDistance() - lastPos) / dt;
    double leftVel = (drive.getDistanceLeftSide() - lastLeft) / dt;
    double rightVel = (drive.getDistanceRightSide() - lastRight) / dt; 
    //double a_vel = (drive.getAngle() - lastAng) / dt;

    SmartDashboard.putNumber("d_vel", d_vel);
    SmartDashboard.putNumber("leftVel", leftVel);
    SmartDashboard.putNumber("rightVel", rightVel);
    SmartDashboard.putNumber("rotations", drive.getRotations());

    double leftJoy = OI.getInstance().getY_Left();
    double rightJoy = OI.getInstance().getY_Right();    

    if (leftJoy < 0.05 && leftJoy > -0.05) {
      leftJoy = 0;  
    } 
    if (rightJoy < 0.05 && rightJoy > -0.05) {
      rightJoy = 0;
    }

    left_pid.setSetpoint(leftJoy * Constants.DRIVE_MAX_VEL);
    right_pid.setSetpoint(rightJoy * Constants.DRIVE_MAX_VEL);


    drive.tankDrive(left_pid.calculate(leftVel, dt), right_pid.calculate(rightVel, dt));
    //drive.tankDrive(left_pid.calculate(leftVel, dt), 0);

    lastTime = Timer.getFPGATimestamp();
    lastPos = drive.getDistance();
    lastLeft = drive.getDistanceLeftSide();
    lastRight = drive.getDistanceRightSide();
    //lastAng = drive.getAngle();
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
