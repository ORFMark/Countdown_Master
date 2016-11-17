package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="FarAuto", group="Exercises")

/**
 * Created by Mark on 11/17/2016.
 */

public class FarAuto extends LinearOpMode
    {
        DcMotor leftMotor;
        DcMotor rightMotor;
        Servo beaconServo;
        ColorSensor beaconColor;
        String programVersion;
        double beaconPosition;

        @Override
        public void runOpMode() throws InterruptedException
        {
            leftMotor = hardwareMap.dcMotor.get("left_motor");
            rightMotor = hardwareMap.dcMotor.get("right_motor");
            beaconServo = hardwareMap.servo.get("beacon_servo");
            rightMotor.setDirection(DcMotor.Direction.REVERSE);
            programVersion = "MRB_11_17_16_2";

            // reset encoder count kept by motor.
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // set motor to run to target encoder position and stop with brakes on.
            // RUN_WITH_ENCODER will stop with coast.
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();

            telemetry.addData("Mode", "running");
            telemetry.update();

            // set left motor to run for 5000 encoder counts.

            leftMotor.setTargetPosition(-696);
            rightMotor.setTargetPosition(-696);

            // set both motors to 25% power. Movement will start.

            leftMotor.setPower(-0.25);
            rightMotor.setPower(-0.25);

            // wait while opmode is active and left motor is busy running to position.

            while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addLine(programVersion);
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftMotor.setTargetPosition(-1052);
            rightMotor.setTargetPosition(-696);
            leftMotor.setPower(-0.25);
            rightMotor.setPower(-0.25);

            while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addLine(programVersion);
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftMotor.setTargetPosition(-4752);
            rightMotor.setTargetPosition(-4496);
            leftMotor.setPower(-0.25);
            rightMotor.setPower(-0.25);

            while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addLine(programVersion);
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftMotor.setTargetPosition(-4852);
            rightMotor.setTargetPosition(-4896);
            leftMotor.setPower(-0.1);
            rightMotor.setPower(-0.2);

            while (opModeIsActive() && rightMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addLine(programVersion);
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            if (beaconColor.red() <= beaconColor.blue())
            {
                beaconPosition = 0.5;
            }
            else if (beaconColor.red() >= beaconColor.blue())
            {
                leftMotor.setTargetPosition(-4452);
                rightMotor.setTargetPosition(-4496);
                leftMotor.setPower(.2);
                rightMotor.setPower(.2);
                while (opModeIsActive() && leftMotor.isBusy())
                {
                    telemetry.addLine(programVersion);
                    telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                    telemetry.update();
                    idle();
                }

            }
            while (opModeIsActive())
            {
                telemetry.addData("encoder-end", leftMotor.getCurrentPosition());
                telemetry.update();
                idle();
            }
        }
    }

