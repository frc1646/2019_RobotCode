/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class CameraSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  NetworkTable ballVisionContour;
  NetworkTable bayVisionContour;
  
  private static CameraSubsystem instance;
  double cameraWidth = 158;
  double[] defaultArray = {cameraWidth/2};

  public boolean foundBall;
  public boolean foundBay;
  

  public CameraSubsystem() {
    ballVisionContour = NetworkTable.getTable("GRIP/orangeBall");
    foundBall = false;
  
    bayVisionContour = NetworkTable.getTable("GRIP/bayHatch");
    foundBay = false;
  }

  public boolean isBallFound(){
    return foundBall;
  }

  public boolean isBayFound() {
    return foundBay;
  }

  public double getRawX() {
    double[] ballX = ballVisionContour.getNumberArray("centerX", defaultArray);
    if (ballX.length == 0){
      foundBall =  false;
      return defaultArray[0];
    }
    else if (ballX.length != 0){
      foundBall = true;
    }
    return ballX[0];
  }

  public double getBayRawX() {
    double[] bayX = bayVisionContour.getNumberArray("centerX", defaultArray);
    if (bayX.length == 0) {
      foundBay = false;
      System.out.println("not found");
      return defaultArray[0];
    } else if (bayX.length == 1) {
      foundBay = true;
      System.out.println("found");
      return bayX[0];
    } else {
      System.out.println("found else");
      foundBay = true;
    }

    if (bayX[0] > bayX[1]) {
      return bayX[0];
    } else {
      return bayX[1];
    }
  }

  public double getX() {
    return getRawX() - (cameraWidth/2);
  }

  public double getBayCenter() {
    return getBayRawX() - (cameraWidth / 2);
  }

  public double getWidth() {
    return cameraWidth;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

public static CameraSubsystem getInstance() {
	if (instance == null){
    instance = new CameraSubsystem();
  
    }
    return instance;
    } 
}
//public double foundBay() {
//	return ;
//}
//}
