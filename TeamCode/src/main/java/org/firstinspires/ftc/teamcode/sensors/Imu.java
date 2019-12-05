package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Imu {

    private BNO055IMU imu;
    private float orientationZero;

    public Imu(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BNO055IMU.class, "IMU");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);
    }

    public void reset() {
        orientationZero = imu.getAngularOrientation().firstAngle;
    }

    public double getOrientation() {
        double orientation = imu.getAngularOrientation().firstAngle - orientationZero;

        if(orientation > 180) {
            return orientation - 360;
        } else if(orientation < -180) {
            return orientation + 360;
        } else {
            return orientation;
        }
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("IMU angle 1", imu.getAngularOrientation().firstAngle);
        telemetry.addData("IMU angle 2", imu.getAngularOrientation().secondAngle);
        telemetry.addData("IMU angle 3", imu.getAngularOrientation().thirdAngle);
        telemetry.update();
    }
}
