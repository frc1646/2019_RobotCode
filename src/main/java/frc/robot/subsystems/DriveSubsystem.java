/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.DrivePID;
import frc.robot.commands.DriveWithJoy;
import frc.robot.commands.TankPID;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private AHRS gyro;
	private DriveSide leftSide, rightSide; 
 	private static DriveSubsystem instance; 


  private Counter testCounter;

  private boolean highGear;
  

 	private DriveSubsystem() { 
     testCounter = new Counter(RobotMap.TEST_HALL_EFFECT);
     gyro = new AHRS(SPI.Port.kMXP);

    leftSide = new DriveSide( RobotMap.FRONT_LEFT, RobotMap.BACK_LEFT, 
                              RobotMap.INV_1, RobotMap.INV_2, 
                              RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B,
                              RobotMap.ENCODER_INV_1); 
    rightSide = new DriveSide(RobotMap.FRONT_RIGHT, RobotMap.BACK_RIGHT,
                              RobotMap.INV_3, RobotMap.INV_4,
                              RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B,
                              RobotMap.ENCODER_INV_2); 
  } 
  
  public double getRotations() {
    return testCounter.get();
  }

  
 	public void setSidePower(double leftPower, double rightPower) { 
 		leftSide.setPower(leftPower); 
 		rightSide.setPower(rightPower); 
 	} 
   
  public void resetEncoder() {
    leftSide.resetEncoder();
    rightSide.resetEncoder();
  }
 
  public AHRS getGyro() {
    return gyro;
  }

 	 
 	public void resetGyro() { 
 	 gyro.reset(); 
  } 

   
  public double getDistanceLeftSide() {
    return leftSide.getDistance();
  }

  public double getDistanceRightSide() {
    return rightSide.getDistance();
  }

  public double getDistance() {
    return (getDistanceLeftSide() + getDistanceRightSide()) / 2;
  }

  public void initDefaultCommand() {
    setDefaultCommand(new DrivePID()); 
  } 
  
public void tankDrive(double leftPow, double rightPow) {
  setSidePower(leftPow, rightPow);
}  

public void arcadeDrive(double leftPow, double rightPow) {
  setSidePower(leftPow - rightPow, leftPow + rightPow);
}

public static DriveSubsystem getInstance() { 
  if (instance == null) { 
    instance = new DriveSubsystem(); 
  } 
  return instance; 
} 


public boolean isHighGear() {
  return highGear;
}

public double getSpeed() {
  return (leftSide.getSpeed() + rightSide.getSpeed()) / 2;
}

private class DriveSide { 
    private TalonSRX master;  
    private VictorSPX slave;
    private Encoder encoder; 

    public DriveSide(int port1, int port2, 
                     boolean inv1, boolean inv2, 
                     int encoder_A, int encoder_B,
                     boolean encoder_inv) {     
      master = new TalonSRX(port1); 
      slave = new VictorSPX(port2); 
     		 
      master.setInverted(inv1); 
      slave.setInverted(inv2);

      master.setNeutralMode(NeutralMode.Brake);

      encoder = new Encoder(encoder_A, encoder_B);
      encoder.setDistancePerPulse(Constants.FEET_PER_COUNT);
    
    } 
     	 
    public void setPower(double power) { 
      master.set(ControlMode.PercentOutput, power);
      slave.set(ControlMode.PercentOutput, power);
    }

    public double getSpeed() {
      return encoder.getRate();
    }

    public double getEncoderCount() {
      return encoder.getRaw();
    }

    public void resetEncoder() {
      encoder.reset();
    }

    public double getDistance() {
      return encoder.getDistance();
    }
  }
}
