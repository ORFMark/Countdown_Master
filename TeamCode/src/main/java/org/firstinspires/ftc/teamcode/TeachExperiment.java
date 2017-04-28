package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Mark on 4/27/2017.
 */
@TeleOp(name = "MRBDriveCode", group = "exercises")
public class TeachExperiment extends LinearOpMode {
    DcMotor leftMotor, rightMotor;
    public void runOpMode() throws InterruptedException
    {

        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            leftMotor.setPower(gamepad1.left_stick_y);
            rightMotor.setPower(gamepad1.right_stick_y);
            telemetry.addLine("Motors: " + "Left: " + leftMotor.getPower() + " , Right: " + rightMotor.getPower());
        }

    }
}
