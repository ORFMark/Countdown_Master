package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp(name="MRBDriveTest", group="Exercises")
//@Disabled
public class MRBDriveTest extends LinearOpMode {
    DcMotor leftMotor, rightMotor ,  intakeMotor, shooterMotor ;
    Servo doorServo, beaconServo;
    ColorSensor beaconColor;
    float leftY, rightY, linputY, rinputY;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    Thread autoShoot = new AutoShoot();
    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor = hardwareMap.dcMotor.get("intake_motor");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        doorServo = hardwareMap.servo.get("door_servo");
        beaconServo = hardwareMap.servo.get("beacon_servo");
        beaconColor = hardwareMap.colorSensor.get("beacon_color");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        ProgramName = "MRB_11_17_16_2";
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramName);
        telemetry.update();

        // wait for start button.
        //shooterMotor.setPower(0);


        //intakeMotor.setPower(0);


        waitForStart();
        autoShoot.start();
        doorServo.setPosition(0.5);

        try {


            while (opModeIsActive()) {
                //shooterMotor.setPower(0);


                // Drive Code
                leftY = gamepad1.left_stick_y;
                rightY = gamepad1.right_stick_y;
                //Stick Dead Zone
                if (leftY <= .2 && leftY >= -0.2)
                    linputY = 0;
                else
                    linputY = leftY;

                if (rightY <= .2 && rightY >= -.2)
                    rinputY = 0;
                else
                    rinputY = rightY;
/*
            rinputY = rightY;
            linputY = leftY;
            // end drive Code
*/
                //Gamepad 1 Commands
                if (gamepad1.dpad_up)
                    intakeMotor.setPower(1);
                if (gamepad1.dpad_down)
                    intakeMotor.setPower(-1);
                if (gamepad1.a)
                    intakeMotor.setPower(0);

                if (gamepad1.y)
                    shooterMotor.setPower(0);

                // Gamepad 2 commands
                if (gamepad2.a) {
                    beaconPosition = -0.5;
                }
                if (gamepad2.b) {
                    beaconPosition = 0.6;
                }
                if (gamepad2.y) {
                    beaconPosition = 1.0;
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
                    colorState = "Blue";
             }
                doorServo.setPosition(doorPosition);
                beaconServo.setPosition(beaconPosition);

                leftMotor.setPower(Range.clip(linputY, -1.0, 1.0));
                rightMotor.setPower(Range.clip(rinputY, -1.0, 1.0));

                telemetry.addData("Mode", "running");
                telemetry.addLine("ProgramName = " + ProgramName);
                telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
                telemetry.addData("output", "left=" + linputY + " right=" + rinputY);
                telemetry.addData("intakeStructure", " shooter =" + shooterMotor.getPower() + " intake =" + intakeMotor.getPower());
                telemetry.addData("DoorServo", " Door Position = " + doorServo.getPosition());
                telemetry.addData("BeaconServo", " Beacon Position = " + beaconPosition);
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
                    doorServo.setPosition(0.1);
                    doorPosition = 0.1;
                    sleep(700);
                    doorServo.setPosition(0.5);
                    doorPosition = 0.5;
                    sleep(200);
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

