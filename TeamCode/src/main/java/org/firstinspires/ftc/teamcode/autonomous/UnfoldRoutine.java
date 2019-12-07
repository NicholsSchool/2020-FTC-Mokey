package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine to unfold from the starting configuration of Mokey.
 */
public class UnfoldRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     */
    public static void run(LinearOpMode opMode) {

        // Fold up
        while(!Robot.folderUp.isPressed() && opMode.opModeIsActive()) {
            Robot.folder.move(Constants.kFolderUpSpeed);
            Robot.grabber.move(Constants.kGrabberClampAutoSpeed);
        }
        Robot.stop();

        // Elevator down
        Robot.elevator.move(Constants.kElevatorBelowJoint, Constants.kElevatorDownSpeed);
        while(Robot.elevator.isBusy() && opMode.opModeIsActive()) {

        }
        Robot.stop();

        // Fold down
        while(!Robot.folderDown.isPressed() && opMode.opModeIsActive()) {
            Robot.folder.move(Constants.kFolderDownSpeed);
        }
        Robot.stop();
    }
}
