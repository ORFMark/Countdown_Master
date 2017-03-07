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
    Robot robot;
    // called when init button is  pressed.



    @Override
    public void runOpMode() throws InterruptedException {
        robot.IntHardware();
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + robot.ProgramName);
        telemetry.update();

        // wait for start button.
        //shooterMotor.setPower(0);


        //intakeMotor.setPower(0);
        liftDrive = false;
        robot.chooperClose();
        robot.liftServoIn();
        robot.intakeStop();
        robot.ceaseFire();
        leftY = 0;
        rightY = 0;
        robot.liftStop();
        liftDrive = false;
        waitForStart();
        autoShoot.start();
        robot.chooperClose();
        robot.liftServoIn();




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
                    robot.intakeIn();
                if (gamepad1.dpad_down)
                    robot.intakeOut();
                if (gamepad1.a)
                    robot.intakeStop();

                if (gamepad1.y)
                    robot.ceaseFire();

                if (gamepad1.b)
                {
                    liftDrive = !liftDrive;
                }

                if (gamepad1.left_bumper) {
                    robot.liftServoIn();
                }
                if (gamepad1.right_bumper) {
                    robot.liftServoOut();

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
                    robot.intakeJog();
                }

                if (gamepad2.right_bumper)
                {
                    robot.chooperClose();
                }
                if (gamepad2.left_bumper)
                {
                    robot.chooperOpen();
                }
                if (gamepad2.dpad_up)
                {
                    robot.liftUp();
                }
                if (gamepad2.dpad_down)
                {
                    robot.liftDown();
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
                        robot.chooperOpen();
                        sleep(200);
                        robot.chooperClose();
                        sleep(200);
                        robot.fire();
                        sleep(700);
                        robot.ceaseFire();
                    }
                    if (gamepad2.dpad_right)
                    {
                        robot.fire();
                    }

                    idle();
                }
            }
            catch (InterruptedException e) {}

        }
    }

}

