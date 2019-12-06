package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Elevator {

    private DcMotor mElevator;

    public Elevator(HardwareMap hardwareMap) {
        mElevator = hardwareMap.get(DcMotor.class, "Elevator");
        mElevator.resetDeviceConfigurationForOpMode();

        mElevator.setDirection(DcMotor.Direction.REVERSE);
        mElevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoder();
    }

    public void move(double speed) {
        mElevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mElevator.setPower(speed);
    }

    public void move(int position, double power) {
        mElevator.setTargetPosition(position);
        mElevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mElevator.setPower(power);
    }

    public boolean isBusy() {
        return mElevator.isBusy();
    }

    public void resetEncoder() {
        mElevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void stop() {
        move(0.0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Elevator position", mElevator.getCurrentPosition());
    }
}
