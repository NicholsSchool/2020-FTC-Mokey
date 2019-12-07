package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine to move the foundation from a parked position.
 */
public class FoundationRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     * @param alliance the current alliance
     */
    public static void run(LinearOpMode opMode, String alliance) {
        // Back up to foundation
        Robot.drive.resetEncoders();
        int ticks = (int)(-48 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Turn towards foundation
        Robot.imu.reset();
        double angle = alliance.equals("red") ? -90.0 : 90.0;
        while(Robot.drive.turn(angle, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Prepare to grab foundation
        while(!Robot.folderUp.isPressed() && opMode.opModeIsActive()) {
            Robot.folder.move(Constants.kFolderUpSpeed);
        }
        Robot.stop();

        Robot.elevator.move(-150, Constants.kElevatorUpSpeed);
        while(Robot.elevator.isBusy() && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Move towards foundation
        Robot.drive.resetEncoders();
        ticks = (int)(26 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Elevator down
        Robot.elevator.move(-220, Constants.kElevatorDownSpeed);
        while(Robot.elevator.isBusy() && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Open grabber
        Robot.grabber.move(Constants.kGrabberReleaseAutoSpeed);
        opMode.sleep(1000);
        Robot.stop();
        //

        // Drag foundation
        Robot.elevator.move(Constants.kElevatorDownSpeed);
        opMode.sleep(500);
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks = (int)(-38 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {
            Robot.elevator.move(Constants.kElevatorDownSpeed);
        }
        Robot.stop();
        //

    }
}
