package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class RetractClimber extends Command {

    private DriveSubsystem drive;
    private double startTime;

    public RetractClimber() {
        requires(drive = DriveSubsystem.getInstance());
    }

    protected void initialize() {
        drive.retractClimb();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > 0.2;
    }

    protected void end() {
        drive.turnClimbOff();
    }

}