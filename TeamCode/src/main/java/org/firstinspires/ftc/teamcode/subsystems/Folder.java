package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Folder {
    private DcMotorSimple folder;

    public Folder(HardwareMap hardwareMap) {
        folder = hardwareMap.get(DcMotorSimple.class, "Folder");
        folder.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {
        folder.setPower(speed);
    }

    public void stop() {
        folder.setPower(0);
    }
}
