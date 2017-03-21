package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.autoCommon;
import org.firstinspires.ftc.teamcode.Robot;



@TeleOp(name="MRBDriveTest", group="Exercises")
//@Disabled
public class MRBDriveTest extends LinearOpMode {
    DcMotor leftMotor, rightMotor, intakeMotor, shooterMotor, liftMotor1, liftMotor2;
    Servo doorServo, beaconServo, liftServo;
    ColorSensor beaconColor;
    String ProgramVersion = "MRB_3_6_17_1";
    float leftY, rightY, linputY, rinputY, oldStick = 0;
    String ProgramName, colorState;
    double doorPosition, beaconPosition;
    public boolean liftDrive;
    Thread autoShoot = new AutoShoot();
    //Robot robot = new Robot();
    // called when init button is  pressed.

    public void liftServoIn() {
        liftServo.setPosition(0.7);
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

    /*public float slew(float StickValue)
    {
        float change = oldStick - StickValue;
        if (StickValue == 0 && change == 0)
            change = 0;
        else if (change >= .1 && StickValue >= 0)f
            change = (float) 0.1;
        else if (change <= -.1 && StickValue <= 0)
            change = (float) -0.1;
        else
            change = StickValue;
        oldStick = change;
        return change;
    }
    */


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
        liftServo = hardwareMap.servo.get("lift_servo");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramVersion);
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
                leftMotor.setPower(linputY);
                rightMotor.setPower(rinputY);
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
                    liftStop();
                }

                leftMotor.setPower(Range.clip(linputY, -1.0, 1.0));
                rightMotor.setPower(Range.clip(rinputY, -1.0, 1.0));


                telemetry.addData("Mode", "running");
                telemetry.addLine(ProgramVersion);
                telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
                telemetry.addData("shooterEncoder: ",shooterMotor.getCurrentPosition());
                if (liftDrive == false)
                {
                    telemetry.addLine("Normal Driving");
                }
                if (liftDrive == true)
                {
                    telemetry.addLine("Lift Drive");
                }
                telemetry.addData("output", "left=" + linputY + " right=" + rinputY);
                telemetry.addData("intakeStructure", " shooter =" + shooterMotor.getPower() + " intake =" + intakeMotor.getPower());
                telemetry.addData("lift", liftMotor1.getPower() + " , ", liftMotor2.getPower());
                telemetry.addData("DoorServo", " Door Position = " + doorServo.getPosition());
                //telemetry.addData("LiftServo: ", robot.liftServo.getPosition());
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
                        ceaseFire();
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

