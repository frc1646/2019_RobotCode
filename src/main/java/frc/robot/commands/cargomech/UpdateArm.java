/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargomech;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
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
// code functions as of 6/10/2019, allows operator to switch between arm states "up", "down", and "manual"
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putString("cargoState",cargoMech.getTargetArmState().toString());
    SmartDashboard.putBoolean("upLimitSwitch", cargoMech.isUpSwitchPressed());
    SmartDashboard.putBoolean("downLimitSwitch", cargoMech.isDownSwitchPressed());
   switch(cargoMech.getTargetArmState()) {
     case UP:
      if (cargoMech.isUpSwitchPressed()){
        cargoMech.setArmPivotPower(0.0);
      } else {
        cargoMech.setArmPivotPower(0.9);
      }
      break;

     case DOWN:
        if (cargoMech.isDownSwitchPressed()) {
          cargoMech.setArmPivotPower(0.0);
        } else {
          cargoMech.setArmPivotPower(-0.9);
        }
      break;

     case MANUAL:
        double armPower = OI.getInstance().getCargoManualArmPower();
        if (armPower > 0) {
          if (cargoMech.isUpSwitchPressed()) {
              cargoMech.setArmPivotPower(0.0);
          } else {
            cargoMech.setArmPivotPower(armPower);
          } 
        }
        else { 
         
          if (cargoMech.isDownSwitchPressed()) {
              cargoMech.setArmPivotPower(0.0);
          } else {
            cargoMech.setArmPivotPower(armPower);
          } 
          
        }

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
