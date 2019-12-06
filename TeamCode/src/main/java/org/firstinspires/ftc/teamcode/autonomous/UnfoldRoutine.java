package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class UnfoldRoutine {

    public static void run(LinearOpMode opMode) {

        while(!Robot.folderUp.isPressed() && opMode.opModeIsActive()) {
            Robot.folder.move(Constants.kFolderUpSpeed);
        }

        Robot.elevator.move(Constants.kElevatorBelowJoint, Constants.kElevatorDownSpeed);
        while(Robot.elevator.isBusy() && opMode.opModeIsActive()) {

        }

        while(!Robot.folderDown.isPressed() && opMode.opModeIsActive()) {
            Robot.folder.move(Constants.kFolderDownSpeed);
        }

        Robot.stop();
    }
}
