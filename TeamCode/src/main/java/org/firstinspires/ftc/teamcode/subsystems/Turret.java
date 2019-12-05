package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Turret {

    private DcMotor turret;

    public Turret(HardwareMap hardwareMap) {
        turret = hardwareMap.get(DcMotor.class, "Turret");
        turret.resetDeviceConfigurationForOpMode();

        resetEncoder();
    }

    public void move(double speed) {
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if(turret.getCurrentPosition() < Constants.kTurretMax && speed > 0) {
            turret.setPower(speed);
        } else if(turret.getCurrentPosition() > Constants.kTurretMin && speed < 0) {
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
