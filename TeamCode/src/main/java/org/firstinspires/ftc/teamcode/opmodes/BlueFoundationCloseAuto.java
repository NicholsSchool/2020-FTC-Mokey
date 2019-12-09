package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.FoundationRoutine;
import org.firstinspires.ftc.teamcode.autonomous.ParkCloseRoutine;

/**
 * An auto OpMode for moving the foundation from the close loading zone position while on the blue alliance.
 */
@Autonomous(name="Blue Foundation Close")
public class BlueFoundationCloseAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);

        waitForStart();

        ParkCloseRoutine.run(this, "blue", "loading", "side");

        FoundationRoutine.run(this, "blue");

    }

}
