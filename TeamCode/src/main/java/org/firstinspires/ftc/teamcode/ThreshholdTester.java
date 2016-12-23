package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Mark on 12/1/2016.
 */
@TeleOp(name="Threshhold", group="Exercises")
public class ThreshholdTester extends LinearOpMode {

    ColorSensor lineColor;
    Servo beaconServo;
    double beaconPosition;
    String programVersion;

    public void runOpMode() throws InterruptedException {
        //lineColor = hardwareMap.colorSensor.get("lineColor");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        programVersion = "MRB_12_12_16_5";
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + programVersion);
        telemetry.update();

        waitForStart();
        /*
        lineColor.enableLed(true);
        lineColor.enableLed(false);
        lineColor.enableLed(true);
*/

        while (opModeIsActive())
        {
            if (gamepad1.right_bumper) {
                beaconPosition = beaconServo.getPosition()+0.1;
            }
            if (gamepad1.left_bumper)
            {
                beaconPosition = beaconServo.getPosition()-0.1;
            }
            beaconServo.setPosition(beaconPosition);
            //lineColor.enableLed(true);
            telemetry.addData("Mode", "Running");
            telemetry.addData("Postiton", beaconServo.getPosition() + beaconPosition);
            //telemetry.addLine("blue" + lineColor.blue());
            telemetry.update();
            idle();
        }
    }

}
