package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Imu handles the imu data from the expansion hub on Mokey.
 */
public class Imu {

    private BNO055IMU mImu;
    private float mOrientationZero;

    /**
     * Creates an imu with the default config at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Imu(HardwareMap hardwareMap) {
        mImu = hardwareMap.get(BNO055IMU.class, "IMU");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        mImu.initialize(parameters);
    }

    /**
     * Resets the imu.
     */
    public void reset() {
        mOrientationZero = mImu.getAngularOrientation().firstAngle;
    }

    /**
     * Returns the yaw of Mokey.
     * @return the yaw in degrees, in the range [-180.0, 180.0]
     */
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

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the output telemetry
     */
    public void debug(Telemetry telemetry) {
        telemetry.addData("IMU angle 1", mImu.getAngularOrientation().firstAngle);
        telemetry.addData("IMU angle 2", mImu.getAngularOrientation().secondAngle);
        telemetry.addData("IMU angle 3", mImu.getAngularOrientation().thirdAngle);
    }
}
