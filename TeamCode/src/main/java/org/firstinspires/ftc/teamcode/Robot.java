package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.sensors.Imu;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Folder;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class Robot {

    public static Drive drive;
    public static Elevator elevator;
    public static Turret turret;
    public static Folder folder;

    public static Imu imu;

    public static void init(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        turret = new Turret(hardwareMap);
        folder = new Folder(hardwareMap);

        imu = new Imu(hardwareMap);
    }

    public static void stop() {
        drive.stop();
        elevator.stop();
        turret.stop();
        folder.stop();
    }
}
