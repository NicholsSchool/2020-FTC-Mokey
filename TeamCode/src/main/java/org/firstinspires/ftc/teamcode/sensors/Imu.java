package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Imu {

    private BNO055IMU mImu;
    private float mOrientationZero;

    public Imu(HardwareMap hardwareMap) {
        mImu = hardwareMap.get(BNO055IMU.class, "IMU");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        mImu.initialize(parameters);
    }

    public void reset() {
        mOrientationZero = mImu.getAngularOrientation().firstAngle;
    }

    public double getOrientation() {
        double orientation = mImu.getAngularOrientation().firstAngle - mOrientationZero;

        if(orientation > 180) {
            return orientation - 360;
        } else if(orientation < -180) {
            return orientation + 360;
        } else {
            return orientation;
        }
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("IMU angle 1", mImu.getAngularOrientation().firstAngle);
        telemetry.addData("IMU angle 2", mImu.getAngularOrientation().secondAngle);
        telemetry.addData("IMU angle 3", mImu.getAngularOrientation().thirdAngle);
    }
}
