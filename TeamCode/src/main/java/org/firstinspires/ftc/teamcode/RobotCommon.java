package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



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
    ProgramVersion = "MRB_6_2_2017_1";
    chooperClose();
    liftServoIn();
    intakeStop();
    ceaseFire();
    liftStop();
    rightMotor.setPower(0);
    leftMotor.setPower(0);
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
public float  Slewdrive(float stick){
    float output;
    float slew;
    float change;

    if (stick != 0) {
        if (stick <= .2 && stick >= -0.2)
            output = 0;
        else
            output = stick;
    } else {
        output = stick;
    }
    float newstick = output;
    float oldstick = newstick;
    change = java.lang.Math.abs(output) - java.lang.Math.abs(oldstick);
    if (output == 0) {
        newstick = 0;
    }
    else if (output >= 0)
    {
        if (change >= 0.05)
        {
            newstick = oldstick + (float) 0.05;
        }
        else if (change <= -0.05)
        {
            newstick = oldstick - (float) 0.05;
        }
        else newstick = output;
    }
    else if (output <= 0)
    {
        if (change >= 0.05)
        {
            newstick = oldstick - (float) 0.05;
        }
        else if (change <= -0.05)
        {
            newstick = oldstick + (float) 0.05;
        }
        else
        {
            newstick = output;
        }
    }
    else
        newstick = output;
    oldstick = newstick;
    return newstick;
}
    public void tankdrive(float left, float right, boolean corrected)
    {
        float leftOutput, rightOutput;
        if (left != 0) {
            if (left <= .2 && left >= -0.2)
                leftOutput = 0;
            else
                leftOutput = left;
        } else {
            leftOutput = left;
        }
        if (right != 0) {
            if (right <= .2 && right >= -0.2)
                rightOutput = 0;
            else
                rightOutput = right;
        } else {
            rightOutput = right;
        }

        if (corrected)
        {
            leftMotor.setPower(Slewdrive(leftOutput));
            rightMotor.setPower(Slewdrive(rightOutput));
        }
        else
        {
            leftMotor.setPower(leftOutput);
            rightMotor.setPower(rightOutput);
        }


    }

    public void autoDriveStraight(double leftPower, int leftEncoder, double rightPower, int rightEncoder, LinearOpMode op)
    {
        boolean autoBusy = true;
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(leftEncoder);
        rightMotor.setTargetPosition(rightEncoder);
        while (autoBusy && op.opModeIsActive())
        {
            if (leftPower != 0) {
                if (leftMotor.isBusy()) {
                    leftMotor.setPower(leftPower);
                } else {
                    leftMotor.setPower(0);
                }
            }
            if (rightPower != 0) {
                if (rightMotor.isBusy()) {
                    rightMotor.setPower(rightPower);
                } else {
                    rightMotor.setPower(0);
                }
            }
            op.telemetry.addLine(ProgramVersion);
            op.telemetry.addLine("Mode: AutoDriving Straight");
            op.telemetry.addData("leftBusy: ", leftMotor.isBusy() + " leftPower: ", leftMotor.getPower() + " LeftPosition: ", leftMotor.getCurrentPosition());
            op.telemetry.addData("rightBusy: ", rightMotor.isBusy() + " rightPower: ", rightMotor.getPower() + " rightPosition: ", leftMotor.getCurrentPosition());
            op.telemetry.update();
            autoBusy = leftMotor.isBusy() && rightMotor.isBusy();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        op.telemetry.addLine(ProgramVersion);
        op.telemetry.addLine("Mode: StopDrive");
    }
    public void autoLeftTurn(double power, int target, LinearOpMode op)
    {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setTargetPosition(target);
        leftMotor.setPower(power);
        while(leftMotor.isBusy() && op.opModeIsActive()) {
            op.telemetry.addLine(ProgramVersion);
            op.telemetry.addLine("Mode: AutoDriving Left Turn");
            op.telemetry.addData("leftBusy: ", leftMotor.isBusy() + " leftPower: ", leftMotor.getPower() + " LeftPosition: ", leftMotor.getCurrentPosition());
            op.telemetry.update();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    public void autoRightTurn(double power, int target, LinearOpMode op)
    {
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setTargetPosition(target);
        rightMotor.setPower(power);
        while(rightMotor.isBusy() && op.opModeIsActive()) {
            op.telemetry.addLine(ProgramVersion);
            op.telemetry.addLine("Mode: AutoDriving Right Turn");
            op.telemetry.addData("rightBusy: ", rightMotor.isBusy() + " rightPower: ", rightMotor.getPower() + " rightPosition: ", leftMotor.getCurrentPosition());
            op.telemetry.update();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}

