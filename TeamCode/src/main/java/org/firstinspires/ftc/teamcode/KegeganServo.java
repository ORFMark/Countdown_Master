package org.firstinspires.ftc.teamcode;

/**
 * Created by Mark on 11/12/2016.
 */

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Servo", group="Exercises")
public class KegeganServo extends LinearOpMode
        {
        Servo Servo1, Servo2, Servo3, Servo4;
            double servoPosition;
            @Override
            public void runOpMode() throws InterruptedException
            {
                Servo1= hardwareMap.servo.get("1");
                Servo2 = hardwareMap.servo.get("2");
                Servo3 = hardwareMap.servo.get("3");
                Servo4 = hardwareMap.servo.get("4");

                waitForStart();
                servoPosition = 0.0;

                while (opModeIsActive())
                {
                    if (gamepad1.a)
                    {
                        servoPosition = 1.0;
                    }
                    if (gamepad1.b) {
                        servoPosition = -1.0;
                    }

                    Servo1.setPosition(servoPosition);
                    Servo2.setPosition(servoPosition);
                    Servo3.setPosition(servoPosition);
                    Servo4.setPosition(servoPosition);
                    idle();
                }


            }
}
