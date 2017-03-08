package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="RedFish", group="Exercises")
//@Disabled
public class FishRed extends LinearOpMode
{
    Robot robot = new Robot();
    autoCommon auto = new autoCommon();

    @Override
    public void runOpMode() throws InterruptedException
    {

        // reset encoder count kept by motor.
        robot.leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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

            // set left motor to run for 1000 encoder counts.
            auto.autodriveForward(900, 0.4, 900, 0.4);

            // set motor power to zero to stop motors.
            auto.autoFire();
            auto.autodriveForward(2700, 0.4, 0, 0);
            auto.autodriveForward(3000, 0.8, 3000, 0.8);


            while (opModeIsActive()) {
               telemetry.addLine(robot.ProgramName);
                telemetry.addLine("end");
                idle();
            }
        }
    }
}

