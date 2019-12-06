package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Touch {

    private TouchSensor mTouch;

    private String mDeviceName;

    public Touch(HardwareMap hardwareMap, String deviceName) {
        mTouch = hardwareMap.get(TouchSensor.class, deviceName);
        this.mDeviceName = deviceName;
    }

    public boolean isPressed() {
        return mTouch.isPressed();
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData(mDeviceName, mTouch.isPressed());
    }
}
