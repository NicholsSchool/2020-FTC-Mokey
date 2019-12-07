

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;


/**
 * An auto OpMode for testing.
 */
@Autonomous(name="Test")
public class TestAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);
        waitForStart();


        int ticks = (int)(-24 * Constants.kTicksPerInch);
        while(Robot.drive.strafe(ticks, 0.7) && opModeIsActive()) {
            Robot.drive.debug(telemetry);
        }

//        Robot.imu.reset();
//        while(Robot.drive.turn(-90.0, 0.7) && opModeIsActive()) {
//            Robot.drive.debug(telemetry);
//        }

        Robot.stop();

        sleep(2000);
    }

}
