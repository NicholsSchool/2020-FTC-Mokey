package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * An auto routine to park from the close position in the loading zone.
 */
public class ParkRoutine {

    /**
     * Runs the routine.
     * @param opMode the OpMode running the routine
     * @param alliance the current alliance
     */
    public static void run(LinearOpMode opMode, String alliance, String start, String finish) {
        // Back up
        Robot.drive.resetEncoders();
        int ticks = 0;
        if(finish.equals("Wall")) {
            ticks = (int)(-2 * Constants.kTicksPerInch);
        } else {
            ticks = (int)(-26 * Constants.kTicksPerInch);
        }
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        // Turn
        Robot.imu.reset();
        double angle = alliance.equals("Red") ? -90.0 : 90.0;
        angle *= start.equals("Loading") ? 1 : -1;
        while(Robot.drive.turn(angle, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //

        UnfoldRoutine.run(opMode);

        // Back up
        Robot.drive.resetEncoders();
        ticks = (int)(-12 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kDriveAutoSpeed) && opMode.opModeIsActive()) {

        }
        Robot.stop();
        //
    }
}