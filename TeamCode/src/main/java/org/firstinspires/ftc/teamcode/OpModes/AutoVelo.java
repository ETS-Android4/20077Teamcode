package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous
public class AutoVelo extends LinearOpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private CRServo flipper;
    private Servo bucket;
    private DcMotorEx duck;
    private DcMotorEx arm;
    private DcMotorEx arm2;

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        flipper = hardwareMap.get(CRServo.class, "flipper");
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm2 = hardwareMap.get(DcMotorEx.class, "arm2");
        bucket = hardwareMap.get(Servo.class, "bucket");
        duck = hardwareMap.get(DcMotorEx.class, "duck");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        telemetry.addData("Encoder value", arm.getCurrentPosition());
        telemetry.addData("Encoder value", arm2.getCurrentPosition());

        telemetry.update();
        while (opModeIsActive()){
            move(1000,1000,1000,1000);
            arm(125);
        }
    }

    //=========================drive=========================
    public void move(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        rightRear.setVelocity(200);
        rightFront.setVelocity(200);
        leftFront.setVelocity(200);
        leftRear.setVelocity(200);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);

        }
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRear.setVelocity(0);
        leftFront.setVelocity(0);
        rightFront.setVelocity(0);
        leftRear.setVelocity(0);


    }

    //===============================arms=======================================
    public void arm(int encod) {


        arm.setTargetPosition(encod);
        arm2.setTargetPosition(encod);

        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        arm.setVelocity(400);
        arm2.setVelocity(400);
        while (arm.isBusy() && arm2.isBusy()) {

            telemetry.addData("Encoder value", arm.getCurrentPosition());
            telemetry.addData("Encoder value", arm2.getCurrentPosition());

            telemetry.update();
        }
        bucket.setPosition(0.7);
        flipper.setPower(-1);
        sleep(200);
        //may have to add a move back?

        arm.setVelocity(0);
        arm2.setVelocity(0);
    }
}