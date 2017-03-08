package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Red 2", group="Exercises")
//@Disabled
public class Red2 extends LinearOpMode
{
   Robot robot = new Robot();
    autoCommon auto = new autoCommon();

    @Override
    public void runOpMode() throws InterruptedException
    {

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        robot.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.chooperClose();
        robot.liftServoIn();
        robot.intakeStop();
        robot.ceaseFire();
        robot.liftStop();
        waitForStart();
        robot.chooperClose();
        robot.liftServoIn();
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(robot.ProgramName);
            telemetry.update();
            sleep(15000);
            // set left motor to run for 1000 encoder counts.
            auto.autodriveForward(200, 0.4, 200, 0.4);
            auto.autodriveForward(0,0,200,0.4);

            // set motor power to zero to stop motors.
            auto.autodriveForward(800, 0.4, 800, 0.4);
            auto.autoFire();
            auto.autodriveForward(0,0, 800, 0.4);
           auto.autodriveForward(3000, 0.8, 3000, 0.8);
            while (opModeIsActive()) {
                telemetry.addLine(robot.ProgramName);
                telemetry.addLine("end");
                telemetry.update();
                idle();
            }
        }
    }
}

