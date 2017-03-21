package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.lang.Thread;


/**
 * Created by Mark on 3/21/2017.
 */
public class RobotCommon {
    Servo doorServo, liftServo;
    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    String ProgramVersion;


    public RobotCommon() {

    }
public void intHardware(LinearOpMode op)
{
    leftMotor = op.hardwareMap.dcMotor.get("left_motor");
    rightMotor = op.hardwareMap.dcMotor.get("right_motor");
    intakeMotor = op.hardwareMap.dcMotor.get("intake_motor");
    shooterMotor = op.hardwareMap.dcMotor.get("shooter_motor");
    liftMotor1 = op.hardwareMap.dcMotor.get("lift_motor1");
    liftMotor2 = op.hardwareMap.dcMotor.get("lift_motor2");
    doorServo = op.hardwareMap.servo.get("door_servo");
    //beaconServo = hardwareMap.servo.get("beacon_servo");
    //beaconColor = hardwareMap.colorSensor.get("beacon_color");
    liftServo = op.hardwareMap.servo.get("lift_servo");
    rightMotor.setDirection(DcMotor.Direction.REVERSE);
    shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    ProgramVersion = "MRB_3_21_2017_1";
}
    public void liftServoIn() {
        liftServo.setPosition(0.5);
    }

    public void liftServoOut() {
        liftServo.setPosition(Servo.MIN_POSITION);
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

    public void intakeJog(LinearOpMode op) {
        intakeMotor.setPower(-1);
        op.sleep(20);
        intakeMotor.setPower(1);
    }

    public void fire() {
        shooterMotor.setPower(-1);
        while (shooterMotor.getCurrentPosition() <= 1000);
        shooterMotor.setPower(0);
    }


    public void ceaseFire() {
        shooterMotor.setPower(0);
    }

    public void liftUp() {
        liftServoIn();
        liftMotor1.setPower(1);
        liftMotor2.setPower(1);
    }

    public void liftDown() {
        liftMotor1.setPower(-0.75);
        liftMotor2.setPower(-0.75);
    }

    public void liftStop() {
        liftMotor1.setPower(0);
        liftMotor2.setPower(0);

    }
public float drive(float stick)
{
    float output=0;
    float slew = 0;
    float oldstick = 0;
    float change = 0;
        if (stick != 0) {
            if (stick <= .2 && stick >= -0.2)
                output = 0;
            else
                stick = output;
        } else {
            stick = output;
        }

    if (stick== 0 && change == 0)
        change = 0;
    else if (change >= .1 && stick >= 0)
            change = (oldstick + (float) 0.1);
    else if (change <= -.1 && stick <= 0)
        change = (oldstick + (float) -0.1);
    else
        change = stick;
    oldstick = change;
    return change;
}
}
