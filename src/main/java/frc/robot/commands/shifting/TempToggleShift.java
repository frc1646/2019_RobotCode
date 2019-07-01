package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.ShiftingSubsystem;

public class TempToggleShift extends Command {

    private ShiftingSubsystem shifter;

    public TempToggleShift() {
        requires(shifter = ShiftingSubsystem.getInstance());
    }

    @Override
    protected void initialize() {
        shifter.shiftUp();
    }

    @Override
    protected void execute() {
        shifter.shiftOff();
    }

    @Override
    protected void interrupted() {
        end();
    }

    protected void end() {
        shifter.shiftDown();
        double startTime = Timer.getFPGATimestamp();
        while (Timer.getFPGATimestamp() - startTime < 0.05) {}
        shifter.shiftOff();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}