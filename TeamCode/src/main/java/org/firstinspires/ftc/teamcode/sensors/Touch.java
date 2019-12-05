package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Touch {

    private TouchSensor touch;

    // TODO: refactor instance variables
    private String deviceName;

    public Touch(HardwareMap hardwareMap, String deviceName) {
        touch = hardwareMap.get(TouchSensor.class, deviceName);
        this.deviceName = deviceName;
    }

    public boolean isPressed() {
        return touch.isPressed();
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData(deviceName, touch.isPressed());
    }
}
