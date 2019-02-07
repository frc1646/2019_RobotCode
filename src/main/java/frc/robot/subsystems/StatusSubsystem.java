/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/* This subsystem is for the status lights on the robot. */

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.SetStatusLights;


/**
 * Add your docs here.
 */
public class StatusSubsystem extends Subsystem {

  private Spark statusLights;
  private static StatusSubsystem instance;

  public StatusSubsystem() {
    statusLights = new Spark(RobotMap.BLINKIN_ID);
  }

  /**
   * @param statusLights the statusLights to set
   */
  public void setStatusLights(double statusLightsValue) {
    statusLights.set(statusLightsValue);
  }

 // public double getMatchTime() {

 // }

  public static StatusSubsystem getInstance() { 
    if (instance == null) { 
      instance = new StatusSubsystem(); 
    } 
    return instance; 
  } 

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // The term idiotLights will act as "Spark"
  // Spark is used as the REVBlinkin

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SetStatusLights());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
}
