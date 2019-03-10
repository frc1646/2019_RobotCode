/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.subsystems.CargoMechanismSubsystem;
import frc.robot.utils.CheesyPID;
import frc.robot.utils.controller.Xbox;

public class ChangeCargoAngle extends Command {

  private CheesyPID cargoArmPID;
  private CargoMechanismSubsystem cargo;
  private double lastTime;
  private double setPoint;

  public ChangeCargoAngle() {
    requires(cargo = CargoMechanismSubsystem.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //System.out.println("changeCargoAngleCommand");
    SmartDashboard.putBoolean("upperSwitch", cargo.isUpSwitchPressed());
    if (OI.getInstance().getOperator().getButton(Xbox.X).get() && !cargo.isBallIn()) {
      cargo.setIntakeRollerPower(-0.9); //intaking
    } else if (OI.getInstance().getOperator().getButton(Xbox.B).get()) {
      cargo.setIntakeRollerPower(0.9); //outaking
    }else{
      cargo.setIntakeRollerPower(0.0);
    }  

    if (cargo.isDownSwitchPressed()) {
      cargo.resetHallEffectSensors();
    }

    double leftYAxis = OI.getInstance().getOperator().getAxis(Xbox.LEFT_VERTICAL);
    System.out.println(leftYAxis);

    //cargo.setArmPivotPower(leftYAxis);

     if(leftYAxis < -0.25 && !cargo.isDownSwitchPressed()) {
      cargo.setArmPivotPower(leftYAxis * -1.0);
    } else if(leftYAxis > 0.25 && !cargo.isUpSwitchPressed()) {
        cargo.setArmPivotPower(leftYAxis * -1.0);
    } else if (OI.getInstance().getOperator().getButton(Xbox.X).get() && cargo.isBallIn() && !cargo.isUpSwitchPressed()) {
      cargo.setArmPivotPower(0.0);
    } else {
      cargo.setArmPivotPower(0.0);
    } 
    
    

    /*

    if (OI.getInstance().getOperator().getButton(Xbox.X).get() && cargo.isBallIn() && cargo.getAvgCount() < Constants.CARGO_ARM_MAX_ENCODER_COUNT)  { 
      cargo.setArmPivotPower(-0.9);
    } else if (!cargo.isDownSwitchPressed()) {
      cargo.setArmPivotPower(leftYAxis);
    } else {
      cargo.setArmPivotPower(0);
    }
    */


    SmartDashboard.putNumber("UltraSonic Distance", cargo.getUltrasonicDistance());
    SmartDashboard.putNumber("Encoder Raw Count", cargo.getAvgCount());

    //lastTime = Timer.getFPGATimestamp();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;//cargo.isUpSwitchPressed();
  }

  // Called once after isFinished returns true
  

  @Override
  protected void end() {
    cargo.setArmPivotPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
