package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class BlueDuck extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private CRServo flipper;
    private Servo   bucket;
    private DcMotor duck;
    //private DcMotor liftMotor;
    //add arm and arm2 and
    private DcMotor arm;
    private DcMotor arm2;
    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        flipper = hardwareMap.get(CRServo.class,"flipper");
        bucket = hardwareMap.get(Servo.class,"bucket");
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        duck = hardwareMap.get(DcMotor.class, "duck");
        // liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        while (opModeIsActive()) {
            //set motors to move to shipping tower.
            //straight
            arm.setPower(-.1);
            arm2.setPower(.1);
            sleep(5);
            //move off wall
            move(100, 100, 100, 100);
            //turn
            move(925, 925, -1010, -1010);
            //move to duck wheel
            move(1110,1110,1110,1110);
            //stop
            move(0, 0, 0, 0);

            // raise arms and set bucket and flipper
            //bucketArm(1000,0.6);
           /*bucket.setPosition(1.0);

            flipper.setPower(-1);
            sleep(400);
            flipper.setPower(0);
           bucket.setPosition(0.5);
           bucket.setPosition(0.0);
           sleep(4000);*/
            //move to duck wheel
            // move(1000,1000,1000,1000);
            duck(5000);
            //move to square
            arm.setPower(-.1);
            arm2.setPower(.1);
            move(-200,-200,-200,-200);
            //turn after duck
            move(450,-450,450,-450);
            //go towards warehouse
            move(-4700,-4700,-4700,-4700);
            sleep(5);
            arm.setPower(-.1);
            arm2.setPower(.1);
            sleep(13000);
            //move(-1000,-1000,-1000,-1000);



        }

    }


    //----------------------------encoder-----------------
    public void move(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Path0",  "Starting at %7d :%7d",
                rightFront.getCurrentPosition(),
                rightRear.getCurrentPosition(),
                leftRear.getCurrentPosition(),
                leftFront.getCurrentPosition());
        telemetry.update();

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        rightRear.setPower(0.3);
        rightFront.setPower(0.3);

        leftFront.setPower(0.3);
        leftRear.setPower(0.3);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);

            telemetry.addData("Path1",  "Running to %7d :%7d", rf,lf,rb,lb);
            telemetry.addData("Path2",  "Running at %7d :%7d",
                    rightFront.getCurrentPosition(),
                    rightRear.getCurrentPosition(),
                    leftRear.getCurrentPosition(),
                    leftFront.getCurrentPosition());

        }
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightRear.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);


    }
    //================bucketArm=============================
    //input arm encoder and servo angle, should move to arm postion, move bucket and flip block
    public void bucketArm(int encoder,double servo){
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(encoder);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(0.7);
        arm2.setPower(-0.7);
        bucket.setPosition(servo);
        flipper.setPower(1);
        while (arm.isBusy()){
            sleep(50);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setPower(0);
            arm2.setPower(0);
        }
    }
    //==========================duck
    public void duck(int time){
        duck.setPower(-.4);
        sleep(time);
        duck.setPower(0);




    }
    //----------------------Lift------------
    public void arms(int encod){
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setTargetPosition(encod);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(0.6);
        arm2.setPower(-0.6);
        while (arm.isBusy()){
            sleep(50);
        }
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setPower(0);

    }
}

