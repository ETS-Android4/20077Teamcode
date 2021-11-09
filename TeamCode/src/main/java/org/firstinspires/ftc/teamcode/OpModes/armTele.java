package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class armTele extends LinearOpMode {
   DcMotorEx arm;
   DcMotorEx arm2;

    @Override
    public void runOpMode() {
      arm = hardwareMap.get(DcMotorEx.class, "arm");
      arm2 = hardwareMap.get(DcMotorEx.class,"arm2");
      arm.setDirection(DcMotorSimple.Direction.REVERSE);
      arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        telemetry.addData("Encoder value", arm.getCurrentPosition());
        telemetry.addData("Encoder value", arm2.getCurrentPosition());

        telemetry.update();

        while (opModeIsActive()) {
            if(gamepad1.dpad_left) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());

                telemetry.update();
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                arm.setTargetPosition(100);
                arm2.setTargetPosition(100);


                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                arm.setPower(.3);
                arm2.setPower(.3);
                while (arm.isBusy() && arm2.isBusy()) {
                    sleep(50);

                    telemetry.addData("Encoder value", arm.getCurrentPosition());
                    telemetry.addData("Encoder value", arm2.getCurrentPosition());

                    telemetry.update();
                }
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                arm.setPower(0);
                arm2.setPower(0);
            }
        }
    }
}