/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Const;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Drive OpMode")
public class DriveOpMode extends OpMode
{
    private DcMotor lFDrive;
    private DcMotorSimple lBDrive;
    private DcMotor rFDrive;
    private DcMotorSimple rBDrive;

    private DcMotor elevator;
    private DcMotor turret;
    private DcMotorSimple folder;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        lFDrive  = hardwareMap.get(DcMotor.class, "LFDrive");
        lBDrive  = hardwareMap.get(DcMotorSimple.class, "LBDrive");
        rFDrive  = hardwareMap.get(DcMotor.class, "RFDrive");
        rBDrive  = hardwareMap.get(DcMotorSimple.class, "RBDrive");

        elevator = hardwareMap.get(DcMotor.class, "Elevator");
        turret = hardwareMap.get(DcMotor.class, "Turret");
        folder = hardwareMap.get(DcMotorSimple.class, "Folder");

        lFDrive.resetDeviceConfigurationForOpMode();
        lBDrive.resetDeviceConfigurationForOpMode();
        rFDrive.resetDeviceConfigurationForOpMode();
        rBDrive.resetDeviceConfigurationForOpMode();

        lFDrive.setDirection(DcMotor.Direction.REVERSE);
        rBDrive.setDirection(DcMotor.Direction.REVERSE);

        elevator.resetDeviceConfigurationForOpMode();
        elevator.setDirection(DcMotor.Direction.REVERSE);

        turret.resetDeviceConfigurationForOpMode();

        folder.resetDeviceConfigurationForOpMode();

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
            lFDrive.setPower(strafeSpeed);
            lBDrive.setPower(-strafeSpeed);
            rFDrive.setPower(strafeSpeed);
            rBDrive.setPower(-strafeSpeed);
        } else {
            double lSpeed = -gamepad1.left_stick_y;
            double rSpeed = -gamepad1.right_stick_y;
            lFDrive.setPower(lSpeed);
            lBDrive.setPower(lSpeed);
            rFDrive.setPower(rSpeed);
            rBDrive.setPower(rSpeed);
        }

        // Elevator control
        if(gamepad2.dpad_up && elevator.getCurrentPosition() < Constants.kElevatorMax) {
            elevator.setPower(Constants.kElevatorUpSpeed);
        } else if(gamepad2.dpad_down && elevator.getCurrentPosition() > Constants.kElevatorMin) {
            elevator.setPower(Constants.kElevatorDownSpeed);
        } else {
            elevator.setPower(0);
        }

        // Turret control
        if(gamepad2.dpad_left) {
            turret.setPower(Constants.kTurretSpeed);
        } else if(gamepad2.dpad_right) {
            turret.setPower(-Constants.kTurretSpeed);
        } else {
            turret.setPower(0);
        }

        // Folder control
        if(gamepad2.y) {
            folder.setPower(Constants.kFolderUpSpeed);
        } else if(gamepad2.a) {
            folder.setPower(Constants.kFolderDownSpeed);
        } else {
            folder.setPower(0);
        }

        telemetry.addData("LF position: ", lFDrive.getCurrentPosition());
        telemetry.addData("RF position: ", rFDrive.getCurrentPosition());
        telemetry.addData("Elevator position: ", elevator.getCurrentPosition());
        telemetry.addData("Turret position: ", turret.getCurrentPosition());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        lFDrive.setPower(0);
        lBDrive.setPower(0);
        rFDrive.setPower(0);
        rBDrive.setPower(0);

        elevator.setPower(0);
        turret.setPower(0);
        folder.setPower(0);
    }

}
