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
            robot.autoDrive(-0.25, -3000, -0.25, -3000, this);

    }

}
