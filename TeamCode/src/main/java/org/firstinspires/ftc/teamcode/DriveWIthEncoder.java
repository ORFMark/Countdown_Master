package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous(name="Drive Encoder", group="Exercises")
//@Disabled
public class DriveWIthEncoder extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;
    ColorSensor beaconColor;

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

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

            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        // set motor power to zero to stop motors.
        leftMotor.setTargetPosition(-1452);
        rightMotor.setTargetPosition(-696);
        leftMotor.setPower(-0.25);
        rightMotor.setPower(-0.25);

        while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {

            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setTargetPosition(-2952);
        rightMotor.setTargetPosition(-2196);
        leftMotor.setPower(-0.25);
        rightMotor.setPower(-0.25);

        while (opModeIsActive() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {

            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        while (opModeIsActive() && rightMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {

            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftMotor.setTargetPosition(-3300);
        rightMotor.setTargetPosition(-3960);
        leftMotor.setPower(-0.15);
        rightMotor.setPower(-0.4);
        while (opModeIsActive() && rightMotor.isBusy() && leftMotor.isBusy())   //.getCurrentPosition() > leftMotor.getTargetPosition())
        {

            telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
            telemetry.update();
            idle();
        }
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