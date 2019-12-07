package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Elevator controls the elevator belt on Mokey.
 */
public class Elevator {

    private DcMotor mElevator;

    /**
     * Creates an elevator with the default state at the beginning of an OpMode.
     * @param hardwareMap the hardware map of Mokey
     */
    public Elevator(HardwareMap hardwareMap) {
        mElevator = hardwareMap.get(DcMotor.class, "Elevator");
        mElevator.resetDeviceConfigurationForOpMode();

        mElevator.setDirection(DcMotor.Direction.REVERSE);
        mElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoder();
    }

    /**
     * Moves the elevator belt.
     * @param speed the speed of the motor, in the range [-1.0, 1.0]
     */
    public void move(double speed) {
        mElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mElevator.setPower(speed);
    }

    /**
     * Moves the elevator to a target position with encoders.
     * @param position the target encoder position
     * @param power the power of the motor, in the range [0.0, 1.0]
     */
    public void move(int position, double power) {
        mElevator.setTargetPosition(position);
        mElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mElevator.setPower(power);
    }

    /**
     * Returns if the elevator is currently moving to a position.
     * @return true if the elevator is busy, false otherwise
     */
    public boolean isBusy() {
        return mElevator.isBusy();
    }

    /**
     * Resets the elevator encoder.
     */
    public void resetEncoder() {
        mElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Soft stops the elevator.
     */
    public void stop() {
        move(0.0);
    }

    /**
     * Adds debug values to the telemetry.
     * @param telemetry the current telemetry
     */
    public void debug(Telemetry telemetry) {
        telemetry.addData("Elevator position", mElevator.getCurrentPosition());
    }
}
