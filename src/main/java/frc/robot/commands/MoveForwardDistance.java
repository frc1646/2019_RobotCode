/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class MoveForwardDistance extends Command {

  private DriveSubsystem drive;
  private double startDist;
  private double endDist;
  private double forward;
  private double turn;

  public MoveForwardDistance(double distance, double forward, double turn) {
    requires(drive = DriveSubsystem.getInstance());
    this.endDist = distance;
    this.forward = forward;
    this.turn = turn;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("running moveForwardDistance");
    startDist = drive.getDistance();
    endDist = Math.abs(startDist + endDist);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drive.arcadeDrive(forward, turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    return Math.abs(drive.getDistance()) >= endDist;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("MoveForward Finished");
    drive.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
