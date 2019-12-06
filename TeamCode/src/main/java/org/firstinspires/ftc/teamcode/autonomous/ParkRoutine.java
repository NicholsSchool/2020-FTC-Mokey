package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class ParkRoutine {

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
