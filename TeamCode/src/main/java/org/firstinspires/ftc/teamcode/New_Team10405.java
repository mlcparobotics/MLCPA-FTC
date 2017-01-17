/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Template: Linear OpMode", group="Linear Opmode")  // @Autonomous(...) is the other common choice

public class New_Team10405 extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorLeft = null;
    private DcMotor motorRight = null;
    private DcMotor beaconMotor = null;
    private DcMotor batMotor = null;
    private com.qualcomm.robotcore.hardware.TouchSensor TouchSensor = null;
    private boolean isShootersOn = false;

    boolean flip = false;
    float speed = 1;


    // DcMotor leftMotor = null;
    // DcMotor rightMotor = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        motorLeft  = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        beaconMotor = hardwareMap.dcMotor.get("beaconMotor");
        batMotor = hardwareMap.dcMotor.get("batMotor");
        TouchSensor= hardwareMap.touchSensor.get("TouchSensor");
        // leftMotor  = hardwareMap.dcMotor.get("left_drive");
        // rightMotor = hardwareMap.dcMotor.get("right_drive");

        // eg: Set the drive motor directions:
        motorLeft.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        batMotor.setDirection(DcMotor.Direction.REVERSE);
        beaconMotor.setDirection(DcMotor.Direction.REVERSE);
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            if(gamepad1.dpad_up){
                flip = true;
            }

            if (gamepad1.dpad_down){
                flip = false;
            }

            if(flip){
                motorRight.setPower(-gamepad1.left_stick_y/speed);
                motorLeft.setPower(-gamepad1.right_stick_y/speed);
            }

            else{
                motorLeft.setPower(gamepad1.left_stick_y/speed);
                motorRight.setPower(gamepad1.right_stick_y/speed);
            }

            if(gamepad1.x){
                speed = 4;
            }

            if (gamepad1.y){
                speed = 1;
            }

            if (gamepad1.a){
                motorLeft.setPower(-1);
                motorRight.setPower(-1);
                sleep(250);

                motorLeft.setPower(1);
                motorRight.setPower(1);
                sleep(250);

            }



       /*
        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
        if(gamepad1.left_bumper && gamepad1.left_trigger == 0)
        {
            motorLeft.setPower(-1);
        }

        else if(gamepad1.left_trigger > 0 && gamepad1.left_bumper == false){
            motorLeft.setPower(gamepad1.left_trigger);
        }

        else{
            motorLeft.setPower(0);
        }

        if(gamepad1.right_bumper && gamepad1.right_trigger == 0)
        {
            motorRight.setPower(-1);
        }

        else if(gamepad1.right_trigger > 0 && gamepad1.right_bumper == false){
            motorRight.setPower(gamepad1.right_trigger);
        }

        else{
            motorRight.setPower(0);
        }

        */


            //Attempt to move batMotor
            if(gamepad2.y) {
                batMotor.setPower(1);
            }
            else if(gamepad2.x){
                batMotor.setPower(-1);
            }
            else {
                batMotor.setPower(0);


                // set beaconMotor forward
            }
            if(gamepad2.a){
                beaconMotor.setPower(.6);
            }
            else if(gamepad2.b) {
                beaconMotor.setPower(-.6);
            }
            else {
                beaconMotor.setPower(0);
            }


            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // leftMotor.setPower(-gamepad1.left_stick_y);
            // rightMotor.setPower(-gamepad1.right_stick_y);
        }
    }
}
