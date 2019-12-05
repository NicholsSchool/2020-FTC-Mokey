package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class Folder {
    private DcMotorSimple folder;

    public Folder(HardwareMap hardwareMap) {
        folder = hardwareMap.get(DcMotorSimple.class, "Folder");
        folder.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {

        if(!Robot.folderUp.isPressed() && speed > 0) {
            folder.setPower(speed);
        } else if(!Robot.folderDown.isPressed() && speed < 0) {
            folder.setPower(speed);
        } else {
            folder.setPower(0);
        }
    }

    public void stop() {
        move(0.0);
    }
}
