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

    Robot robot;
    autoCommon auto;
    // called when init button is  pressed.




    @Override
    public void runOpMode() throws InterruptedException
    {
        robot = new Robot();
        auto = new autoCommon();
        robot.IntHardware();
        // reset encoder count kept by motor.
        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.chooperClose();
        robot.liftServoIn();

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        robot.chooperClose();
        robot.liftServoIn();
        robot.intakeStop();
        robot.ceaseFire();
        robot.liftStop();
        waitForStart();
        robot.chooperClose();
        robot.liftServoIn();

        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(robot.ProgramName);
            telemetry.update();
            sleep(15000);
            // set left motor to run for 1000 encoder counts.
            auto.autodriveForward(200, 0.4, 200, 0.4);
            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
            auto.autodriveForward(700, 0.4, 0, 0);

            // set motor power to zero to stop motors.
            auto.autodriveForward(800, 0.4, 800, 0.4);

            auto.autoFire();

            auto.autodriveForward(800, 0.4, 0, 0);
            auto.autodriveForward(2000, 0.8, 2000, 0);

            robot.leftMotor.setPower(0);
            robot.rightMotor.setPower(0);
            while (opModeIsActive()) {
                telemetry.addLine(robot.ProgramName);
                telemetry.addLine("end");
                telemetry.update();
                idle();
            }
        }
    }
}


