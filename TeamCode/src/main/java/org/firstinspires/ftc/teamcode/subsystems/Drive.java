package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {

    private DcMotor lFDrive;
    private DcMotorSimple lBDrive;
    private DcMotor rFDrive;
    private DcMotorSimple rBDrive;

    public Drive(HardwareMap hardwareMap) {
        lFDrive  = hardwareMap.get(DcMotor.class, "LFDrive");
        lBDrive  = hardwareMap.get(DcMotorSimple.class, "LBDrive");
        rFDrive  = hardwareMap.get(DcMotor.class, "RFDrive");
        rBDrive  = hardwareMap.get(DcMotorSimple.class, "RBDrive");

        lFDrive.resetDeviceConfigurationForOpMode();
        lBDrive.resetDeviceConfigurationForOpMode();
        rFDrive.resetDeviceConfigurationForOpMode();
        rBDrive.resetDeviceConfigurationForOpMode();

        lFDrive.setDirection(DcMotor.Direction.REVERSE);
        rBDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void move(double lSpeed, double rSpeed) {
        lFDrive.setPower(lSpeed);
        lBDrive.setPower(lSpeed);
        rFDrive.setPower(rSpeed);
        rBDrive.setPower(rSpeed);
    }

    public void strafe(double speed) {
        // positive speed moves left
        lFDrive.setPower(speed);
        lBDrive.setPower(-speed);
        rFDrive.setPower(speed);
        rBDrive.setPower(-speed);
    }

    public void stop() {
        lFDrive.setPower(0);
        lBDrive.setPower(0);
        rFDrive.setPower(0);
        rBDrive.setPower(0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("LF position: ", lFDrive.getCurrentPosition());
        telemetry.addData("RF position: ", rFDrive.getCurrentPosition());
    }

}
