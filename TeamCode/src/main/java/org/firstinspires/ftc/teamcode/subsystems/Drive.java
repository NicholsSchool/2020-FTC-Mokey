package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class Drive {

    private DcMotor lFDrive;
    private DcMotorSimple lBDrive;
    private DcMotor rFDrive;
    private DcMotorSimple rBDrive;


    public Drive(HardwareMap hardwareMap) {
        lFDrive  = hardwareMap.get(DcMotor.class, "LFDrive");
        lBDrive  = hardwareMap.get(DcMotorSimple.class, "LBDrive");
        rFDrive  = hardwareMap.get(DcMotor.class, "RFDrive");
        rBDrive  = hardwareMap.get(DcMotorSimple.class, "RBDrive");

        lFDrive.resetDeviceConfigurationForOpMode();
        lBDrive.resetDeviceConfigurationForOpMode();
        rFDrive.resetDeviceConfigurationForOpMode();
        rBDrive.resetDeviceConfigurationForOpMode();

        lFDrive.setDirection(DcMotor.Direction.REVERSE);
        rBDrive.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
    }

    public void move(double lSpeed, double rSpeed) {
        lFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lFDrive.setPower(lSpeed);
        lBDrive.setPower(lSpeed);
        rFDrive.setPower(rSpeed);
        rBDrive.setPower(rSpeed);
    }

    public void strafe(double speed) {
        lFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // positive speed moves left
        lFDrive.setPower(speed);
        lBDrive.setPower(-speed);
        rFDrive.setPower(speed);
        rBDrive.setPower(-speed);
    }

    public boolean move(int position, double power) {
        int currentPosition = (lFDrive.getCurrentPosition() + rFDrive.getCurrentPosition()) / 2;

        double speed = currentPosition < position ? power : -power;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            move(speed, speed);

            return true;
        } else {
            return false;
        }
    }

    public boolean strafe(int position, double power) {
        int currentPosition = (lFDrive.getCurrentPosition() + rFDrive.getCurrentPosition()) / 2;

        double speed = currentPosition < position ? power : -power;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            strafe(speed);

            return true;
        } else {
            return false;
        }
    }

    public boolean turn(double angle, double speed) {
        lFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double currentAngle = Robot.imu.getOrientation();

        speed *= currentAngle < angle ? 1 : -1;

        if(Math.abs(currentAngle - angle) > Constants.kAngleTargetMargin) {
            lFDrive.setPower(-speed);
            lBDrive.setPower(-speed);
            rFDrive.setPower(speed);
            rBDrive.setPower(speed);

            return true;
        } else {
            return false;
        }
    }

    public void resetEncoders() {
        lFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void stop() {
        move(0.0, 0.0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("LF position", lFDrive.getCurrentPosition());
        telemetry.addData("LF power", lFDrive.getPower());
        telemetry.addData("LF isBusy", lFDrive.isBusy());
        telemetry.addData("RF position", rFDrive.getCurrentPosition());
    }

}
