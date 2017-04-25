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
    public float leftY, rightY;
    public boolean liftDrive;
    RobotCommon robot = new RobotCommon();
    Thread autoShoot = new AutoShoot();
    //Robot robot = new Robot();
    // called when init button is  pressed.



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
        robot.intHardware(this);
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + robot.ProgramVersion);
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
                robot.tankdrive(leftY, rightY, false);
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
                    sleep(100);
                }

                if (gamepad1.left_bumper) {
                    //liftServoIn();
                }
                if (gamepad1.right_bumper) {
                    //liftServoOut();

                }
                // Gamepad 2 commands
                if (gamepad2.a) {
					/*
                    beaconPosition = 0.5;
                    beaconServo.setPosition(0.5);
					 */
                }
                if (gamepad2.b) {
                    robot.liftServoIn();
                }
                if (gamepad2.y) {
					robot.liftServoOut();
                }
                if (gamepad2.x)
                {

                }
                if (gamepad2.dpad_left)
                {
                    robot.intakeJog(this);
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
                    robot.liftStop();
                }


                telemetry.addData("Mode", "running");
                telemetry.addLine(robot.ProgramVersion);
                telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
                telemetry.addData("DrivePower: ", "Left: " + robot.leftMotor.getPower() + "Right: " + robot.rightMotor.getPower());
                telemetry.addData("shooterEncoder: ",robot.shooterMotor.getCurrentPosition());
                if (liftDrive == false)
                {
                    telemetry.addLine("Normal Driving");
                }
                if (liftDrive == true) {
                    telemetry.addLine("Lift Drive");
                }
                telemetry.addData("intakeStructure", " shooter =" + robot.shooterMotor.getPower() + " intake =" + robot.intakeMotor.getPower());
                telemetry.addData("lift", robot.liftMotor1.getPower() + " , ", robot.liftMotor2.getPower());
                telemetry.addData("DoorServo", " Door Position = " + robot.doorServo.getPosition());
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
                        robot.chooperOpen();
                        sleep(700);
                        robot.chooperClose();
                        sleep(200);
                        robot.fire();
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

