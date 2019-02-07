/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

//import com.sun.org.apache.xalan.internal.templates.Constants;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ChangeCargoAngle;
import frc.robot.utils.CheesyPID;

/**
 * Add your docs here.
 */
public class CargoMechanismSubsystem extends Subsystem {

  private VictorSP leftArmMotor;
  private VictorSP rightArmMotor;
  private VictorSP intakeMotor;
  private Ultrasonic ultra;
  private DigitalInput upLimitSwitch, downLimitSwitch;
  private Counter leftEffectSensor, rightEffectSensor;
  private Encoder cargoEncoder;

  public static CargoMechanismSubsystem instance;

  private CargoMechanismSubsystem() {
    leftArmMotor = new VictorSP(RobotMap.LEFT_CARGO_ARM_MOTOR_ID);
    rightArmMotor = new VictorSP(RobotMap.RIGHT_CARGO_ARM_MOTOR_ID);
    intakeMotor = new VictorSP(RobotMap.INTAKE_MOTOR_ID);

    ultra = new Ultrasonic(RobotMap.ULTRA_SENSOR_PING_ID, RobotMap.ULTRA_SENSOR_ECHO_ID);
    
    upLimitSwitch = new DigitalInput(RobotMap.UP_LIMIT_SWITCH_ID);
    downLimitSwitch = new DigitalInput(RobotMap.DOWN_LIMIT_SWITCH_ID);
    
    leftEffectSensor = new Counter(RobotMap.LEFT_EFFECT_SENSOR_ID);
    rightEffectSensor = new Counter(RobotMap.RIGHT_EFFECT_SENSOR_ID);

    cargoEncoder = new Encoder(RobotMap.LEFT_EFFECT_SENSOR_ID, RobotMap.RIGHT_EFFECT_SENSOR_ID);
  }

  public static CargoMechanismSubsystem getInstance() {
    if (instance == null) {
      instance = new CargoMechanismSubsystem();
    }
    return instance;
  }

  public double getAvgCount() {
    //return (leftEffectSensor.get() + rightEffectSensor.get()) / 2;
    return cargoEncoder.getRaw();
  }

  public boolean isBall_IN() {
    return ultra.getRangeMM() < 0;
  }

  public boolean isUpSwitchPressed() {
    return upLimitSwitch.get();
  }

  public boolean isDownSwitchPressed() {
    return downLimitSwitch.get();
  }



  public void setIntakeRollerPower(double power) {
    intakeMotor.set(power);
  }

  public void setArmPivotPower(double power) {
    rightArmMotor.set(power);
    leftArmMotor.set(power);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }


}


