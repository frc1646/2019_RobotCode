/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargomech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoMechanismSubsystem;
import frc.robot.subsystems.CargoMechanismSubsystem.ArmState;

public class UpdateArm extends Command {

  private CargoMechanismSubsystem cargoMech;  

  public UpdateArm() {
    requires(cargoMech = CargoMechanismSubsystem.getInstance());
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
   switch(cargoMech.getTargetArmState()) {
     case UP:
      if(cargoMech.isUpSwitchPressed()){
        cargoMech.setArmPivotPower(0.0);
      } else {
        cargoMech.setArmPivotPower(0.9);
      }
      break;

     case DOWN:
        if(cargoMech.isDownSwitchPressed()) {
          cargoMech.setArmPivotPower(0.0);
        } else {
          cargoMech.setArmPivotPower(-0.9);
        }
      break;

     case MANUAL:

      break;
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
