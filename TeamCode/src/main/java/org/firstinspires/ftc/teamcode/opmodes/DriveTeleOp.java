

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;


/**
 * A driver-controlled OpMode for the TeleOp period.
 */
@TeleOp(name="Drive")
public class DriveTeleOp extends OpMode
{
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        Robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        // Tank drive and strafe
        double strafeSpeed;
        if(gamepad1.left_bumper) {
            strafeSpeed = Constants.kSlowStrafeSpeed;
        } else if(gamepad1.right_bumper) {
            strafeSpeed = -Constants.kSlowStrafeSpeed;
        } else if(gamepad1.left_trigger > Constants.kTriggerThreshold) {
            strafeSpeed = Constants.kFastStrafeSpeed;
        } else if(gamepad1.right_trigger > Constants.kTriggerThreshold) {
            strafeSpeed = -Constants.kFastStrafeSpeed;
        } else {
            strafeSpeed = 0;
        }

        if(strafeSpeed != 0) {
            Robot.drive.strafe(strafeSpeed);
        } else {
            double lSpeed = -gamepad1.left_stick_y;
            double rSpeed = -gamepad1.right_stick_y;

            Robot.drive.move(lSpeed, rSpeed);
        }
        //

        // Elevator control
        if(gamepad2.dpad_up) {
            Robot.elevator.move(Constants.kElevatorUpSpeed);
        } else if(gamepad2.dpad_down) {
            Robot.elevator.move(Constants.kElevatorDownSpeed);
        } else {
            Robot.elevator.move(0.0);
        }
        //

        // Turret control
        if(gamepad2.dpad_left) {
            Robot.turret.move(Constants.kTurretSpeed);
        } else if(gamepad2.dpad_right) {
            Robot.turret.move(-Constants.kTurretSpeed);
        } else {
            Robot.turret.move(0.0);
        }
        //

        // Folder control
        if(gamepad2.y) {
            Robot.folder.move(Constants.kFolderUpSpeed);
        } else if(gamepad2.a) {
            Robot.folder.move(Constants.kFolderDownSpeed);
        } else {
            Robot.folder.move(0.0);
        }
        //

        // Grabber control
        Robot.grabber.move(0.5 * gamepad2.left_trigger - gamepad2.right_trigger);
        //

        Robot.elevator.debug(telemetry);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        Robot.stop();
    }

}
