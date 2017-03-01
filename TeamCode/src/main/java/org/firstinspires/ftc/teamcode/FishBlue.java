package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="BlueFish", group="Exercises")
//@Disabled
public class FishBlue extends LinearOpMode
{
    DcMotor leftMotor, rightMotor, intakeMotor1, intakeMotor2, shooterMotor, liftMotor;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float leftY, rightY, linputY, rinputY, shooterPower, intakePower, liftPower;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    public boolean liftDrive;
    // called when init button is  pressed.


    public void IntHardware() {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor1 = hardwareMap.dcMotor.get("intake_motor1");
        intakeMotor2 = hardwareMap.dcMotor.get("intake_motor2");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        liftMotor = hardwareMap.dcMotor.get("lift_motor");
        doorServo = hardwareMap.servo.get("door_servo");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        beaconColor = hardwareMap.colorSensor.get("beacon_color");
        liftServo = hardwareMap.servo.get("lift_servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        ProgramName = "MRB_2_1_17_1";
    }

    public void liftServoIn() {
        liftServo.setPosition(-0.3);
    }

    public void liftServoOut() {
        liftServo.setPosition(0.8);
    }

    public void chooperClose() {
        doorServo.setPosition(0.9);
    }

    public void chooperOpen() {
        doorServo.setPosition(0.3);
    }

    public void intakeIn()
    {
        intakePower = 1;
    }
    public void intakeOut()
    {
        intakePower = -1;
    }

    public void intakeStop()
    {
        intakePower=0;
    }
    public void intakeJog()
    {
        intakePower=-1;
        sleep(20);
        intakePower=1;
    }
    public void fire()
    {
        shooterPower = 1;
        sleep(800);
        shooterPower = 0;
    }
    public void ceaseFire()
    {
        shooterPower = 0;
    }
    public void liftUp()
    {
        liftPower = (float) -.75;
    }
    public void liftDown()
    {
        liftPower = (float) 0.5;
    }
    public void liftStop()
    {
        liftPower = 0;
    }
    @Override
    public void runOpMode() throws InterruptedException
    {
       IntHardware();
        // reset encoder count kept by motor.
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motor to run to target encoder position and stop with brakes on.
        // RUN_WITH_ENCODER will stop with coast.
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        chooperClose();
        liftServoIn();
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        liftDrive = false;
        chooperClose();
        liftServoIn();
        intakeStop();
        ceaseFire();
        leftY = 0;
        rightY = 0;
        liftStop();
        liftDrive = false;
        waitForStart();
        chooperClose();
        liftServoIn();
        while (opModeIsActive()) {
            telemetry.addData("Mode", "running");
            telemetry.addLine(ProgramName);
            telemetry.update();
            sleep(10000);
            // set left motor to run for 1000 encoder counts.
            leftMotor.setPower(0.4);
            rightMotor.setPower(0.4);
            leftMotor.setTargetPosition(900);
            rightMotor.setTargetPosition(900);

            // set both motors to 25% power. Movement will start.


            // wait while opmode is active and left motor is busy running to position.

            while (opModeIsActive() && leftMotor.getCurrentPosition() >= 850)   //.getCurrentPosition() > leftMotor.getTargetPosition())
            {
                telemetry.addData("encoder-fwd", leftMotor.getCurrentPosition() + "  busy=" + leftMotor.isBusy());
                telemetry.addData("MotorPower: ", "leftMotor: " + leftMotor.getPower(), "rightMotor" + rightMotor.getPower());
                telemetry.addLine(ProgramName);
                telemetry.update();
                idle();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);


            // set motor power to zero to stop motors.
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            chooperClose();
            intakeMotor1.setPower(1);
            intakeMotor2.setPower(1);
            chooperOpen();
            sleep(700);
            chooperClose();
            sleep(200);
            shooterMotor.setPower(1);
            sleep(800);
            shooterMotor.setPower(0);
            chooperOpen();
            sleep(700);
            chooperClose();
            sleep(200);
            shooterMotor.setPower(1);
            sleep(800);
            shooterMotor.setPower(0);
            chooperOpen();
            sleep(700);
            chooperClose();
            sleep(200);
            shooterMotor.setPower(1);
            sleep(800);
            shooterMotor.setPower(0);
            intakeMotor1.setPower(0);
            intakeMotor2.setPower(0);
            chooperClose();
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(200);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sleep(200);
            leftMotor.setTargetPosition(3000);
            rightMotor.setTargetPosition(3000);
            sleep(1000);
            leftMotor.setPower(0);
            rightMotor.setPower(0.4);
            while (opModeIsActive() && rightMotor.getCurrentPosition() >= 2700)
            {
                telemetry.addData("encoder-wait", rightMotor.getCurrentPosition());
                telemetry.update();
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(200);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftMotor.setTargetPosition(2000);
            rightMotor.setTargetPosition(2000);
            leftMotor.setPower(0.8);
            rightMotor.setPower(0.8);
            while (opModeIsActive() && leftMotor.getCurrentPosition() >= -2000) {
                telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                telemetry.addData("doorServo", doorServo.getPosition() + " beaconServo", beaconServo.getPosition());
                telemetry.addLine(ProgramName);
                telemetry.update();
                idle();
            }

            leftMotor.setPower(0);
            rightMotor.setPower(0);
            while (opModeIsActive()) {
                telemetry.addData("encoder-wait", leftMotor.getCurrentPosition());
                telemetry.addData("doorServo", doorServo.getPosition() + " beaconServo", beaconServo.getPosition());
                telemetry.addLine(ProgramName);
                telemetry.update();
                idle();
            }
        }
    }
}

