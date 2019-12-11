

package org.firstinspires.ftc.teamcode.opmodes.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.ParkRoutine;

/**
 * An auto OpMode for parking from the close loading zone position while on the blue alliance.
 */
@Autonomous(name="ParkBlueLoadingNeutralAuto")
public class ParkBlueLoadingNeutralAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args

        waitForStart();

        ParkRoutine.run(this, args[1], args[2], args[3]);

    }

}
