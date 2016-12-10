package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by HEROESST on 11/11/2016.
 * Code recreated by Laura K. Prier
 */

@TeleOp(name = "MLCP_10405_LKP", group = "prierPractice")
public class MLCP_10405_LKP extends LinearOpMode {

    private DcMotor motorLeft = null;
    private DcMotor motorRight;
    private DcMotor beaconMotor;
    private DcMotor batMotor;
    double speed = 4;

    @Override
    public void runOpMode() throws InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        beaconMotor = hardwareMap.dcMotor.get("beaconMotor");
        batMotor= hardwareMap.dcMotor.get("batMotor");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        beaconMotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive()){

            motorLeft.setPower(-gamepad1.left_stick_y/speed);
            motorRight.setPower(-gamepad1.right_stick_y/speed);
            beaconMotor.setPower(gamepad1.right_trigger);
            batMotor. setPower(gamepad1. left_trigger);

            if(gamepad1.a && speed > 1){

                    speed = speed - 0.5;
            }

            if(gamepad1.x){
                speed = speed + .5;
            }



            idle();
        }
    }
}
