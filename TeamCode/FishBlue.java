package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="BlueFish", group="Exercises")
//@Disabled
public class FishBlue extends LinearOpMode {

    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    Servo doorServo, beaconServo, liftServo;
    boolean leftRun, rightRun;
    ColorSensor beaconColor;
    String ProgramVersion = "MRB_3_6_17_1";
    // called when init button is  pressed.

    //public void liftServoIn() {
    //liftServo.setPosition(-0.3);
    //}

    /*public void liftServoOut() {
        liftServo.setPosition(0.8);
    }
*/
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
        sleep(800);
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
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
        if (leftEncoder > 0 && rightEncoder > 0) {
            leftRun = true;
            rightRun = true;
            while (leftEncoder >= leftMotor.getCurrentPosition()) {
                rightMotor.setPower(rightPower);
                leftMotor.setPower(leftPower);
                telemetry.addLine("autoForward");
                telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
                telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
                telemetry.addLine(ProgramVersion);
                telemetry.update();
            }
        } else if (leftEncoder < 0 && rightEncoder < 0) {
            leftRun = true;
            while (leftEncoder <= leftMotor.getCurrentPosition()) {
                rightMotor.setPower(rightPower);
                leftMotor.setPower(leftPower);
                telemetry.addLine("autoForward");
                telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
                telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
                telemetry.addLine(ProgramVersion);
                telemetry.update();
            }


        } else if (leftEncoder == 0 && rightEncoder > 0) {
            leftRun = false;
            rightRun = true;
            while (rightEncoder >= rightMotor.getCurrentPosition()) {
                rightMotor.setPower(rightPower);
                leftMotor.setPower(leftPower);
                telemetry.addLine("autoForward");
                telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
                telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
                telemetry.addLine(ProgramVersion);
                telemetry.update();
            }
        } else if (rightEncoder == 0 && leftEncoder > 0) {
            leftMotor.setPower(0);
            while (leftEncoder <= leftMotor.getCurrentPosition()) {
                rightMotor.setPower(rightPower);
                leftMotor.setPower(leftPower);
                telemetry.addLine("autoForward");
                telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
                telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
                telemetry.addLine(ProgramVersion);
                telemetry.update();
            }
        } else {
            rightMotor.setPower(0);
            leftMotor.setPower(0);

            rightMotor.setPower(rightPower);
            leftMotor.setPower(leftPower);
            telemetry.addLine("autoForward");
            telemetry.addData("RightGoal: ", rightEncoder + " RightCurrent: ", rightMotor.getCurrentPosition());
            telemetry.addData("leftGoal: ", leftEncoder + " leftCurrent: ", leftMotor.getCurrentPosition());
            telemetry.addLine(ProgramVersion);
            telemetry.update();


        }
    }

    public void autoFire() {
        for (int shoot = 1; shoot < 4; shoot++) {
            telemetry.addLine(ProgramVersion);
            telemetry.addLine("Fireing: " + shoot);
            chooperClose();
            intakeIn();
            chooperOpen();
            sleep(700);
            chooperClose();
            sleep(200);
            fire();
            sleep(1000);
            ceaseFire();
        }
        intakeStop();
    }

    // called when init button is  pressed.


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
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramVersion);
        telemetry.update();

        // reset encoder count kept by motor.
       chooperClose();
        //liftServoIn();
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        chooperClose();
        //liftServoIn();
        intakeStop();
        ceaseFire();
        liftStop();
        waitForStart();
        chooperClose();
        //liftServoIn();
        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(ProgramVersion);
            telemetry.update();
            // set left motor to run for 1000 encoder counts.

            chooperClose();
            telemetry.addData("Mode", "waiting");
            telemetry.update();
            // wait for start button.
            waitForStart();
            while (opModeIsActive()) {
                telemetry.addData("Mode", "running");
                telemetry.addLine(ProgramVersion);
                telemetry.update();
                // set left motor to run for 1000 encoder counts.
                leftMotor.setPower(0.4);
                rightMotor.setPower(0.4);
                sleep(1000);
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                sleep(200);
                autoFire();
                leftMotor.setPower(0);
                rightMotor.setPower(0.7);
                sleep(2000);
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                leftMotor.setPower(.6);
                rightMotor.setPower(-0.6);
                sleep(500);
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                leftMotor.setPower(0.8);
                rightMotor.setPower(0.8);
                sleep(3000);
                leftMotor.setPower(0);
                rightMotor.setPower(0);

                while (opModeIsActive()) {
                    telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                    telemetry.addData("doorServo", doorServo.getPosition());
                    telemetry.addLine(ProgramVersion);
                    telemetry.update();
                    idle();

                }

            }

        }

    }
}