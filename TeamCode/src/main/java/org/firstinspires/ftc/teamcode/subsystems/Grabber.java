package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Grabber controls the stone-grabbing mechanism of Mokey.
 */
public class Grabber {
    private DcMotorSimple mGrabber;

    /**
     * Creates a grabber with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Grabber(HardwareMap hardwareMap) {
        mGrabber = hardwareMap.get(DcMotorSimple.class, "Grabber");
        mGrabber.resetDeviceConfigurationForOpMode();
    }

    /**
     * Clamp or release the grabber.
     * @param speed the speed of the motor, in the range [-1.0, 1.0]
     */
    public void move(double speed) {

        mGrabber.setPower(speed);
    }

    /**
     * Soft stops the grabber.
     */
    public void stop() {
        move(0.0);
    }
}
