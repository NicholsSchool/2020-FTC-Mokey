package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Turret controls the rotating turret on Mokey.
 */
public class Turret {

    private DcMotor mTurret;

    /**
     * Creates a turret with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Turret(HardwareMap hardwareMap) {
        mTurret = hardwareMap.get(DcMotor.class, "Turret");
        mTurret.resetDeviceConfigurationForOpMode();
        mTurret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoder();
    }

    /**
     * Turns the turret.
     * @param speed the speed of the motor, positive is counterclockwise, in the range [-1.0, 1.0]
     */
    public void move(double speed) {
        mTurret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mTurret.setPower(speed);
    }

    /**
     * Turns the turret to the target encoder position.
     * @param position the target encoder position
     * @param power the power of the motor, in the range [0.0, 1.0]
     */
    public void move(int position, double power) {
        mTurret.setTargetPosition(position);
        mTurret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mTurret.setPower(power);
    }
    /**
     * Returns if the turret is currently moving to a position.
     * @return true if the turret is busy, false otherwise
     */
    public boolean isBusy() {
        return mTurret.isBusy();
    }

    /**
     * Soft stops the turret.
     */
    public void stop() {
        move(0.0);
    }

    /**
     * Resets the turret encoder.
     */
    public void resetEncoder() {
        mTurret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the output telemetry
     */
    public void debug(Telemetry telemetry) {
        telemetry.addData("Turret position", mTurret.getCurrentPosition());
    }
}
