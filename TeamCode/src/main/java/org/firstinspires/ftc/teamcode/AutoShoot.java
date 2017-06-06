package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Mark on 6/2/2017.
 */
@Autonomous(name = "AutoShoot", group = "exercises")
public class AutoShoot extends LinearOpMode {

    public void runOpMode()
    {
        RobotCommon robot = new RobotCommon();
        robot.intHardware(this);

        waitForStart();
        robot.intakeIn();
        robot.autoDriveStraight(-.3, -750, -.3, -750, this);
        robot.chooperOpen();
        sleep(300);
        robot.shooterMotor.setPower(-1);
        sleep(1000);
        robot.shooterMotor.setPower(0);
        robot.chooperClose();
        robot.intakeStop();

    }
}
