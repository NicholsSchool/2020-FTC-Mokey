package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final double kTriggerThreshold = 0.5;

    public static final double kSlowStrafeSpeed = 0.5;
    public static final double kFastStrafeSpeed = 1.0;
    public static final int kTicksPerDriveRotation = 1120;
    public static final double kDriveWheelDiameter = 3.92;
    public static final double kTicksPerInch = kTicksPerDriveRotation / (Math.PI * kDriveWheelDiameter);

    public static final double kElevatorUpSpeed = 1.0;
    public static final double kElevatorDownSpeed = -0.5;
    public static final int kElevatorMax = 170;
    public static final int kElevatorMin = -330;

    public static final double kTurretSpeed = 0.4;
    public static final int kTurretMax = 260;
    public static final int kTurretMin = -800;

    public static final double kFolderUpSpeed = 1.0;
    public static final double kFolderDownSpeed = -1.0;

    public static final int kEncoderTargetMargin = 50;
    public static final double kAngleTargetMargin = 5.0;

    public static final double kGrabberClampSpeed = -1.0;
    public static final double kGrabberReleaseSpeed = 0.5;
}
