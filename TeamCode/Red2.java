package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name="Red 2", group="Exercises")
@Disabled
public class Red2 extends LinearOpMode {
    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    Servo doorServo, beaconServo, liftServo;
    boolean leftRun, rightRun;
    ColorSensor beaconColor;
    String ProgramVersion = "MRB_3_6_17_1";
    // called when init button is  pressed.

    public void liftServoIn() {
        liftServo.setPosition(-0.3);
    }

    public void liftServoOut() {
        liftServo.setPosition(0.8);
    }

    public void chooperClose() {
        doorServo.setPosition(Servo.MAX_POSITION);
    }

    public void chooperOpen() {
        doorServo.setPosition(Servo.MIN_POSITION);
    }

    public void intakeIn() {
        intakeMotor.setPower(1);
    }

    public void intakeOut() {

        intakeMotor.setPower(-1);
    }

    public void intakeStop() {

        intakeMotor.setPower(0);
    }

    public void intakeJog() {
        intakeMotor.setPower(-1);
        sleep(20);
        intakeMotor.setPower(1);
    }

    public void fire() {

        shooterMotor.setPower(-1);
        sleep(550);
        shooterMotor.setPower(0);
    }

    public void ceaseFire() {
        shooterMotor.setPower(0);
    }

    public void liftUp() {
        liftMotor1.setPower(0.75);
        liftMotor2.setPower(0.75);
    }

    public void liftDown() {
        liftMotor1.setPower(-0.5);
        liftMotor2.setPower(-0.5);
    }

    public void liftStop() {
        liftMotor1.setPower(0);
        liftMotor2.setPower(0);
    }

    public void autodriveForward(int leftEncoder, double leftPower, int rightEncoder, double rightPower) {
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
        } else if (leftEncoder < 0) {
            while (opModeIsActive() && leftMotor.getCurrentPosition() >= leftEncoder) {
                leftRun = true;
                leftMotor.setPower(leftPower);
            }
            while (leftMotor.getCurrentPosition() <= leftEncoder) {
                leftRun = false;
                leftMotor.setPower(0);

            }
        } else if (leftEncoder == 0) {
            leftRun = false;
            leftMotor.setPower(leftPower);
        } else
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
        } else if (rightEncoder <= 0) {
            while (opModeIsActive() && rightMotor.getCurrentPosition() >= rightEncoder) {
                rightRun = true;
                rightMotor.setPower(rightPower);
            }
            while (rightMotor.getCurrentPosition() <= rightEncoder) {
                rightRun = false;
                rightMotor.setPower(0);
            }
        } else if (rightEncoder == 0) {
            rightRun = false;
            rightMotor.setPower(rightPower);
        } else
            rightMotor.setPower(0);
        while (opModeIsActive() && rightRun || leftRun) {
            telemetry.addLine("autoForward");
            telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
            telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
            telemetry.addLine(ProgramVersion);
            telemetry.update();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void autoFire() {
        for (int shoot = 1; shoot < 3; shoot++) {
            telemetry.addLine(ProgramVersion);
            telemetry.addLine("Fireing: " + shoot);
            chooperClose();
            intakeIn();
            chooperOpen();
            sleep(700);
            chooperClose();
            sleep(200);
            fire();
            sleep(800);
            ceaseFire();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor = hardwareMap.dcMotor.get("intake_motor");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        liftMotor1 = hardwareMap.dcMotor.get("lift_motor1");
        liftMotor2 = hardwareMap.dcMotor.get("lift_motor2");
        doorServo = hardwareMap.servo.get("door_servo");
        //beaconServo = hardwareMap.servo.get("beacon_servo");
        //beaconColor = hardwareMap.colorSensor.get("beacon_color");
        //liftServo = hardwareMap.servo.get("lift_servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramVersion);
        telemetry.update();
        {

            // set motor to run to target encoder position and stop with brakes on.
            // RUN_WITH_ENCODER will stop with coast.
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            chooperClose();
            liftServoIn();
            intakeStop();
            ceaseFire();
            liftStop();
            waitForStart();
            chooperClose();
            liftServoIn();
            telemetry.addData("Mode", "waiting");
            telemetry.update();

            // wait for start button.

            waitForStart();
            while (opModeIsActive()) {
                telemetry.addData("Mode", "running");
                telemetry.addLine(ProgramVersion);
                telemetry.update();
                sleep(15000);
                // set left motor to run for 1000 encoder counts.
                autodriveForward(200, 0.4, 200, 0.4);
                autodriveForward(0, 0, 200, 0.4);

                // set motor power to zero to stop motors.
                autodriveForward(800, 0.4, 800, 0.4);
                autoFire();
                autodriveForward(0, 0, 800, 0.4);
                autodriveForward(3000, 0.8, 3000, 0.8);
                while (opModeIsActive()) {
                    telemetry.addLine(ProgramVersion);
                    telemetry.addLine("end");
                    telemetry.update();
                    idle();
                }
            }
        }
    }
}

