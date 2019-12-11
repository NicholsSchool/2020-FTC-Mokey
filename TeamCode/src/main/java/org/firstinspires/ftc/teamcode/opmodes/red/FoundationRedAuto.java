package org.firstinspires.ftc.teamcode.opmodes.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.FoundationRoutine;

/**
 * An auto OpMode for moving the foundation from the close loading zone position while on the blue alliance.
 */
@Autonomous(name="FoundationRedAuto")
public class FoundationRedAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);
        String[] args = getClass().getSimpleName().split("(?<=[a-z])(?=[A-Z])"); // split class name for args

        waitForStart();

        FoundationRoutine.run(this, args[1]);

    }

}
