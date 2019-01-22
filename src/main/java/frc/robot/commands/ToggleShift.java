package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class ToggleShift extends Command {

    private DriveSubsystem drive;

    public ToggleShift() {
        requires(drive = DriveSubsystem.getInstance());
    }

    @Override
    protected void initialize() {
        if (drive.isHighGear()) {
            drive.shiftDown();
        } else {
            drive.shiftUp();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}