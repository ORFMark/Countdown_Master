package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Mark on 5/17/2017.
 */
@Autonomous(name = "NewProject", group = "execises")
public class NewProject extends LinearOpMode{


    public void runOpMode() throws InterruptedException
    {
        RobotCommon robot = new RobotCommon();
        robot.intHardware(this);

        waitForStart();

            telemetry.addLine("Drive Forward, First leg");
            robot.autoDriveStraight(-0.4, -3250, -0.4, -3250, this);
            telemetry.addLine("First Turn");
            robot.autoRightTurn(-.25, -1800, this);
            telemetry.addLine("Getting Ball");
            sleep(1000);
            robot.intakeIn();
            robot.autoDriveStraight(-0.3, -2500, -0.3, -3000, this);
            robot.intakeStop();
        idle();
    }

}
