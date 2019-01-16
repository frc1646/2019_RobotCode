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
  

  private static CameraSubsystem instance;
  double cameraWidth = 158;
  double[] defaultArray = {cameraWidth/2};

  public boolean foundBall;

  

  public CameraSubsystem() {
    ballVisionContour = NetworkTable.getTable("GRIP/orangeBall");
    foundBall = false;

  }

  public boolean isBallFound(){
    return foundBall;
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

  public double getX() {
    return getRawX() - (cameraWidth/2);

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
