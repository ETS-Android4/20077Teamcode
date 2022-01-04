package org.firstinspires.ftc.teamcode.match;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleAll")
public class TeleAll extends LinearOpMode {


    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx arm;
    private DcMotorEx arm2;
    private DcMotorEx duck;
    private Servo bucket;
    private CRServo flipper;
    private Servo cap;


    @Override
    public void runOpMode() throws InterruptedException {
        //motors for drivetrain
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        //2 motors for arm swing
        arm = hardwareMap.get(DcMotorEx.class, "arm");
        arm2 = hardwareMap.get(DcMotorEx.class, "arm2");
        //motor for duck spinner
        duck = hardwareMap.get(DcMotorEx.class, "duck");
        //servo for bucket
        bucket = hardwareMap.get(Servo.class, "bucket");
        //Continuous servo for intake
        flipper = hardwareMap.get(CRServo.class, "flipper");
        //for TSE scoring
        cap = hardwareMap.get(Servo.class,"cap");

        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Say", "Hello Driver");
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Encoder value", arm.getCurrentPosition());
           telemetry.addData("capPos", cap.getPosition());
            telemetry.addData("servoPos", bucket.getPosition());
            telemetry.addData("right front", leftFront.getCurrentPosition());
            telemetry.addData("right rear", rightRear.getCurrentPosition());
            telemetry.addData("left rear", leftRear.getCurrentPosition());
            telemetry.addData("right front", rightFront.getCurrentPosition());

            telemetry.update();
        }
    }
}
