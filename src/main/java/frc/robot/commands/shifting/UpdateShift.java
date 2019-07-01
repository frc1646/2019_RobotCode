package frc.robot.commands.shifting;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShiftingSubsystem;
import frc.robot.subsystems.StatusSubsystem;
import jdk.jfr.Threshold;

public class UpdateShift extends Command {

    private ShiftingSubsystem shifter;
    private DriveSubsystem drive;
    private OI oi;
    private StatusSubsystem status;
    public static final double THRESHOLD = 0.25;
    private double lastCheck;

    public UpdateShift() {
        requires(shifter = ShiftingSubsystem.getInstance());
        drive = DriveSubsystem.getInstance();
        oi = OI.getInstance();
    }

    @Override
    protected void initialize() {
        
    }

    //FIXME
    @Override
    protected void execute() {
        status.setDesiredGear(!oi.getShiftButton());
        double speed = Math.abs(drive.getLeftSpeed() + drive.getRightSpeed()) / 2;
        if(status.getDesiredGear() && speed > THRESHOLD) {
            shifter.shiftUp();
        } else if (!status.getDesiredGear() && speed > THRESHOLD){
            shifter.shiftDown();
        } else {
            shifter.shiftOff();
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}