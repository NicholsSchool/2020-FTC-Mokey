

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.ParkCloseRoutine;

/**
 * An auto OpMode for parking from the close loading zone position while on the red alliance.
 */
@Autonomous(name="Red Park Loading Center Close")
public class RedParkLoadingCenterCloseAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);

        waitForStart();

        ParkCloseRoutine.run(this, "red", "loading", "center");

    }

}
