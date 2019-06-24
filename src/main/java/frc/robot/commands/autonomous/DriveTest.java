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
import frc.robot.subsystems.CargoMechanismSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utils.controller.Xbox;

public class DriveTest extends Command {
  
  private CargoMechanismSubsystem cargo;
  private DriveSubsystem drive;

  public DriveTest() {
    requires(drive = DriveSubsystem.getInstance());
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

    double leftJoy_Y = OI.getInstance().getDriver().getAxis(Xbox.LEFT_VERTICAL);
    double rightJoy_Y = OI.getInstance().getDriver().getAxis(Xbox.RIGHT_VERTICAL);

    SmartDashboard.putNumber("encoder count", cargo.getAvgCount());
    drive.tankDrive(leftJoy_Y, rightJoy_Y);
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
