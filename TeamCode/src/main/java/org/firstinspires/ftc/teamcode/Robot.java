package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Imu;
import org.firstinspires.ftc.teamcode.sensors.Touch;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Folder;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

/**
 * The Robot class contains static references to each subsystem and sensor on Mokey.
 */
public class Robot {

    public static Drive drive;
    public static Elevator elevator;
    public static Turret turret;
    public static Folder folder;
    public static Grabber grabber;

    public static Imu imu;

    public static Touch folderDown;
    public static Touch folderUp;

    /**
     * Initializes each subsystem and sensor to its default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public static void init(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        turret = new Turret(hardwareMap);
        folder = new Folder(hardwareMap);
        grabber = new Grabber(hardwareMap);

        imu = new Imu(hardwareMap);

        folderDown = new Touch(hardwareMap, "FolderDown");
        folderUp = new Touch(hardwareMap, "FolderUp");
    }

    /**
     * Soft stops the robot. Note this does not reset and sensors or encoders.
     */
    public static void stop() {
        drive.stop();
        elevator.stop();
        turret.stop();
        folder.stop();
        grabber.stop();
    }
}
