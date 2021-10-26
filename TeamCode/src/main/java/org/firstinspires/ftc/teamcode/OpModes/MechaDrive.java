package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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
     private double position=0;
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
        // rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        // rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

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
        double left;
        double right;
        double rf = -gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x;
        double rr = -gamepad1.right_stick_y + gamepad1.right_stick_x -gamepad1.left_stick_x;
        double lf = gamepad1.right_stick_y + gamepad1.right_stick_x  -gamepad1.left_stick_x;
        double lr = gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x;
        if(Math.abs(rf) >= 0.1 || Math.abs(rr) >= 0.1 || Math.abs(lf) >= 0.1 || Math.abs(lr) >= 0.1){
            rightFront.setPower(rf);
            rightRear.setPower(rr);
            leftRear.setPower(lr);
            leftFront.setPower(lf);
        }
        else{
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftRear.setPower(0);
            leftFront.setPower(0);
        }

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
