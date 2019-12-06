package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Imu;
import org.firstinspires.ftc.teamcode.sensors.Touch;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Folder;
import org.firstinspires.ftc.teamcode.subsystems.Grabber;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class Robot {

    public static Drive drive;
    public static Elevator elevator;
    public static Turret turret;
    public static Folder folder;
    public static Grabber grabber;

    public static Imu imu;

    public static Touch folderDown;
    public static Touch folderUp;

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

    public static void stop() {
        drive.stop();
        elevator.stop();
        turret.stop();
        folder.stop();
        grabber.stop();
    }
}
