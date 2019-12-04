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
        turret.setPower(speed);
    }

    public void stop() {
        turret.setPower(0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Turret position", turret.getCurrentPosition());
        telemetry.update();
    }
}
