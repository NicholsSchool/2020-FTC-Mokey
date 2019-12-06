package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

public class Folder {
    private DcMotorSimple mFolder;

    public Folder(HardwareMap hardwareMap) {
        mFolder = hardwareMap.get(DcMotorSimple.class, "Folder");
        mFolder.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {

        if(!Robot.folderUp.isPressed() && speed > 0) {
            mFolder.setPower(speed);
        } else if(!Robot.folderDown.isPressed() && speed < 0) {
            mFolder.setPower(speed);
        } else {
            mFolder.setPower(0);
        }
    }

    public void stop() {
        move(0.0);
    }
}
