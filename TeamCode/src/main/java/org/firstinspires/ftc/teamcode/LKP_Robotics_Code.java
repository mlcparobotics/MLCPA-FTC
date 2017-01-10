package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Laura on 12/10/2016.
 */

@TeleOp(name = "Lkp Program")
public class LKP_Robotics_Code extends LinearOpMode {

    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor liftMotor;
    private DcMotor liftMotor2;

    private Servo arms;

    private static final double open = 0.0;
    private static final double closed = 0.8;

    @Override
    public void runOpMode() throws InterruptedException{

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        liftMotor = hardwareMap.dcMotor.get("liftMotor");
        liftMotor2 = hardwareMap.dcMotor.get("liftMotor2");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        arms = hardwareMap.servo.get("arms");

        arms.setPosition(closed);

        waitForStart();

        while(opModeIsActive()){

            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            if(gamepad1.dpad_up){
                liftMotor.setPower(1);
                liftMotor2.setPower(1);
            }

            else if(gamepad1.dpad_down){
                liftMotor.setPower(-1);
                liftMotor2.setPower(-1);
            }

            else{
                liftMotor.setPower(0);
                liftMotor2.setPower(0);
            }


            if(gamepad1.a) {

                arms.setPosition(open);
            }
            if(gamepad1.b){
                arms.setPosition(closed);
            }

            idle();
        }
    }

}
