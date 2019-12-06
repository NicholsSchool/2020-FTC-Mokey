package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Grabber {
    private DcMotorSimple mGrabber;

    public Grabber(HardwareMap hardwareMap) {
        mGrabber = hardwareMap.get(DcMotorSimple.class, "Grabber");
        mGrabber.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {

        mGrabber.setPower(speed);
    }

    public void stop() {
        move(0.0);
    }
}
