package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Turret {

    private DcMotor turret;

    private int min;
    private int max;

    public Turret(HardwareMap hardwareMap) {
        turret = hardwareMap.get(DcMotor.class, "Turret");
        turret.resetDeviceConfigurationForOpMode();
        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        min = Constants.kTurretMin;
        max = Constants.kTurretMax;

        resetEncoder();
    }

    public void move(double speed) {
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if(turret.getCurrentPosition() < max && speed > 0) {
            turret.setPower(speed);
        } else if(turret.getCurrentPosition() > min && speed < 0) {
            turret.setPower(speed);
        } else {
            turret.setPower(0);
        }
    }

    public void position(int position, double power) {
        turret.setTargetPosition(position);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turret.setPower(power);
    }

    public void setExtremes(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isBusy() {
        return turret.isBusy();
    }

    public void stop() {
        move(0.0);
    }

    public void resetEncoder() {
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Turret position", turret.getCurrentPosition());
    }
}
