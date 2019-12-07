package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Touch handles the touch sensors on Mokey.
 */
public class Touch {

    private TouchSensor mTouch;

    private String mDeviceName;

    /**
     * Creates a touch sensor with the default config at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     * @param deviceName the device name of the touch sensor
     */
    public Touch(HardwareMap hardwareMap, String deviceName) {
        mTouch = hardwareMap.get(TouchSensor.class, deviceName);
        this.mDeviceName = deviceName;
    }

    /**
     * Returns if the touch sensor is pressed.
     * @return true if it the pressed, false if it is not
     */
    public boolean isPressed() {
        return mTouch.isPressed();
    }

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the output telemetry
     */
    public void debug(Telemetry telemetry) {
        telemetry.addData(mDeviceName, mTouch.isPressed());
    }
}
