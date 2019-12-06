package org.firstinspires.ftc.teamcode;

public class Constants {
    // Controls
    public static final double kTriggerThreshold = 0.5;

    // Drive
    public static final double kSlowStrafeSpeed = 0.5;
    public static final double kFastStrafeSpeed = 1.0;
    public static final double kDriveAutoSpeed = 0.75;
    public static final double kDriveEqualizer = 0.95;
    public static final double kTurnEqualizer = 0.65;
    public static final int kTicksPerDriveRotation = 1120;
    public static final double kDriveWheelDiameter = 3.92;
    public static final double kTicksPerInch = kTicksPerDriveRotation / (Math.PI * kDriveWheelDiameter);


    // Elevator
    public static final double kElevatorUpSpeed = 1.0;
    public static final double kElevatorDownSpeed = -0.5;
    public static final int kElevatorMax = 170;
    public static final int kElevatorMin = -320;
    public static final int kElevatorBelowJoint = -280;


    // Turret
    public static final double kTurretSpeed = 0.4;
    public static final int kTurretMax = 260;
    public static final int kTurretMin = -800;


    // Turret
    public static final double kFolderUpSpeed = 1.0;
    public static final double kFolderDownSpeed = -1.0;


    // Grabber
    public static final double kGrabberClampAutoSpeed = -0.5;
    public static final double kGrabberReleaseAutoSpeed = 0.5;


    // Sensors
    public static final int kEncoderTargetMargin = 50;
    public static final double kAngleTargetMargin = 10.0;
}
