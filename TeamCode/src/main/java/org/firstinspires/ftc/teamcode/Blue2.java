package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Blue2", group="Exercises")
//@Disabled
public class Blue2 extends LinearOpMode
{
    DcMotor leftMotor, rightMotor, intakeMotor1, intakeMotor2, shooterMotor, liftMotor;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float leftY, rightY, linputY, rinputY, shooterPower, intakePower, liftPower;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    public boolean liftDrive;
    Robot robot;
    autoCommon auto;
    // called when init button is  pressed.




    @Override
    public void runOpMode() throws InterruptedException
    {
        robot.IntHardware();
        // reset encoder count kept by motor.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.chooperClose();
        robot.liftServoIn();

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        liftDrive = false;
        robot.chooperClose();
        robot.liftServoIn();
        robot.intakeStop();
        robot.ceaseFire();
        leftY = 0;
        rightY = 0;
        robot.liftStop();
        liftDrive = false;
        waitForStart();
        robot.chooperClose();
        robot.liftServoIn();

        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(ProgramName);
            telemetry.update();
            sleep(15000);
            // set left motor to run for 1000 encoder counts.
            auto.autodriveForward(200, 0.4, 200, 0.4);
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            auto.autodriveForward(700, 0.4, 0, 0);

            // set motor power to zero to stop motors.
            auto.autodriveForward(800, 0.4, 800, 0.4);

            auto.autoFire();

            auto.autodriveForward(800, 0.4, 0, 0);
            auto.autodriveForward(2000, 0.8, 2000, 0);

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            while (opModeIsActive()) ;
            telemetry.addLine("end");
            telemetry.update();
            idle();
        }
    }
}


