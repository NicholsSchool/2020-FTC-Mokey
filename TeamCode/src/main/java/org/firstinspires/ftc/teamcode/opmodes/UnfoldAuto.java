

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.autonomous.UnfoldRoutine;


/**
 * An auto OpMode that does nothing.
 */
@Autonomous(name="Unfold")
public class UnfoldAuto extends LinearOpMode {


    /**
     * Runs the OpMode.
     */
    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);
        waitForStart();

        UnfoldRoutine.run(this);
    }

}
