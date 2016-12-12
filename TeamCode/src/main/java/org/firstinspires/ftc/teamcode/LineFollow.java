package org.firstinspires.ftc.teamcode;

/**
 * Created by Mark on 12/5/2016.
 */

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;
@Autonomous(name="BeaconWIP", group="Exercises")
public class LineFollow extends LinearOpMode {

    DcMotor rightMotor, leftMotor;
    ColorSensor lineColor;
    String programVersion;

    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        lineColor = hardwareMap.colorSensor.get("line_color");
        programVersion = "MRB_12_5_16_1";

        telemetry.addData("Mode:", " Waiting");
        telemetry.addLine(programVersion);
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
        {
            if (lineColor.blue() >= 50) {
                leftMotor.setPower(-0.25);
                rightMotor.setPower(-0.1);
            }
            else
            {
                leftMotor.setPower(-0.1);
                rightMotor.setPower(-0.25);
            }
            telemetry.addData("Mode:", " Running");
            telemetry.addData("Color:", + lineColor.blue());
            telemetry.addLine(programVersion);
            telemetry.update();
        }
    }

}
