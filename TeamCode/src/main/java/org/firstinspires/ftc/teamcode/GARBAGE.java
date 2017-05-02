package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

//CREATED BY ANDRES SALAS

/**
 * Created by ANDRES SALAS
 */
@Autonomous(name = "DO_NOT_PICK", group = "Image Recognition Software")
public class GARBAGE extends LinearOpMode {
    OpenGLMatrix LastKnownLocation;
    OpenGLMatrix PhoneLocation;
    private VuforiaLocalizer vuforia;
    OpenGLMatrix RobotLocationTransform;
    //Attempt at creating a constructor(?I think thats what they're called) so I could call this when needed
    //static final VuforiaLocalizer.CameraDirection CAMERDIREC = VuforiaLocalizer.CameraDirection.BACK;
    public static final String TAG = "vuforia";
    private static final int MAX_TARGETS = 4;
    CrabBot_Hardware CrabBot = new CrabBot_Hardware();


    @Override
    public void runOpMode() throws InterruptedException{
        waitForStart();
        CrabBot.Standard_Hardware_declaration(this);

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();                             // OR... Use this line to improve performance


        parameters.vuforiaLicenseKey = "Afbu2Uv/////AAAAGVouNdSAD0P8la+sq37vCdQ6uLVH8NWrBLnfZ1R5rObJQpVVHJzqvIgMZO5gTqXG6DYJZcgwtSVZXU2g20FAJobxCog9Wc5vtqgJJmrsJ0NOABRbi9vy4Y9IzBVfaDoRsQTmjxxFf62Z9slttsb44KopGpVGTQ83iHnTo/wDvnZBWRhmckG6IKuqkbRYCFD+w1hHvVLuDoIYLgfpa1Rw1Pc7rszP/CDzUfeO9KwodFpEsfZHIZI8KHIYzfRIOhg1Tg0T4eRsLCO8s9vfZd6vfTuUA/sZkID3N7BsrlLaL6vUqheGPvsbPuQQsMqgPNYTqbhvv3KI/SR5WxUaccuVHnpVMhAjkdpruWVliCCZqp1t";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.useExtendedTracking = false;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        while (opModeIsActive()){
            init();
        }




/*
        //Declaring all necessary info to setup VUFORIA
        VuforiaLocalizer.Parameters param = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        param.vuforiaLicenseKey = "AZXXuF7/////AAAAGdXla+m4ckJis+Gy9oos9r5FmMyN6GPlOzKkggKFDcq8p+SsVLCNGdKzBNrdImQG0eB5Xhn9tdRpDE7VzZdWxR9J36hQgS4ly14d7/lf61ruII3kzsAB7tLYluqaMFjQRUE/FfvwMR8ZEj1R3aMPhu0mL/nBfJ/4W2cGyYeAZ8VCAJI/p79ZuqKxm+Bg3SegfRlksvw8yz5gOgOVZbB2+0rmolVcO4a4ULtJ119mLFRCgD0auZQE1KyJHOX3KILX54lZiAy1+j3nElf6bIfUBqpE4h1DyohsDlopwr4y/sVzceP7Sea6kTCryBiNYqRnRXFu3TzDGf7XCQZ+DgdfH3c5QOeMK1BIjYZYGRdcqP9+";
        VuforiaLocalizer.CameraDirection CamDirec = VuforiaLocalizer.CameraDirection.BACK;
        this.Vuforia = ClassFactory.createVuforiaLocalizer(param);
        VuforiaTrackables Beacon_Targets = this.Vuforia.loadTrackablesFromAsset("FTC_2016-17");
        VuforiaLocalizer vufroiaLocalizer = ClassFactory.createVuforiaLocalizer(param);
        param.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

//        VuforiaTrackableDefaultListener Wheels = (VuforiaTrackableDefaultListener) Beacon_Targets.get(0).getListener();
//        VuforiaTrackableDefaultListener Legos = (VuforiaTrackableDefaultListener) Beacon_Targets.get(2).getListener();

        //Load the targets from the asset folder
        VuforiaTrackable Wheels = Beacon_Targets.get(0);
        Wheels.setName("Wheels");
        VuforiaTrackable Legos = Beacon_Targets.get(2);
        Legos.setName("Legos");

        ((VuforiaTrackableDefaultListener)Wheels.getListener()).setPhoneInformation(PhoneLocation, param.cameraDirection);
        ((VuforiaTrackableDefaultListener)Legos.getListener()).setPhoneInformation(PhoneLocation, param.cameraDirection);


        float mmPerInch        = 25.4f;
        float mmBotWidth       = 18 * mmPerInch;
        float mmFTCFieldWidth  = (12*12 - 2) * mmPerInch;
        //SHOULD Track the "Wheels Target, and display the information in a human readable format
        // /thanks to the public string format located at the bottom of the code
        OpenGLMatrix WheelsTargetOnField = OpenGLMatrix.translation(-mmFTCFieldWidth/2,0,0).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0,0,0));
        Wheels.setLocation(WheelsTargetOnField);
        RobotLog.ii(TAG, "Wheels = $s", Format(WheelsTargetOnField));
        OpenGLMatrix LegosLocationOnField = OpenGLMatrix.translation(-mmFTCFieldWidth/2,0,0).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0,0,0));
        Legos.setLocation(LegosLocationOnField);
        RobotLog.ii(TAG, "Legos = $s", Format(LegosLocationOnField));
        param.useExtendedTracking = false;

        final float PHONE_FROM_CENTER_OF_ROBOT = 85;
        final float PHONE_FROM_GROUND = 85;
        final float PHONE_OFF_FROM_CENTERLINE_OF_ROBOT = 0;



        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix.translation(PHONE_FROM_CENTER_OF_ROBOT, PHONE_FROM_GROUND, PHONE_OFF_FROM_CENTERLINE_OF_ROBOT).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES, 0, 0, 0));
        RobotLog.ii(TAG, "phone=%s", Format(phoneLocationOnRobot));
        //Failed Attempt, didn't work due to some unresolved issue
        //OpenGLMatrix PhoneLocationOnRobot = OpenGLMatrix.translation(PHONE_FROM_CENTER, PHONE_FROM_GROUND, PHONE_OFF_FROM_CENTER).multiplied(Orientation.getRotationMatrix(AxesReference.EXTRINSIC,AxesOrder.XYZ, AngleUnit.DEGREES, ));
        //Activate data necessary to identify targets
        //int X = 0;
        Beacon_Targets.activate();

        FrontLeft.setPower(.25);
        FrontRight.setPower(.25);
        BackLeft.setPower(.25);
        BackRight.setPower(.25);
*/


/*
        while (opModeIsActive()){
            idle();
            for (VuforiaTrackable Targets : Beacon_Targets) {
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) Targets.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    LastKnownLocation = robotLocationTransform;
                }


            }
            //Provides Feedback over robots Location
            if (LastKnownLocation != null){
                RobotLog.vv(TAG, "robot=%s", Format(LastKnownLocation));
                telemetry.addData("Position=", Format(LastKnownLocation));
                telemetry.update();
            }else {
                telemetry.addData("Position=", "Unknown");
                telemetry.update();
            }
*/



        }

    public String Format(OpenGLMatrix Format){
        return Format.formatAsTransform();
    }
}
