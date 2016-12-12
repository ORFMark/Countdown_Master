package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AutoShot", group="Exercises")


public class AutoShot extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor shooterMotor, intakeMotor1, intakeMotor2;
    Servo beaconServo, doorServo;
    ColorSensor beaconColor;
    String programVersion;
    float intakePower;
    double beaconPosition, doorPosition;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        intakeMotor1 = hardwareMap.dcMotor.get("intake_motor1");
        intakeMotor2 = hardwareMap.dcMotor.get("intake_motor2");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        doorServo = hardwareMap.servo.get("door_servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        programVersion = "MRB_11_17_16_3";


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

        leftMotor.setTargetPosition(1829);
        rightMotor.setTargetPosition(1829);

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
        doorServo.setPosition(0.3);
        doorPosition = 0.1;
        sleep(700);
        doorServo.setPosition(-0.8);
        doorPosition = 0.5;
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        intakeMotor1.setPower(1);
        intakeMotor2.setPower(2);
        doorServo.setPosition(0.3);
        doorPosition = 0.1;
        sleep(700);
        intakeMotor1.setPower(0);
        intakeMotor2.setPower(0);
        doorServo.setPosition(0.8);
        doorPosition = 0.5;
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        while (opModeIsActive())
        {
            telemetry.addData("encoder-end", leftMotor.getCurrentPosition());
            telemetry.update();
            idle();

        }
    }
}