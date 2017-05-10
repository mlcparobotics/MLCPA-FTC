package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by ANDRES SALAS
 */
@TeleOp(name = "TeleopMode_ME")
public class TeleoOpmode extends LinearOpMode {
    private CrabBot_Hardware CrabBot = new CrabBot_Hardware();
//          private CrabBot_Vuforia Vuforia = new CrabBot_Vuforia();

    @Override
    public void runOpMode()throws InterruptedException{
        CrabBot.Standard_Hardware_declaration(this);
//        Vuforia.VuforiaJunk(this, CrabBot);
//        Vuforia.init_track();
/*
        VuforiaLocalizer.Parameters Maram = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        Maram.vuforiaLicenseKey = "AbUZtgH/////AAAAGXeLnnQrrU5+qrxK0IAwAalZnkNmyO94eGoqcSeXitwa/YkBzM/9YPiM/m1H9mhBrP89G5amGmzbfsDfNu3lVvp97tzix+3H3TMUuODMQj6PdkNciCEDaud+KQR+hQeiupqz6n9s0RMf/MHyefUUGvnF7Ltd1mXFSAqaObvFgWl6DFi0GtLhz5Fa+DZoPrT4n8BkoiGkY4psDR5OEu+yYGbLi9SGB75dlMGhYbQYYE2yiACv0YjZstfQwoxWwKCq/U6ibd2s16wMfBEOVrBVInH6DS4q0TUFQysZ3NRKL8j0mlsbAdVmRMWskeXs4v7GI89T/n0+wmn3o4dxBxgpL+h+6QtJOAFyQlRcCuXkX4o3";
        VuforiaLocalizer.CameraDirection Marat = VuforiaLocalizer.CameraDirection.BACK;
*/
        while (!isStarted()){

            telemetry.addData("Press Play To Begin", "><");
            telemetry.update();


        while (opModeIsActive()){
            telemetry.addData("HAVE FUN!", "><");
            /*
            if (gamepad1.b){
                telemetry.addData("Camera View", Maram.cameraMonitorFeedback);
            }else {
                telemetry.clearAll();
            }
            */
            telemetry.update();
            CrabBot.TeleopDrive();
        }
        }
    }
}