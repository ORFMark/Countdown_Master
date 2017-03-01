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
    DcMotor leftMotor, rightMotor, intakeMotor1, intakeMotor2, shooterMotor, liftMotor;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    float leftY, rightY, linputY, rinputY, shooterPower, intakePower, liftPower;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    public boolean liftDrive;
    Thread autoShoot = new AutoShoot();
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
    public void runOpMode() throws InterruptedException {
        IntHardware();
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramName);
        telemetry.update();

        // wait for start button.
        //shooterMotor.setPower(0);


        //intakeMotor.setPower(0);
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
        autoShoot.start();
        chooperClose();
        liftServoIn();




        try {
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
                        linputY = leftY * (float) 0.6;

                    if (rightY <= .2 && rightY >= -.2)
                        rinputY = 0;
                    else
                        rinputY = rightY * (float) 0.6;
                }
                else {
                    rinputY = rightY;
                    linputY = leftY;
                }
            // end drive Code


                //Gamepad 1 Commands
                if (gamepad1.dpad_up)
                    intakeIn();
                if (gamepad1.dpad_down)
                    intakeOut();
                if (gamepad1.a)
                    intakeStop();

                if (gamepad1.y)
                    ceaseFire();

                if (gamepad1.b)
                {
                    liftDrive = !liftDrive;
                }

                if (gamepad1.left_bumper) {
                    liftServoIn();
                }
                if (gamepad1.right_bumper) {
                    liftServoOut();

                }
                // Gamepad 2 commands
                if (gamepad2.a) {
                    /*
                    beaconPosition = 0.5;
                    beaconServo.setPosition(0.5);
                    */
                }
                if (gamepad2.b) {
                    //beaconPosition = 0.6;
                }
                if (gamepad2.y) {
                    /*beaconPosition = 1.0;
                    beaconServo.setPosition(1.0);*/
                }
                if (gamepad2.x)
                {

                }
                if (gamepad2.dpad_left)
                {
                    intakeJog();
                }

                if (gamepad2.right_bumper)
                {
                    chooperClose();
                }
                if (gamepad2.left_bumper)
                {
                    chooperOpen();
                }
                if (gamepad2.dpad_up)
                {
                   liftUp();
                }
                if (gamepad2.dpad_down)
                {
                    liftDown();
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
                shooterMotor.setPower(Range.clip(shooterPower, -1.0, 1.0));

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
                    chooperOpen();
                    sleep(700);
                    chooperClose();
                    sleep(200);
                    fire();
                }
                if (gamepad2.dpad_right)
                {
                    fire();
                }

                idle();
            }
        }
        catch (InterruptedException e) {}

    }
}

        }

