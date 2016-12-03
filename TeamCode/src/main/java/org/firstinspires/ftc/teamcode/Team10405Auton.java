/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.
This program capable of performing the following tasks
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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@Autonomous(name="Template: Linear OpMode", group="Linear Opmode")  // @Autonomous(...) is the other common choice

public class Team10405Auton extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor motorLeft = null;
    DcMotor motorRight = null;
    DcMotor beaconMotor = null;

    public void Stop(){
        motorLeft.setPower(0);
        motorRight.setPower(0);
        sleep(10);
    }

    public void FWD(){
        motorLeft.setPower(1);
        motorRight.setPower(1);
        sleep(1000);

        motorLeft.setPower(0);
        motorRight.setPower(0);
        sleep(1000);
    }
    public void Forward(double speed, int duration){
        motorLeft.setPower(speed);
        motorRight.setPower(speed);
        sleep(duration);


        Stop();
    }
    public void Reverse(int duration){
        motorLeft.setPower(-1);
        motorRight.setPower(-1);
        sleep(duration);

        Stop();

    }
    public void Turnleft(int duration){
        motorLeft.setPower(-1);
        motorRight.setPower(1);
        sleep(duration);

        Stop();
    }
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

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        // leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            FWD();


/*
            Forward(1, 2000);
         //Unnecessary, Just use function for forward and input negative numbers
            Reverse(1000);
         // Might work, supposed to turn left for half a second
            Turnleft(500);
*/

/*
        // Forward one second
            motorLeft.setPower(.5);
            motorRight.setPower(.5);
            sleep(1000);

            // Stop for 10 milliseconds
            motorLeft.setPower(0);
            motorRight.setPower(0);
            sleep(10);

            //Turn left one second
            motorLeft.setPower(0);
            motorRight.setPower(1);
            sleep(1000);

                //Forward for one second
            motorLeft.setPower(1);
            motorRight.setPower(1);
            sleep(1000);

            //Stop for one second
            motorLeft.setPower(0);
            motorRight.setPower(0);
            sleep(1000);
*/

        }
    }
}
