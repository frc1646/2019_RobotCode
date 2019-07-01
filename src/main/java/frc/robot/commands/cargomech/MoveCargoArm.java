/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.cargomech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CargoMechanismSubsystem;
import frc.robot.utils.controller.Xbox;

public class MoveCargoArm extends Command {

  private CargoMechanismSubsystem cargoMechSub;

  public MoveCargoArm() {
    requires(cargoMechSub = CargoMechanismSubsystem.getInstance());
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
    double armPivotPower = OI.getInstance().getOperator().getAxis(Xbox.RIGHT_VERTICAL);

    if (cargoMechSub.isDownSwitchPressed() == true && armPivotPower < 0) {
      cargoMechSub.setArmPivotPower(0.0);
    } else {
      cargoMechSub.setArmPivotPower(armPivotPower);
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