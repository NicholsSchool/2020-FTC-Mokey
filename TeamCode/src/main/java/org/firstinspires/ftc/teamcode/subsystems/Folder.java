package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * Folder controls the folding joint mechanism on Mokey.
 */
public class Folder {
    private DcMotorSimple mFolder;

    /**
     * Creates a folder with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Folder(HardwareMap hardwareMap) {
        mFolder = hardwareMap.get(DcMotorSimple.class, "Folder");
        mFolder.resetDeviceConfigurationForOpMode();
    }

    /**
     * Folds or unfolds.
     * @param speed the speed of the motor, in the range [-1.0, 1.0]
     */
    public void move(double speed) {

        if(!Robot.folderUp.isPressed() && speed > 0) {
            mFolder.setPower(speed);
        } else if(!Robot.folderDown.isPressed() && speed < 0) {
            mFolder.setPower(speed);
        } else {
            mFolder.setPower(0);
        }
    }

    /**
     * Soft stops the folder.
     */
    public void stop() {
        move(0.0);
    }
}
