

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


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
        DcMotorSimple servo1 = hardwareMap.get(DcMotorSimple.class, "Servo1");
        DcMotorSimple servo2 = hardwareMap.get(DcMotorSimple.class, "Servo2");

        waitForStart();

        servo1.setPower(-1.0);
        servo2.setPower(1.0);

        sleep(29000);

        servo1.setPower(0);
        servo2.setPower(0);
    }

}
