package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShiftingSubsystem;
import sun.tools.tree.ShiftRightExpression;

public class ToggleShift extends Command {

    private ShiftingSubsystem shifter;

    public ToggleShift() {
        requires(shifter = ShiftingSubsystem.getInstance());
    }

    @Override
    protected void initialize() {
        if (shifter.isHighGear()) {
             shifter.shiftDown();
         } else {
             shifter.shiftUp();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}