package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Mark on 6/2/2017.
 */
public class AutoShoot extends LinearOpMode {

    public void runOpMode()
    {
        RobotCommon robot = new RobotCommon();
        robot.intHardware(this);

        waitForStart();
        robot.autoDriveStraight(-.3, -500, -.3, -500, this);
        robot.intakeIn();
        robot.autoDriveStraight(-.3, -500, -.3, -500, this);
        robot.chooperOpen();
        sleep(300);
        robot.shooterMotor.setPower(-1);
        sleep(1000);
        robot.shooterMotor.setPower(0);
        robot.chooperClose();
        robot.intakeStop();

    }
}
