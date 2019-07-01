/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.controller.Xbox;

public class DriveToBall extends Command {
  
  DriveSubsystem drive;
  CameraSubsystem camera;





  public DriveToBall() {
    requires(drive = DriveSubsystem.getInstance());
    requires(camera = CameraSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override

  protected void execute() {
    double x = camera.getX();
    System.out.println(x);
    if (camera.isBallFound()){
      
      drive.arcadeDrive(1.0 , x/(camera.getWidth()));
    
    } else {
      double leftPow = OI.getInstance().getDriver().getAxis(Xbox.LEFT_VERTICAL);
      double rightPow = OI.getInstance().getDriver().getAxis(Xbox.RIGHT_HORIZONTAL);

      SmartDashboard.putNumber("leftPow", leftPow);
      SmartDashboard.putNumber("rightPow", rightPow);
    
      if (leftPow < 0.05 && leftPow > -0.05) {
       leftPow = 0;
      } 
      if (rightPow < 0.05 && rightPow > -0.05) {
       rightPow = 0;
      }
      drive.arcadeDrive(leftPow, rightPow);
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