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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
  private DoubleSolenoid shifter;
  private DoubleSolenoid climber;

  private boolean highGear;
  

 	private DriveSubsystem() { 
     gyro = new AHRS(SPI.Port.kMXP);

    leftSide = new DriveSide( RobotMap.LEFT_TALON, RobotMap.LEFT_VICTOR, 
                              RobotMap.L_TALON_INV, RobotMap.L_VICT_INV, 
                              RobotMap.LEFT_ENCODER_A, RobotMap.LEFT_ENCODER_B,
                              RobotMap.ENCODER_INV_1); 
    rightSide = new DriveSide(RobotMap.RIGHT_TALON, RobotMap.RIGHT_VICTOR,
                              RobotMap.R_TALON_INV, RobotMap.R_VICT_INV,
                              RobotMap.RIGHT_ENCODER_A, RobotMap.RIGHT_ENCODER_B,
                              RobotMap.ENCODER_INV_2); 
    shifter = new DoubleSolenoid(RobotMap.SHIFTER_PORT_A, RobotMap.SHIFTER_PORT_B);

    climber = new DoubleSolenoid(RobotMap.CLIMBER_SOLENOID_A, RobotMap.CLIMBER_SOLENOID_B);
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
    //setDefaultCommand(new DrivePID()); 
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


public void shiftUp() {
  shifter.set(Value.kForward);
  highGear = true;
}

public void shiftDown() {
  shifter.set(Value.kReverse);
  highGear = false;
}

public boolean isHighGear() {
  return highGear;
}

public void shiftOff() {
  shifter.set(Value.kOff);
}

public void extendClimb() {
  climber.set(Value.kForward);
}

public void retractClimb() {
  climber.set(Value.kReverse);
}

public void turnClimbOff() {
  climber.set(Value.kOff);
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
      //FIXME pls remove cap thx
      master.set(ControlMode.PercentOutput, power * 0.8);
      slave.set(ControlMode.PercentOutput, power * 0.8);
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
