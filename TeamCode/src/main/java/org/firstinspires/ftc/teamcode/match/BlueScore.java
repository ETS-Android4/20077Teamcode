package org.firstinspires.ftc.teamcode.match;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class BlueScore extends LinearOpMode {
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

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        flipper = hardwareMap.get(CRServo.class,"flipper");
        bucket = hardwareMap.get(Servo.class,"bucket");
        arm = hardwareMap.get(DcMotor.class, "arm");

        duck = hardwareMap.get(DcMotor.class, "duck");
        // liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();
// Robot go vroom vroom
        while (opModeIsActive()) {

            //set motors to move to shipping tower.
            //straight
            //raise arm
            arms(-2255);
            //move off wall
            movefast(-450,-450,-450,-450);
            //turn toward goal
            movefast(350,350,-350,-350);
            //move forward to goal
            movefast(-575,-575,-575,-575);
            score();
            //back away from goal
            movefast(500,500,500,500);
            //turn to line up to warehouse
            movefast(-350,-350,350,350);
            //back to wall
            movefast(-1000,-1000,1000,1000);
            //lower arm for getting block
            bucket.setPosition(0.25);
            down();

            //strafe
            movefast(-1100, 1100, 1100, -1100);
            //move into warehouse
            flipper.setPower(1);
            movefast(-2200,-2200,-2200,-2200);
            //collect block

            movefast(-250, 250, 250, -250);
            movefast(250, -250, -250, 250);
            //updown
            //movefast(200,200,200,200);
            //movefast(-200,-200,-200,-200);
            //movefast(500,500,500,500);
            //movefast(-500,-500,-500,-500);

            bucket.setPosition(0.25);
            flipper.setPower(0);
            arms(-3000);
            //strafe to wall
            movefast(-800, 800, 800, -800);
            //move back
            movefast(2050,2050,2050,2050);
            //turn
            movefast(1000,1000,-1000,-1000);
            //move forward
            movefast(-850,-850,-850,-850);
            movefast(300,300,-300,-300);
            //turn to goal
            //movefast(400,400,-400,-400);
           // movefast(-200,-200,-200,-200);
            score();
            //turn back
            movefast(-350,-350,350,350);
            movefast(600,600,600,600);
            //face warehouse
            movefast(-900,-900,900,900);
            //strafe to wall
            movefast(-1100, 1100, 1100, -1100);
            //park
            down();
            bucket.setPosition(0.25);
            movefast(-3250,-3250,-3250,-3250);
           sleep(50000);
            /* flipper.setPower(1);
            //movefast(250, -250, -250, 250);
            //movefast(-250, 250, 250, -250);

            //updown
            movefast(-200,-200,-200,-200);

            bucket.setPosition(0.0);
            flipper.setPower(0);
            arms(-800);
            //strafe to wall
            movefast(-450, 450, 450, -450);
            //move back
            movefast(2000,2000,2000,2000);
            //turn
            movefast(1000,1000,-1000,-1000);
            //move forward
            movefast(-800,-800,-800,-800);
            //turn to goal
            movefast(500,500,-500,-500);
            movefast(-100,-100,-100,-100);
            score();
            //turn back
            movefast(-350,-350,350,350);
            movefast(800,800,800,800);
            //face warehouse
            movefast(-1000,-1000,1000,1000);
            //strafe to wall
            movefast(-500, 500, 500, -500);
            //park
            movefast(-2000,-2000,-2000,-2000);

            sleep(5000000);
            //move off wall
            /*arms(-100);
            move(-100, -100, -100, -100);
            //turn
            move(925, 925, -1000, -1000);
            //move to duck wheel
            move(1100,1100,1100,1100);
            sleep(200);
            moveslow(100,100,100,100);
            moveslow(-50,-50,50,50);

            duck(4500);



            movefast(-200,-200,-200,-200);
            //turn after duck
            move(150,150,-150,-150);
            //forward
            move(-500,-500,-500,-500);
            //strafe
            movefast(500, -500, -500, 500);
            //go towards warehouse
            movefast(-4700,-4700,-4700,-4700);
            sleep(5);

            move(-250,-250,250,250);
            move(-250,-250,-250,-250);
            sleep(13000);
            move(-1000,-1000,-1000,-1000);
            */


        }

    }

//=================movefast
public void movefast(int rf, int rb, int lf, int lb) {
    rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    rightFront.setTargetPosition(rf);
    rightRear.setTargetPosition(rb);
    leftFront.setTargetPosition(lf);
    leftRear.setTargetPosition(lb);

    rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    rightRear.setPower(.7);
    rightFront.setPower(.7);

    leftFront.setPower(.7);
    leftRear.setPower(.7);

    while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
        sleep(50);



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

    //----------------------------encoder-----------------
    public void move(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        rightRear.setPower(0.5);
        rightFront.setPower(0.5);

        leftFront.setPower(0.5);
        leftRear.setPower(0.5);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);



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

    //==========================duck
    public void duck(int time){
        duck.setPower(-.4);
        sleep(time);
        duck.setPower(0);




    }
    //----------------------ARMS------------
    public void arms(int encod) {
        // arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setTargetPosition(encod);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm.setPower(1);

        while (arm.isBusy()) {
            sleep(50);
        }

    }
    //================Score
    public void score() {

        bucket.setPosition(0.55);

        flipper.setPower(-1);
        sleep(1000);
        flipper.setPower(0);

        bucket.setPosition(0.0);


    }
    //===================down
    public void down(){


        arm.setTargetPosition(-50);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        arm.setPower(-1);

        while (arm.isBusy()) {
            sleep(50);
        }

    }
    //----------------------------MOVESLOW-----------------
    public void moveslow(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


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

}

