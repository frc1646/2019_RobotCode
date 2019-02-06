/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.sql.Time;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.subsystems.StatusSubsystem;

public class SetStatusLights extends Command {

  private StatusSubsystem statusLights;
  private double currentColorLight; 
  private double startTime, currentTime;
  

  public SetStatusLights() {

    requires(statusLights = StatusSubsystem.getInstance());
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    startTime = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    currentTime = Timer.getFPGATimestamp() - startTime;
    SmartDashboard.putNumber("currentTime", currentTime);
    SmartDashboard.putNumber("lightSetting", currentColorLight);

    
    statusLights.setStatusLights(currentColorLight);

   if (currentTime < 15) {
    statusLights.setStatusLights (-0.73);
   }

   if (currentTime > 15 && currentTime < 105) {
    statusLights.setStatusLights (0.21); }

   if (currentTime > 105 && currentTime < 135) {
     statusLights.setStatusLights (-0.63);
   }

   if (currentTime > 135 && currentTime < 150) {

     statusLights.setStatusLights (0.07);
   }

   if (currentTime > 150) {
     statusLights.setStatusLights(-0.89);
   }





    //statusLights.setStatusLights(-0.89);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    statusLights.setStatusLights(-0.59);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
