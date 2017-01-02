package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="RedBeacon", group="Exercises")
//@Disabled
public class NewAuto extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor intakeMotor1, intakeMotor2, shooterMotor;
    Servo doorServo, beaconServo;
    int loopCount, newLoopCount;
    String programVersion;
    ColorSensor lineColor;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor1 = hardwareMap.dcMotor.get("intake_motor1");
        intakeMotor2 = hardwareMap.dcMotor.get("intake_motor2");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        doorServo = hardwareMap.servo.get("door_servo");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        lineColor = hardwareMap.colorSensor.get("line_color");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        programVersion = "MRB_12_15_16_3";
        // reset encoder count kept by motor.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        beaconServo.setPosition(0.5);
        doorServo.setPosition(0.3);
        loopCount = 0;
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();
while (opModeIsActive()) {
    telemetry.addData("Mode", "running");
    telemetry.addLine(programVersion);
    telemetry.update();

    // set left motor to run for 1000 encoder counts.
    leftMotor.setPower(0.04);
    rightMotor.setPower(0.04);
    leftMotor.setTargetPosition(900);
    rightMotor.setTargetPosition(900);

    // set both motors to 25% power. Movement will start.

    leftMotor.setPower(0.04);
    rightMotor.setPower(0.04);

    // wait while opmode is active and left motor is busy running to position.

    while (opModeIsActive() && leftMotor.getCurrentPosition() >= -900)   //.getCurrentPosition() > leftMotor.getTargetPosition())
    {
        telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
        telemetry.addData("MotorPower: ", "leftMotor: " + leftMotor.getPower(), "rightMotor" + rightMotor.getPower());
        telemetry.addLine(programVersion);
        telemetry.update();
        idle();
    }
    leftMotor.setPower(0);
    rightMotor.setPower(0);


    // set motor power to zero to stop motors.
    leftMotor.setPower(0.0);
    rightMotor.setPower(0.0);
    doorServo.setPosition(0.3);
    intakeMotor1.setPower(1);
    intakeMotor2.setPower(1);
    doorServo.setPosition(-0.9);
    sleep(700);
    doorServo.setPosition(0.3);
    sleep(200);
    shooterMotor.setPower(1);
    sleep(800);
    shooterMotor.setPower(0);
    doorServo.setPosition(-0.9);
    sleep(700);
    doorServo.setPosition(0.3);
    sleep(200);
    shooterMotor.setPower(1);
    sleep(800);
    shooterMotor.setPower(0);
    intakeMotor1.setPower(0);
    intakeMotor2.setPower(0);
    newLoopCount = loopCount++;
    leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    sleep(200);
    leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    leftMotor.setTargetPosition(300);
    rightMotor.setTargetPosition(300);
    sleep(500);
    leftMotor.setPower(0.04);
    rightMotor.setPower(0.4);
    while (opModeIsActive() && leftMotor.getCurrentPosition() >= -100)
    {
        telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
        telemetry.addData("MotorPower: ", "leftMotor: " + leftMotor.getPower(), "rightMotor" + rightMotor.getPower());
        telemetry.addLine(programVersion);
        telemetry.update();
        idle();
    }
    leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    leftMotor.setTargetPosition(300);
    rightMotor.setTargetPosition(300);
    sleep(500);
while (opModeIsActive() && rightMotor.getCurrentPosition() >= -120)
{
    rightMotor.setPower(0.1);
    leftMotor.setPower(0);
    telemetry.addData("encoder-wait", rightMotor.getCurrentPosition());
    telemetry.update();
}
leftMotor.setPower(0);
    rightMotor.setPower(0);
    sleep(1000);

    leftMotor.setPower(0.04);
    rightMotor.setPower(0.04);
    while (opModeIsActive() && lineColor.blue() <= 10) {
        telemetry.addData("blue", lineColor.blue() );
        telemetry.addData("Power", "right: " + rightMotor.getPower(), " , left: " + leftMotor.getPower() );
        telemetry.update();
    }
    leftMotor.setPower(0);
    rightMotor.setPower(0);
    while (opModeIsActive()) {
        telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
        telemetry.addData("doorServo", doorServo.getPosition() + " beaconServo", beaconServo.getPosition());
        telemetry.addData("loop", loopCount + ", ", newLoopCount);
        telemetry.addLine(programVersion);
        telemetry.update();
        idle();
    }
}
    }
}

