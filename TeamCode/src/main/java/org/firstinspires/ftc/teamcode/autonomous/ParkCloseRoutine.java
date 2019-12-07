package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine to park from the close position in the loading zone.
 */
public class ParkCloseRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     * @param alliance the current alliance
     */
    public static void run(LinearOpMode opMode, String alliance) {
        // Back up
        Robot.drive.resetEncoders();
        int ticks = (int)(-6 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Turn
        Robot.imu.reset();
        double angle = alliance.equals("red") ? -90.0 : 90.0;
        while(Robot.drive.turn(angle, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        UnfoldRoutine.run(opMode);

        // Back up
        Robot.drive.resetEncoders();
        ticks = (int)(-18 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //
    }
}
