/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoy;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private ADXRS450_Gyro gyro;
	private DriveSide leftSide, rightSide; 
   private static DriveSubsystem instance;
    
	 
 	private DriveSubsystem() { 
 		gyro = new ADXRS450_Gyro(); 
 		leftSide = new DriveSide(RobotMap.FRONT_LEFT, RobotMap.BACK_LEFT, RobotMap.INV_1, RobotMap.INV_2); 
 		rightSide = new DriveSide(RobotMap.FRONT_RIGHT, RobotMap.BACK_RIGHT, RobotMap.INV_3, RobotMap.INV_4); 
 	} 
 	 
 	public void setSidePower(double leftPower, double rightPower) { 
 		leftSide.setPower(leftPower); 
 		rightSide.setPower(rightPower); 
 	} 
 
 
 	public double getAngle() { 
 		return gyro.getAngle(); 
 	} 
 	 
 	public void resetGyro() { 
 		gyro.reset(); 
   } 
   
  public double getDistance() {
    return 0;
  }
 	 
 	public void calibrateGyro() { 
 		gyro.calibrate(); 
 	}
  
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithJoy()); 
  } 
  
public void tankDrive(double leftPow, double rightPow) {
  leftPow = OI.getInstance().getY_Left();
  rightPow = OI.getInstance().getY_Right();

  if (leftPow < 0.05 && leftPow > -0.05) {
    leftPow = 0;
  } 
  if (rightPow < 0.05 && rightPow > -0.05) {
    rightPow = 0;
  }

  setSidePower(leftPow, rightPow);
}  

public void arcadeDrive(double drive, double turn) {
  setSidePower(drive - turn, drive + turn);
}

public static DriveSubsystem getInstance() { 
  if (instance == null) { 
    instance = new DriveSubsystem(); 
  } 
  return instance; 
} 

private class DriveSide { 
    private TalonSRX master, slave;
   //private Encoder encoder; 

    public DriveSide(int port1, int port2, boolean inv1, boolean inv2) { 
      master = new TalonSRX(port1); 
      slave = new TalonSRX(port2); 
     		 
      master.setInverted(inv1); 
      slave.setInverted(inv2); 
    } 
     	 
    public void setPower(double power) { 
      master.set(ControlMode.PercentOutput, power);
      slave.set(ControlMode.PercentOutput, power);
    } 
  }
}
