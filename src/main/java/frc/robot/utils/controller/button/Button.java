/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils.controller.button;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class provides an easy way to link commands to OI inputs.
 *
 * <p>It is very easy to link a button to a command. For instance, you could link the trigger button
 * of a joystick to a "score" command.
 *
 * <p>This class represents a subclass of Trigger that is specifically aimed at buttons on an
 * operator interface as a common use case of the more generalized Trigger objects. This is a simple
 * wrapper around Trigger with the method names renamed to fit the Button object use.
 */
public abstract class Button extends Trigger {

  /**
   * Starts the given command whenever the button is newly pressed.
   *
   * @param command the command to start
   */
  public Button whenPressed(final Command command) {
    whenActive(command);
    return this;
  }

  /**
   * Constantly starts the given command while the button is held.
   *
   * {@link Command#start()} will be called repeatedly while the button is held, and will be
   * canceled when the button is released.
   *
   * @param command the command to start
   * 
   * @return the button
   */
  public Button whileHeld(final Command command) {
    whileActive(command);
    return this;
  }

  /**
   * Starts the command when the button is released.
   *
   * @param command the command to start
   * 
   * @return the button
   */
  public Button whenReleased(final Command command) {
    whenInactive(command);
    return this;
  }

  /**
   * Toggles the command whenever the button is pressed (on then off then on).
   *
   * @param command the command to start
   * 
   * @return the button
   */
  public Button toggleWhenPressed(final Command command) {
    toggleWhenActive(command);
    return this;
  }

  /**
   * Cancel the command when the button is pressed.
   *
   * @param command the command to start
   * 
   * @return the button
   */
  public Button cancelWhenPressed(final Command command) {
    cancelWhenActive(command);
    return this;
  }
}
