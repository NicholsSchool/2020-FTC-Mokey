package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Drive {

    private DcMotor lFDrive;
    private DcMotorSimple lBDrive;
    private DcMotor rFDrive;
    private DcMotorSimple rBDrive;

    private BNO055IMU imu;
    private float orientationZero;

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


        imu = hardwareMap.get(BNO055IMU.class, "IMU");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);

        resetEncoders();
    }

    public void move(double lSpeed, double rSpeed) {

        lFDrive.setPower(lSpeed);
        lBDrive.setPower(lSpeed);
        rFDrive.setPower(rSpeed);
        rBDrive.setPower(rSpeed);
    }

    public void strafe(double speed) {
        // positive speed moves left
        lFDrive.setPower(speed);
        lBDrive.setPower(-speed);
        rFDrive.setPower(speed);
        rBDrive.setPower(-speed);
    }

    public boolean move(int position, double speed) {
        int currentPosition = (lFDrive.getCurrentPosition() + rFDrive.getCurrentPosition()) / 2;

        speed *= currentPosition < position ? 1 : -1;

        if(Math.abs(currentPosition - position) > Constants.kEncoderTargetMargin) {
            lFDrive.setPower(speed);
            lBDrive.setPower(speed);
            rFDrive.setPower(speed);
            rBDrive.setPower(speed);

            return true;
        } else {
            return false;
        }
    }

    public boolean turn(double angle, double speed) {
        float currentAngle = (imu.getAngularOrientation().firstAngle - orientationZero) % 180;

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
        lFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rFDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetIMU() {
        orientationZero = imu.getAngularOrientation().firstAngle;
    }

    public void stop() {
        lFDrive.setPower(0);
        lBDrive.setPower(0);
        rFDrive.setPower(0);
        rBDrive.setPower(0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("LF position", lFDrive.getCurrentPosition());
        telemetry.addData("LF power", lFDrive.getPower());
        telemetry.addData("LF isBusy", lFDrive.isBusy());
        telemetry.addData("RF position", rFDrive.getCurrentPosition());
        telemetry.addData("IMU angle 1", imu.getAngularOrientation().firstAngle);
        telemetry.addData("IMU angle 2", imu.getAngularOrientation().secondAngle);
        telemetry.addData("IMU angle 3", imu.getAngularOrientation().thirdAngle);
        telemetry.update();
    }

}
