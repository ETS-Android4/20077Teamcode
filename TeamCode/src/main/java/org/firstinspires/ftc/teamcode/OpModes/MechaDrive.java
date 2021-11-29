package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

    //for bucket servo to start at a specific angle
    private double position = 0.5;
    private boolean buttonDown = false;
    DecimalFormat df;

    @Override
    public void init() {
        //motors for drivetrain
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        //2 motors for arm swing
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm2 = hardwareMap.get(DcMotor.class, "arm2");
        //motor for duck spinner
        duck = hardwareMap.get(DcMotor.class, "duck");
        //servo for bucket
        bucket = hardwareMap.get(Servo.class, "bucket");
        //Continuous servo for intake
        flipper = hardwareMap.get(CRServo.class, "flipper");
        //liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
        //arm.setDirection(DcMotorSimple.Direction.REVERSE);
        //reverse motors for drivetrain
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //More servo stuff
        //set position to start



        df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        //set arm motors to hold when not in use
        //arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int pos = 0;
        int pos2 = 0;

        telemetry.addData("Say", "Hello Driver");
    }

    @Override
    public void loop() {
        drive();
        arm();
        duck();
        EncoderControl(-130,gamepad2.x);
        //EncoderControl(40,gamepad2.y);
        flipper();
        //must set target interger for arm



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
    public void arm() {

        if (gamepad2.left_trigger>= 0.1) {
            arm.setPower(-.5);
            arm2.setPower(-.5);

        } else if (gamepad2.right_trigger >= 0.1) {
            arm.setPower(.5);
            arm2.setPower(.5);

        } else {
            arm.setPower(0);
            arm.setPower(0);

        }


    }

    public void duck() {

        if (gamepad2.a) {
            duck.setPower(-0.7);
        } else if (gamepad2.b) {
            duck.setPower(0.7);
        } else {
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
        telemetry.addData("position: ", df.format(position));

    }

    //==========================set Bucket---------------
    public void setBucket(double sp) {
        if (gamepad2.dpad_right) {
            bucket.setPosition(sp);
        } else if(gamepad2.dpad_left){
            bucket.setPosition(0.4);
        }else{
            bucket.setPosition(0);
        }

    }


    public void flipper() {
        if (gamepad2.right_bumper) {
            flipper.setPower(1);
        } else if (gamepad2.left_bumper) {
            flipper.setPower(-1);
        } else {
            flipper.setPower(0);
        }
    }

    //===========================armservo--------------------------------------
    //uses arm and servo together
    public void armServo(int ae) {
        //Set to gamepad 2 dpad, could change
        if (gamepad2.dpad_left) {
            //reset encoders for arm motor
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            //accept the target position asked for in the function
            arm.setTargetPosition(ae);
            //tell arms to move to target position
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //set the power to get to the target
            arm.setPower(0.7);
            arm2.setPower(-0.7);
            //give the motors 50 milli seconds to make sure they are at the target
            while (arm.isBusy()) {


                //Stop and reset encoder when hit the target, it is redundant but ok
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                //move the bucket to the needed angle
                bucket.setPosition(0.5);
                //turn on fipper to spit out block
                //flipper.setPower(1);
                //leave the flipper on for 70 milliseconds
                //wait(70);
                //turn off the flipper
                //flipper.setPower(0);
                //Turn off arm
                arm.setPower(0);
                arm2.setPower(0);
            }
        }
    }

        //===============================encoded arm with button chooser==========
        public void EncoderControl ( int encoder, boolean button){
            if (button) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());

                telemetry.update();

                arm.setTargetPosition(encoder);
                arm2.setTargetPosition(encoder);


                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                arm.setPower(.7);
                arm2.setPower(.1);


                telemetry.addData("Encoder value", arm.getCurrentPosition());
                telemetry.addData("Encoder value", arm2.getCurrentPosition());


                telemetry.update();
                //could add servo controls like bucket.setPosition(1); and flipper.setPower(1); maybe
                //maybe needs this to not stall motor?
                //arm.setPower(0);

                //arm2.setPower(0);
            }
        }

    }

