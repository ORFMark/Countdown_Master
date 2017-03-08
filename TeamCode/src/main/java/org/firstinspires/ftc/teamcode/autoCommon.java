package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Mark on 3/6/2017.
 */
public class autoCommon extends LinearOpMode
{
    DcMotor leftMotor, rightMotor;
    boolean leftRun, rightRun;
    Robot robot;

    public void autodriveForward(int leftEncoder, double leftPower, int  rightEncoder, double rightPower ) {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(200);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sleep(200);
        leftMotor.setTargetPosition(leftEncoder);
        rightMotor.setTargetPosition(rightEncoder);
        sleep(1000);
        if (leftEncoder > 0) {
            while (opModeIsActive() && leftMotor.getCurrentPosition() <= leftEncoder) {
                leftRun = true;
                leftMotor.setPower(leftPower);
            }
            while (leftMotor.getCurrentPosition() >= leftEncoder) {
                leftRun = false;
                leftMotor.setPower(0);

            }
        }
        else if (leftEncoder < 0) {
            while (opModeIsActive() && leftMotor.getCurrentPosition() >= leftEncoder) {
                leftRun = true;
                leftMotor.setPower(leftPower);
            }
            while (leftMotor.getCurrentPosition() <= leftEncoder) {
                leftRun = false;
                leftMotor.setPower(0);

            }
        }
        else if (leftEncoder == 0)
        {
            leftRun=false;
            leftMotor.setPower(leftPower);
        }
        else
            leftMotor.setPower(0);
        if (rightEncoder >= 0) {
            while (opModeIsActive() && rightMotor.getCurrentPosition() <= rightEncoder) {
                rightRun = true;
                rightMotor.setPower(rightPower);
            }
            while (rightMotor.getCurrentPosition() >= rightEncoder) {
                rightRun = false;
                rightMotor.setPower(0);

            }
        }
        else if (rightEncoder <= 0) {
            while (opModeIsActive() && rightMotor.getCurrentPosition() >= rightEncoder) {
                rightRun = true;
                rightMotor.setPower(rightPower);
            }
            while (rightMotor.getCurrentPosition() <= rightEncoder) {
                rightRun = false;
                rightMotor.setPower(0);
            }
        }
        else if (rightEncoder == 0)
        {
            rightRun = false;
            rightMotor.setPower(rightPower);
        }
        else
            rightMotor.setPower(0);
        while (opModeIsActive() && rightRun || leftRun)
        {
            telemetry.addLine("autoForward");
            telemetry.addData("RightGoal: ", rightEncoder+ " RightCurrent: ", rightMotor.getCurrentPosition());
            telemetry.addData("leftGoal: ", leftEncoder+ " leftCurrent: ", leftMotor.getCurrentPosition());
            telemetry.addLine(robot.ProgramName);
            telemetry.update();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void autoFire()
    {
        for (int shoot = 1; shoot < 3; shoot++ ) {
            telemetry.addLine(robot.ProgramName);
            telemetry.addLine("Fireing: " + shoot);
            robot.chooperClose();
            robot.intakeIn();
            robot.chooperOpen();
            sleep(700);
            robot.chooperClose();
            sleep(200);
            robot.fire();
            sleep(800);
            robot.ceaseFire();
        }
    }

    public void runOpMode() throws InterruptedException {

        waitForStart();
        try {
            while (opModeIsActive()) {

            }

        } catch (Exception e) {
        }

    }
}
