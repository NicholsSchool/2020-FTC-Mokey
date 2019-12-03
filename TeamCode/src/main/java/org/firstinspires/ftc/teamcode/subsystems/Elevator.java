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
    }

    public void move(double speed) {
        if(elevator.getCurrentPosition() < Constants.kElevatorMax && speed > 0) {
            elevator.setPower(speed);
        }
        if(elevator.getCurrentPosition() > Constants.kElevatorMin && speed < 0) {
            elevator.setPower(speed);
        }
    }

    public void stop() {
        elevator.setPower(0);
    }

    public void debug(Telemetry telemetry) {
        telemetry.addData("Elevator position: ", elevator.getCurrentPosition());
    }
}
