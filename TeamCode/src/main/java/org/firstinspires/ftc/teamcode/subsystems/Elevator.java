package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Elevator {

    private DcMotor elevator;

    public Elevator(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "Elevator");
        elevator.resetDeviceConfigurationForOpMode();

        elevator.setDirection(DcMotor.Direction.REVERSE);

        resetEncoder();
    }

    public void move(double speed) {
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if(elevator.getCurrentPosition() < Constants.kElevatorMax && speed > 0) {
            elevator.setPower(speed);
        } else if(elevator.getCurrentPosition() > Constants.kElevatorMin && speed < 0) {
            elevator.setPower(speed);
        } else {
            elevator.setPower(0);
        }
    }

    public void move(int position, double power) {
        elevator.setTargetPosition(position);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevator.setPower(power);
    }

    public boolean isBusy() {
        return elevator.isBusy();
    }

    public void resetEncoder() {
        elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void stop() {
        move(0.0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Elevator position", elevator.getCurrentPosition());
    }
}
