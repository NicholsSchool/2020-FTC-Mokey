package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class Grabber {
    private DcMotorSimple grabber;

    public Grabber(HardwareMap hardwareMap) {
        grabber = hardwareMap.get(DcMotorSimple.class, "Grabber");
        grabber.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {

        grabber.setPower(speed);
    }

    public void stop() {
        move(0.0);
    }
}
