/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {

    public static final double FEET_PER_COUNT = Math.PI / 1024;
    //shift high much early than it already is
    //do not shift when there is no joystick input
    public static final double DRIVE_MAX_VEL = 12.0; // feet per second
    public static final double ANGLE_MAX_VEL = 340; // degrees per second

    public static final double LOW_AUTOSHIFT = 7;
    public static final double HIGH_AUTOSHIFT = 25;

    public static final double DRIVE_VEL_P = -0.1;
    public static final double DRIVE_VEL_I = 0;
    public static final double DRIVE_VEL_D = 0; 
    
    public static final double ANGLE_VEL_P = -0.4;
    public static final double ANGLE_VEL_I = 0;
    public static final double ANGLE_VEL_D = -0.2;

    public static final double TANK_LEFT_VEL_P = -29;
    public static final double TANK_LEFT_VEL_I = 0;
    public static final double TANK_LEFT_VEL_D = 0;

    public static final double TANK_RIGHT_VEL_P = -20;
    public static final double TANK_RIGHT_VEL_I = 0;
    public static final double TANK_RIGHT_VEL_D = 0;

    public static final double TANK_LEFT_KF = 1 / (DRIVE_MAX_VEL);

    public static final double TANK_RIGHT_KF = 1 / (DRIVE_MAX_VEL);
}
