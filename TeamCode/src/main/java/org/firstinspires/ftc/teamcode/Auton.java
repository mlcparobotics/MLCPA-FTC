package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by ANDRES SALAS
 */
@Autonomous(name = "Autonomous")
public class Auton extends LinearOpMode {
    CrabBot_Hardware CrabBot = new CrabBot_Hardware();
    ElapsedTime runtime = new ElapsedTime();
//    CrabBot_Vuforia Vuforia = new CrabBot_Vuforia();

    private void TurnLeft(double x, int i){
        CrabBot.FrontLeft.setPower(x);
        CrabBot.BackLeft.setPower(x);
        sleep(i);
    }
    private void TurnRight(double y, int a){
        CrabBot.BackRight.setPower(y);
        CrabBot.FrontRight.setPower(y);
        sleep(a);

    }
    private void Forward_Backward(double m, int k){
        CrabBot.FrontRight.setPower(m);
        CrabBot.BackRight.setPower(m);
        CrabBot.BackLeft.setPower(m);
        CrabBot.FrontLeft.setPower(m);
        sleep(k);
    }

    @Override
    public void runOpMode(){

        CrabBot.Standard_Hardware_declaration(this);


        telemetry.addData("Press Play To Start", "><");
        telemetry.update();
        waitForStart();
        runtime.reset();

        while (opModeIsActive()){

            telemetry.addData("HAVE FUN!", "><");

            CrabBot.CenterMotor.setPower(-.6);
            sleep(500);
            TurnLeft(.5, 500);
            Forward_Backward(.7, 1500);
            sleep(300000);


        }

    }
}
