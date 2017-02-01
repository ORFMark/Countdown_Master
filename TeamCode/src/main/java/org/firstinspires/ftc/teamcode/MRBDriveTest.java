package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;



@TeleOp(name="MRBDriveTest", group="Exercises")
//@Disabled
public class MRBDriveTest extends LinearOpMode {
    DcMotor leftMotor, rightMotor ,  intakeMotor1, intakeMotor2, shooterMotor, liftMotor ;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float leftY, rightY, linputY, rinputY, shooterPower, intakePower, liftPower;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    public boolean liftDrive;
    Thread autoShoot = new AutoShoot();
    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException {
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
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramName);
        telemetry.update();

        // wait for start button.
        //shooterMotor.setPower(0);


        //intakeMotor.setPower(0);
        liftDrive = false;
        doorServo.setPosition(0.9);
        liftServo.setPosition(-0.3);
        intakePower = 0;
        shooterPower = 0;
        leftY = 0;
        rightY = 0;
        liftPower = 0;
        waitForStart();
        autoShoot.start();
        doorServo.setPosition(0.9);
        liftServo.setPosition(-0.3);




        try {
            liftDrive = false;

            while (opModeIsActive()) {
                //shooterMotor.setPower(0);


                // Drive Code
                leftY = gamepad1.left_stick_y;
                rightY = gamepad1.right_stick_y;
                //Stick Dead Zone
                if (liftDrive == false) {
                    if (leftY <= .2 && leftY >= -0.2)
                        linputY = 0;
                    else
                        linputY = leftY;

                    if (rightY <= .2 && rightY >= -.2)
                        rinputY = 0;
                    else
                        rinputY = rightY;
                 }
               else if (liftDrive == true)
                {
                    if (leftY <= .2 && leftY >= -0.2)
                        linputY = 0;
                    else
                        linputY = leftY / 2;

                    if (rightY <= .2 && rightY >= -.2)
                        rinputY = 0;
                    else
                        rinputY = rightY / 2;
                }
                else {
                    rinputY = rightY;
                    linputY = leftY;
                }
            // end drive Code


                //Gamepad 1 Commands
                if (gamepad1.dpad_up)
                    intakePower=1;
                if (gamepad1.dpad_down)
                    intakePower=-1;
                if (gamepad1.a)
                    intakePower=0;

                if (gamepad1.y)
                    shooterPower=0;

                if (gamepad1.b)
                {
                    liftDrive = !liftDrive;
                }

                if (gamepad1.left_bumper) {
                    liftServo.setPosition(-0.3);
                }
                if (gamepad1.right_bumper) {
                    liftServo.setPosition(0.8);

                }
                // Gamepad 2 commands
                if (gamepad2.a) {
                    beaconPosition = 0.5;
                    beaconServo.setPosition(0.5);
                }
                if (gamepad2.b) {
                    beaconPosition = 0.6;
                }
                if (gamepad2.y) {
                    beaconPosition = 1.0;
                    beaconServo.setPosition(1.0);
                }
                if (gamepad2.x)
                {
                    shooterPower=1;
                    sleep(800);
                    shooterPower=0;
                }
                if (gamepad2.dpad_left)
                {
                    intakePower=-1;
                    sleep(20);
                    intakePower=1;
                }

                if (gamepad2.right_bumper)
                {
                    doorServo.setPosition(0.9);
                }
                if (gamepad2.left_bumper)
                {
                    doorServo.setPosition(0.3);
                }
                if (gamepad2.dpad_up)
                {
                    liftPower = (float) -.75;
                }
                if (gamepad2.dpad_down)
                {
                    liftPower = (float) 0.5;
                }
                if (gamepad2.right_trigger > .5)
                {
                    liftPower = (float) 0;
                }
                if (beaconColor.red() <= beaconColor.blue())
                {
                    colorState = "Blue";
                }
                else if(beaconColor.red() >= beaconColor.blue())
                {
                    colorState = "Red";
                }
                else
                {
                    colorState = "Unknown";
             }

                beaconServo.setPosition(beaconPosition);

                leftMotor.setPower(Range.clip(linputY, -1.0, 1.0));
                rightMotor.setPower(Range.clip(rinputY, -1.0, 1.0));
                intakeMotor1.setPower(Range.clip(intakePower, -1.0, 1.0));
                intakeMotor2.setPower(Range.clip(intakePower, -1.0, 1.0));
                liftMotor.setPower(Range.clip(liftPower, -1.0, 1.0));

                telemetry.addData("Mode", "running");
                telemetry.addLine("ProgramName = " + ProgramName);
                telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
                if (liftDrive == false)
                {
                    telemetry.addLine("Normal Driving");
                }
                if (liftDrive == true)
                {
                    telemetry.addLine("Lift Drive");
                }
                telemetry.addData("output", "left=" + linputY + " right=" + rinputY);
                telemetry.addData("intakeStructure", " shooter =" + shooterPower + " intake =" + intakePower);
                telemetry.addData("lift", liftPower);
                telemetry.addData("DoorServo", " Door Position = " + doorServo.getPosition());
                telemetry.addData("BeaconServo", " Beacon Position = " + beaconPosition);
                telemetry.addData("LiftServo: ", liftServo.getPosition());
                telemetry.update();

                idle();

            }

        }
        catch (Exception e) {}
        autoShoot.interrupt();

    }

public class AutoShoot extends Thread
{
    AutoShoot()
    {
        this.setName("AutoShoot");

    }
    @Override
    public void run()
    {
        try {
            while (!interrupted()) {
                if (gamepad1.x) {
                    doorServo.setPosition(0.3);
                    doorPosition = 0.1;
                    sleep(700);
                    doorServo.setPosition(0.9);
                    doorPosition = 0.3;
                    sleep(200);
                    shooterMotor.setPower(1);
                    sleep(800);
                    shooterMotor.setPower(0);
                }
                if (gamepad2.dpad_right)
                {
                    shooterMotor.setPower(1);
                    sleep(800);
                    shooterMotor.setPower(0);
                }

                idle();
            }
        }
        catch (InterruptedException e) {}

    }

}

        }

