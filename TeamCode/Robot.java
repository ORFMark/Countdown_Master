package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;



//@TeleOp(name="Robot", group="Exercises")

public class Robot extends LinearOpMode {
    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float shooterPower, intakePower, liftPower;
    String ProgramName = "MRB_3_6_17_1";
    // called when init button is  pressed.
public Robot()
{

}

    public void IntHardware() {
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
        sleep(200);
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

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        try {
            while (opModeIsActive()) {
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
            }


        } catch (Exception e) {
        }

    }
}


