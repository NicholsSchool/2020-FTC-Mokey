package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Turret {

    private DcMotor turret;

    public Turret(HardwareMap hardwareMap) {
        turret = hardwareMap.get(DcMotor.class, "Turret");
        turret.resetDeviceConfigurationForOpMode();
    }

    public void move(double speed) {
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        turret.setPower(speed);
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

    public void debug(Telemetry telemetry) {
        telemetry.addData("Turret position", turret.getCurrentPosition());
        telemetry.update();
    }
}
