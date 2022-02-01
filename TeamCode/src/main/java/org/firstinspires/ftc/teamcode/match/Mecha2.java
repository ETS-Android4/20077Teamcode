package org.firstinspires.ftc.teamcode.match;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.text.DecimalFormat;


@TeleOp

public class Mecha2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor leftRear = hardwareMap.dcMotor.get("leftRear");
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor rightRear = hardwareMap.dcMotor.get("rightRear");
        DcMotorEx arm = (DcMotorEx) hardwareMap.dcMotor.get("arm");
         DcMotor arm2 = hardwareMap.dcMotor.get("arm2");
        DcMotor duck = hardwareMap.dcMotor.get("duck");
        Servo bucket = hardwareMap.servo.get("bucket");
        Servo cap = hardwareMap.servo.get("cap");
        CRServo flipper = hardwareMap.crservo.get("flipper");
        CRServo Tape = hardwareMap.crservo.get("Tape");
        double position = 0.5;
        boolean buttonDown = false;
        DecimalFormat df;
        int armPos = 0; //sets arm postion default ot 0
        int pos = 0;
        int pos2 = 0;
        double          CapOffset  = 0.0 ;                  // Servo mid position
        final double    Cap_SPEED  = 0.02 ;

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);


        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        armPos = arm.getCurrentPosition();//stores current postion


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, this is reversed!
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            double armPower= -gamepad2.right_stick_y;
            // Denominator65 is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            leftFront.setPower(frontLeftPower);
            leftRear.setPower(backLeftPower);
            rightFront.setPower(frontRightPower);
            rightRear.setPower(backRightPower);
//
            arm.setPower(armPower);
            //!!=========arm simple=======
            if (gamepad2.left_trigger >= 0.001) {
                arm.setPower(-1);



            } else if (gamepad2.right_trigger >= 0.001) {
                arm.setPower(1);


            } else {
                arm.setPower(0);



            }

            //tape
            if (gamepad1.left_bumper) {
                Tape.setPower(1);
            }else if (gamepad1.right_bumper) {
                Tape.setPower(-1);
            }else {
                        Tape.setPower(0);
                    }


                //---duck
            if (gamepad2.a) {
                duck.setPower(-0.7);
            } else if (gamepad2.b) {
                duck.setPower(0.7);
            } else {
                duck.setPower(0);
            }
            //---bucket
            if (gamepad2.x) {
                if (!buttonDown) {
                    position -= 0.05;
                    bucket.setPosition(position);
                }
                buttonDown = true;
            } else if (gamepad2.y) {
                if (!buttonDown) {
                    position += 0.05;
                    bucket.setPosition(position);
                }
                buttonDown = true;
            } else {
                buttonDown = false;
            }
            telemetry.addData("position: ", df.format(position));
            //Flipper
            if (gamepad2.left_bumper) {
                flipper.setPower(-1.0);
            }else if (gamepad2.right_bumper) {
                flipper.setPower(1.0);
            }else{
                flipper.setPower(0.0);
            }


            //auto arm for score
            if (gamepad2.dpad_down) {

                bucket.setPosition(0.4);

            }
            if (gamepad2.dpad_up) {



                bucket.setPosition(0.0);

            }
            if (gamepad2.dpad_right) {
              cap.setPosition(cap.getPosition()-0.001);
                }

            if (gamepad2.dpad_left){
                cap.setPosition(cap.getPosition()+0.001);
            }


           // if (gamepad2.dpad_right)
               // CapOffset += Cap_SPEED;
            //else if (gamepad2.dpad_left)
               // CapOffset -= Cap_SPEED;
            //CapOffset = Range.clip(CapOffset, -0.5, 0.5);
        }
    }
}