package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="NearAuto", group="Exercises")
@Disabled
public class FirstAuto extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor intakeMotor1, intakeMotor2, shooterMotor;
    Servo doorServo, liftServo;
    String programVersion;
    ColorSensor beaconColor;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor1 = hardwareMap.dcMotor.get("intake_motor1");
        intakeMotor2 = hardwareMap.dcMotor.get("intake_motor2");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        doorServo = hardwareMap.servo.get("door_servo");
        liftServo = hardwareMap.servo.get("lift_servo");
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
        liftServo.setPosition(-1);
        telemetry.addData("Mode", "running");
        telemetry.update();



        // set left motor to run for 5000 encoder counts.

        leftMotor.setTargetPosition(1000);
        rightMotor.setTargetPosition(1000);

        // set both motors to 25% power. Movement will start.

while (leftMotor.getCurrentPosition() <= 1000)
{
    leftMotor.setPower(-0.1); //Sean: Try slowing this down to see if just skipping over position maybe like 0.1 or 0.05 power.
    rightMotor.setPower(-0.1);
}

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())


            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.addData("MotorPower", + leftMotor.getPower() + ", " + rightMotor.getPower() );
            telemetry.update();
            idle();

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        if (leftMotor.getCurrentPosition() >= 1500)
        {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
        // set motor power to zero to stop motors.
      /*  sleep(300);
        doorServo.setPosition(-0.9);
        sleep(700);
        doorServo.setPosition(0.3);
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        intakeMotor1.setPower(1);
        intakeMotor2.setPower(1);
        sleep(400);
        doorServo.setPosition(-0.9);
        sleep(700);
        doorServo.setPosition(0.3);
        sleep(200);
        shooterMotor.setPower(1);
        sleep(800);
        shooterMotor.setPower(0);
        intakeMotor1.setPower(0);
        intakeMotor2.setPower(0);
*/
        while (opModeIsActive())
        {
            telemetry.addData("encoder-end", leftMotor.getCurrentPosition());
            telemetry.addLine(programVersion);
            telemetry.update();
            idle();
        }
    }
}