package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;



//@TeleOp(name="Robot", group="Exercises")

public class Robot extends LinearOpMode {
    DcMotor leftMotor, rightMotor, intakeMotor1, intakeMotor2, shooterMotor, liftMotor;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float shooterPower, intakePower, liftPower;
    String ProgramName = "MRB_3_6_17_1";
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

    public void intakeIn() {
        intakePower = 1;
    }

    public void intakeOut() {
        intakePower = -1;
    }

    public void intakeStop() {
        intakePower = 0;
    }

    public void intakeJog() {
        intakePower = -1;
        sleep(20);
        intakePower = 1;
    }

    public void fire() {
        shooterPower = 1;
        sleep(800);
        shooterPower = 0;
    }

    public void ceaseFire() {
        shooterPower = 0;
    }

    public void liftUp() {
        liftPower = (float) -.75;
    }

    public void liftDown() {
        liftPower = (float) 0.5;
    }

    public void liftStop() {
        liftPower = 0;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        try {
            while (opModeIsActive()) {

            }

        } catch (Exception e) {
        }

    }

}

