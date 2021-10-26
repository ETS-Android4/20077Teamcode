package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.text.DecimalFormat;

@TeleOp(name = "MechaDrive")
public class MechaDrive extends OpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor arm;
    private DcMotor arm2;
    private DcMotor duck;
    private Servo bucket;
    private CRServo flipper;
    // private DcMotor liftMotor;

    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
     //for bucket servo
     private double position=0.5;
    private boolean buttonDown=false;
    DecimalFormat df;

    @Override
    public void init(){
        //motors for drivetrain
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
       //2 motors for arm swing
        arm = hardwareMap.get(DcMotor.class,"arm");
        arm2 = hardwareMap.get(DcMotor.class,"arm2");
        //motor for duck spinner
        duck = hardwareMap.get(DcMotor.class, "duck");
        //servo for bucket
        bucket = hardwareMap.get(Servo.class,"bucket");
        //Continuous servo for intake
        flipper =hardwareMap.get(CRServo.class,"flipper");
        //liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
         rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        //More servo stuff
        bucket.setPosition(position);
        df= new DecimalFormat();
        df.setMaximumFractionDigits(2);
        //set arm motors to hold when not in use
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int pos = 0;
        int pos2 = 0;

        telemetry.addData("Say", "Hello Driver");
    }
    @Override
    public void loop() {
        drive();
        arm();
        duck();
        bucket();
        flipper();

    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
    //-----------------------------------------Drive
    public void drive() {
        double y = -gamepad1.left_stick_y; // Remember, this is reversed!
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
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


    }
    //-------------------------Arm-------------------
    public void arm(){

        if(gamepad2.right_trigger>=0.1){
            arm.setPower( -.6);
            arm2.setPower(.6);
        }

        else if(gamepad2.left_trigger>=0.1){
            arm.setPower(.6);
            arm2.setPower(-.6);
        }
        else {
            arm.setPower(0);
            arm.setPower(0);
        }


    }
    public void duck(){

        if(gamepad2.a){
            duck.setPower(/*-gamepad2.right_trigger*/ -.4);
        }

        else if(gamepad2.b){
            duck.setPower(.4);
        }
        else {
            duck.setPower(0);
        }


    }

    public void bucket() {
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
        telemetry.addData("position: ",df.format(position));
    }


    public void flipper(){
        if(gamepad2.right_bumper){
            flipper.setPower(1);
        }
        else if(gamepad2.left_bumper){
            flipper.setPower(-1);
        }
        else {
            flipper.setPower(0);
        }
    }
}
