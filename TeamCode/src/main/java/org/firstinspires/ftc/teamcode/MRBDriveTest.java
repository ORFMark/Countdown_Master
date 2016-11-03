package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="MRBDriveTest", group="Exercises")
//@Disabled
public class MRBDriveTest extends LinearOpMode {
    DcMotor leftMotor, rightMotor ,  intakeMotor, shooterMotor ;
    float leftY, rightY, linputY, rinputY;
    String ProgramName;
    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        intakeMotor = hardwareMap.dcMotor.get("intake_motor");
        shooterMotor = hardwareMap.dcMotor.get("shooter_motor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        ProgramName = "MRB_11_01_16_1";
        telemetry.addData("Mode", "waiting");
        telemetry.addLine("ProgramName = " + ProgramName);
        telemetry.update();

        // wait for start button.
        //shooterMotor.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        //intakeMotor.setPower(0);
        waitForStart();

        while (opModeIsActive()) {
            //shooterMotor.setPower(0);

            // Drive Code
            leftY = gamepad1.left_stick_y;
            rightY = gamepad1.right_stick_y;
            //Stick Dead Zone
            if (leftY <= .2 && leftY >= -0.2)
                linputY=0;
            else
                linputY=leftY;

            if (rightY <= .2 && rightY >= -.2)
                rinputY = 0;
            else
                rinputY=rightY;

            rinputY = rightY;
            linputY = leftY;
            // end drive Code

            //Gamepad 1 Commands
          if (gamepad1.dpad_up)
                intakeMotor.setPower(1);
            if (gamepad1.dpad_down)
                intakeMotor.setPower(-1);
            if (gamepad1.a)
                intakeMotor.setPower(0);
            if (gamepad1.x)
                shooterMotor.setPower(1);
            if (gamepad1.y)
                shooterMotor.setPower(0);

            leftMotor.setPower(Range.clip(linputY, -1.0, 1.0));
            rightMotor.setPower(Range.clip(rinputY, -1.0, 1.0));

            telemetry.addData("Mode", "running");
            telemetry.addLine("ProgramName = " + ProgramName);
            telemetry.addData("sticks", "  left=" + leftY + "  right=" + rightY);
            telemetry.addData("output", "left=" + linputY + " right=" + rinputY);
            telemetry.addData("intakeStructure", " shooter =" + shooterMotor.getPower() + " intake =" + intakeMotor.getPower());
            telemetry.update();

            idle();
        }
    }
/*public class AutoShoot extends Thread
{
    AutoShoot()
    {
        this.setName(Autoshoot);

    }
    public void run()
    {

    }
}
*/
        }

