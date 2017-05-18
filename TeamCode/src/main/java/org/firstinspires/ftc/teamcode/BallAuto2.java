package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by landryav on 5/16/2017.
 */

@Autonomous(name="Drive Tank AVL", group="Exercises")
public class BallAuto2 extends LinearOpMode {

    DcMotor leftMotor, rightMotor, intakeMotor;
    public float LeftY;
    public float RightY;
    {}

    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 50% power.

        leftMotor.setPower(-0.5);
        rightMotor.setPower(-0.5);

        sleep(1250);        // wait for 3.5 seconds.

        // set motor power to zero to stop motors.

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        sleep(500);  // wait half second for bot to stop moving.

        // now set motors, one forward one reverse. Should cause the bot to rotate.

        leftMotor.setPower(-0.25);
        rightMotor.setPower(0.25);

        sleep(1300); // adjust this delay to get the bot to rotate 90 degrees.

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        sleep(500); // wait for bot to stop moving.

        leftMotor.setPower(-0.5);
        rightMotor.setPower(-0.5);

        sleep(1250);        // wait for 3.5 seconds.

        // set motor power to zero to stop motors.

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

    }
}