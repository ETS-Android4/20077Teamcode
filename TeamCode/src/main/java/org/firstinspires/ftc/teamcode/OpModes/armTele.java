package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class armTele extends LinearOpMode {
   DcMotorEx arm;
   DcMotorEx arm2;
   Servo bucket;
   CRServo flipper;

    @Override
    public void runOpMode() {
      arm = hardwareMap.get(DcMotorEx.class, "arm");
      arm2 = hardwareMap.get(DcMotorEx.class,"arm2");
      bucket = hardwareMap.get(Servo.class,"bucket");
      flipper = hardwareMap.get(CRServo.class, "flipper");
      //arm.setDirection(DcMotorSimple.Direction.REVERSE);
      arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        telemetry.addData("Encoder value", arm.getCurrentPosition());
        telemetry.addData("Encoder value", arm2.getCurrentPosition());
        telemetry.addData("servoPosiont", bucket.getPosition());

        telemetry.update();

        while (opModeIsActive()) {
        EncoderControl(-900, gamepad1.a);
        EncoderControl(-60, gamepad1.b);
        EncoderControl(-200, gamepad1.y);
        EncoderControl(-500,gamepad1.x);
            /*if(gamepad1.dpad_left) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());

                telemetry.update();
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                arm.setTargetPosition(100);
                arm2.setTargetPosition(100);


                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                arm.setPower(.5);
                arm2.setPower(.5);
                while (arm.isBusy() && arm2.isBusy()) {
                    sleep(500);
                    bucket.setPosition(0.0);
                    sleep(500);
                    flipper.setPower(1);
                    sleep(500);
                    flipper.setPower(0);
                    sleep(500);


                    telemetry.addData("Encoder value", arm.getCurrentPosition());
                    telemetry.addData("Encoder value", arm2.getCurrentPosition());

                    telemetry.update();
                }
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm.setPower(0);
                arm2.setPower(0);
            }*/
        }
    }
    //=============================Encoder controller===============
    public void EncoderControl(int encoder,boolean button){
        if(button) {
            telemetry.addData("Encoder value", arm.getCurrentPosition());
            telemetry.addData("Encoder value", arm2.getCurrentPosition());

            telemetry.update();
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            arm.setTargetPosition(encoder);
            arm2.setTargetPosition(encoder);


            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            arm.setPower(.3);

            /*while (arm.isBusy() && arm2.isBusy()) {
                sleep(500);
                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());


                telemetry.update();
            }*/
                sleep(1000);
                bucket.setPosition(1.0);
                sleep(1500);
                flipper.setPower(-1);
                sleep(1500);
                flipper.setPower(0);
                sleep(1500);
                bucket.setPosition(0.0);
                sleep(500);


                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());


                telemetry.update();
            arm.setTargetPosition(10);
            arm2.setTargetPosition(10);


            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            arm.setPower(.4);
            arm2.setPower(.1);
            arm.setTargetPosition(0);
            arm2.setTargetPosition(0);


            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            arm.setPower(0);

            arm2.setPower(0);
        }
    }
    //==================move

}