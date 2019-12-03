package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Elevator;
import org.firstinspires.ftc.teamcode.subsystems.Folder;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class Robot {

    public Drive drive;
    public Elevator elevator;
    public Turret turret;
    public Folder folder;

    public Robot(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        turret = new Turret(hardwareMap);
        folder = new Folder(hardwareMap);
    }

    public void stop() {
        drive.stop();
        elevator.stop();
        turret.stop();
        folder.stop();
    }
}
