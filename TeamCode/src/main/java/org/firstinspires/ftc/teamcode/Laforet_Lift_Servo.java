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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Laforet Lift Servo", group="Iterative Opmode")  // @Autonomous(...) is the other common choice

public class Laforet_Lift_Servo extends OpMode
{
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor Lift;


    private Servo liftArmLeft;
    private Servo liftArmRight;
    private Servo lance;

    private double LANCE_EXTENDED_POSITION = 0.0;
    private double LANCE_RETRACTED_POSITION = 0.6;
    private double ServoUp = .5;
    private double ServoDown = .999999;



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        Lift = hardwareMap.dcMotor.get("Lift");
        lance = hardwareMap.servo.get("lance");
        liftArmLeft = hardwareMap.servo.get("liftArmLeft");
        liftArmRight = hardwareMap.servo.get("liftArmRight");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        lance.setPosition(LANCE_RETRACTED_POSITION);
        liftArmRight.setPosition(ServoUp);
        liftArmLeft.setPosition(1 - ServoUp);

        // eg: Set the drive motor directions:
        // Reverse the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        //  rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        // telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString() + "  liftArmLeft: " + liftArmLeft.getPosition() +  "  liftArmRight: " + liftArmRight.getPosition() +  "  ServoDown: " + ServoDown);

        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
        // leftMotor.setPower(-gamepad1.left_stick_y);
        // rightMotor.setPower(-gamepad1.right_stick_y);

        motorLeft.setPower(-gamepad1.left_stick_y);
        motorRight.setPower(-gamepad1.right_stick_y);


        if (gamepad1.a) {
            lance.setPosition(LANCE_EXTENDED_POSITION);
        }
        if (gamepad1.x) {
            lance.setPosition(LANCE_RETRACTED_POSITION);
        }

        if (gamepad1.right_trigger > 0) {
            Lift.setPower(-1);
        } else if (gamepad1.left_trigger > 0) {
            Lift.setPower(1);
        } else {
            Lift.setPower(0);
        }

        if (gamepad1.y) {
            liftArmRight.setPosition(ServoUp);
            liftArmLeft.setPosition(1 - ServoUp);
        }

        if (gamepad1.b) {
            liftArmLeft.setPosition(1 - ServoDown);
            liftArmRight.setPosition(ServoDown);
        }

        if (gamepad1.left_bumper && ServoDown > .02) {
            ServoDown = ServoDown - .01;
        }

        if (gamepad1.right_bumper && ServoDown < .98) {
            ServoDown = ServoDown + .01;
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
