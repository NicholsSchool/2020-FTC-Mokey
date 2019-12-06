package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

public class Drive {

    private DcMotor mLFDrive;
    private DcMotorSimple mLBDrive;
    private DcMotor mRFDrive;
    private DcMotorSimple mRBDrive;


    public Drive(HardwareMap hardwareMap) {
        mLFDrive = hardwareMap.get(DcMotor.class, "LFDrive");
        mLBDrive = hardwareMap.get(DcMotorSimple.class, "LBDrive");
        mRFDrive = hardwareMap.get(DcMotor.class, "RFDrive");
        mRBDrive = hardwareMap.get(DcMotorSimple.class, "RBDrive");

        mLFDrive.resetDeviceConfigurationForOpMode();
        mLBDrive.resetDeviceConfigurationForOpMode();
        mRFDrive.resetDeviceConfigurationForOpMode();
        mRBDrive.resetDeviceConfigurationForOpMode();

        mLFDrive.setDirection(DcMotor.Direction.REVERSE);
        mRBDrive.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();
    }

    public void move(double lSpeed, double rSpeed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLFDrive.setPower(lSpeed);
        mLBDrive.setPower(lSpeed);
        mRFDrive.setPower(rSpeed * Constants.kDriveEqualizer);
        mRBDrive.setPower(rSpeed * Constants.kDriveEqualizer);
    }

    public void strafe(double speed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // positive speed moves left
        mLFDrive.setPower(speed);
        mLBDrive.setPower(-speed);
        mRFDrive.setPower(speed);
        mRBDrive.setPower(-speed);
    }

    public boolean move(int position, double power) {
        int currentPosition = (mLFDrive.getCurrentPosition() + mRFDrive.getCurrentPosition()) / 2;

        double speed = currentPosition < position ? power : -power;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            move(speed, speed);

            return true;
        } else {
            return false;
        }
    }

    public boolean strafe(int position, double power) {
        int currentPosition = (mLFDrive.getCurrentPosition() + mRFDrive.getCurrentPosition()) / 2;

        double speed = currentPosition < position ? power : -power;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            strafe(speed);

            return true;
        } else {
            return false;
        }
    }

    public boolean turn(double angle, double power) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double currentAngle = Robot.imu.getOrientation();

        double speed = currentAngle < angle ? power : -power;

        if(Math.abs(currentAngle - angle) > Constants.kAngleTargetMargin) {
            mLFDrive.setPower(-speed * Constants.kTurnEqualizer);
            mLBDrive.setPower(-speed * Constants.kTurnEqualizer);
            mRFDrive.setPower(speed);
            mRBDrive.setPower(speed);

            return true;
        } else {
            return false;
        }
    }

    public void resetEncoders() {
        mLFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void stop() {
        move(0.0, 0.0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("LF position", mLFDrive.getCurrentPosition());
        telemetry.addData("LF power", mLFDrive.getPower());
        telemetry.addData("LF isBusy", mLFDrive.isBusy());
        telemetry.addData("RF position", mRFDrive.getCurrentPosition());
    }

}
