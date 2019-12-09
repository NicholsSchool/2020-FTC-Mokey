package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Drive controls the drive train of Mokey.
 */
public class Drive {

    private DcMotor mLFDrive;
    private DcMotorSimple mLBDrive;
    private DcMotor mRFDrive;
    private DcMotorSimple mRBDrive;


    /**
     * Creates a drive train with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey.
     */
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

    /**
     * Tank drive.
     * @param lSpeed speed of the left motors, in the range [-1.0, 1.0]
     * @param rSpeed speed of the right motors, in the range [-1.0, 1.0]
     */
    public void move(double lSpeed, double rSpeed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mLFDrive.setPower(lSpeed);
        mLBDrive.setPower(lSpeed);
        mRFDrive.setPower(rSpeed * Constants.kDriveEqualizer);
        mRBDrive.setPower(rSpeed * Constants.kDriveEqualizer);
    }

    /**
     * Mecanum strafe horizontally.
     * @param speed speed of the strafe, in the range [-1.0, 1.0], a positive speed moves left
     */
    public void strafe(double speed) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // positive speed moves left
        mLFDrive.setPower(speed * Constants.kStrafeEqualizer);
        mLBDrive.setPower(-speed);
        mRFDrive.setPower(speed * Constants.kStrafeEqualizer);
        mRBDrive.setPower(-speed);
    }

    /**
     * Drives straight to a position with encoders.
     * @param position the target encoder position
     * @param power the power of the motors, in the range [0.0, 1.0]
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
    public boolean move(int position, double power) {
        int currentPosition = mLFDrive.getCurrentPosition() > mRFDrive.getCurrentPosition() ? mLFDrive.getCurrentPosition() : mRFDrive.getCurrentPosition();

        double speed = currentPosition < position ? power : -power;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            move(speed, speed);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Strafes to a position with encoders.
     * @param position the target encoder position, positive is to the left
     * @param power the power of the motors, in the range [0.0, 1.0]
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
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

    /**
     * Turns in place to a position with the IMU.
     * @param angle the target angle, positive is counterclockwise
     * @param power the power of the motors, in the range [0.0, 1.0]
     * @return true if the robot is still moving towards the target, false if the robot is at the target
     */
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

    public boolean turn(double angle, double power, String side) {
        mLFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double currentAngle = Robot.imu.getOrientation();

        double speed = currentAngle < angle ? power : -power;

        if(Math.abs(currentAngle - angle) > Constants.kAngleTargetMargin) {
            if(side.equals("left")) {
                mLFDrive.setPower(-speed * Constants.kTurnEqualizer);
                mLBDrive.setPower(-speed * Constants.kTurnEqualizer);
            } else {
                mRFDrive.setPower(speed);
                mRBDrive.setPower(speed);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Resets the drive encoders.
     */
    public void resetEncoders() {
        mLFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mRFDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Soft stops the drive train.
     */
    public void stop() {
        move(0.0, 0.0);
    }

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the current telemetry
     */
    public void debug(Telemetry telemetry) {
        telemetry.addData("LF position", mLFDrive.getCurrentPosition());
        telemetry.addData("LF power", mLFDrive.getPower());
        telemetry.addData("LF isBusy", mLFDrive.isBusy());
        telemetry.addData("RF position", mRFDrive.getCurrentPosition());
    }

}
