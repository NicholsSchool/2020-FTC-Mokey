/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot;



@Autonomous(name="Red Foundation Close Old")
@Disabled
public class RedFoundationCloseOldAuto extends LinearOpMode {



    @Override
    public void runOpMode() {
        Robot.init(hardwareMap);

        waitForStart();

        // Back up a foot
        Robot.drive.resetEncoders();
        int ticks = (int)(-6 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoSpeed) && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Turn
        Robot.imu.reset();
        while(Robot.drive.turn(-90.0, Constants.kAutoSpeed) && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Unfold
        while(!Robot.folderUp.isPressed() && opModeIsActive()) {
            Robot.folder.move(Constants.kFolderUpSpeed);
        }

        Robot.elevator.move(Constants.kElevatorBelowJoint, Constants.kElevatorDownSpeed);
        while(Robot.elevator.isBusy() && opModeIsActive()) {

        }

        while(!Robot.folderDown.isPressed() && opModeIsActive()) {
            Robot.folder.move(Constants.kFolderDownSpeed);
        }

        Robot.stop();
        //

        // Back up to foundation
        Robot.drive.resetEncoders();
        ticks = (int)(-70 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoSpeed) && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Turn towards foundation
        Robot.imu.reset();
        while(Robot.drive.turn(-90.0, Constants.kAutoSpeed) && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Prepare to grab foundation
        while(!Robot.folderUp.isPressed() && opModeIsActive()) {
            Robot.folder.move(Constants.kFolderUpSpeed);
        }

        Robot.elevator.move(0, Constants.kElevatorUpSpeed);
        while(Robot.elevator.isBusy() && opModeIsActive()) {

        }

        Robot.stop();
        //

        // Move towards foundation
        Robot.drive.resetEncoders();
        ticks = (int)(24 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoSpeed) && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Elevator down
        Robot.elevator.move(-220, Constants.kElevatorDownSpeed);
        while(Robot.elevator.isBusy() && opModeIsActive()) {

        }
        Robot.stop();
        //

        // Open grabber
        Robot.grabber.move(0.5);
        sleep(1000);
        Robot.stop();
        //

        // Drag foundation
        Robot.elevator.move(Constants.kElevatorDownSpeed);
        sleep(500);
        Robot.stop();

        Robot.drive.resetEncoders();
        ticks = (int)(-38 * Constants.kTicksPerInch);
        while(Robot.drive.move(ticks, Constants.kAutoSpeed) && opModeIsActive()) {
            Robot.elevator.move(Constants.kElevatorDownSpeed);
        }
        Robot.stop();
        //
    }

}
