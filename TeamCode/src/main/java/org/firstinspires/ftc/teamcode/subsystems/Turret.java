package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Turret {

    private DcMotor mTurret;

    public Turret(HardwareMap hardwareMap) {
        mTurret = hardwareMap.get(DcMotor.class, "Turret");
        mTurret.resetDeviceConfigurationForOpMode();
        mTurret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoder();
    }

    public void move(double speed) {
        mTurret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        mTurret.setPower(speed);
    }

    public void position(int position, double power) {
        mTurret.setTargetPosition(position);
        mTurret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        mTurret.setPower(power);
    }

    public boolean isBusy() {
        return mTurret.isBusy();
    }

    public void stop() {
        move(0.0);
    }

    public void resetEncoder() {
        mTurret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Turret position", mTurret.getCurrentPosition());
    }
}
