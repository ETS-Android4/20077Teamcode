package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MechaDrive")
public class MechaDrive extends OpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor arm;
    private DcMotor duck;
    // private Servo flipper;
    // private DcMotor liftMotor;
    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo
    @Override
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        arm = hardwareMap.get(DcMotor.class,"arm");
        duck = hardwareMap.get(DcMotor.class, "duck");
        //flipper = hardwareMap.get(Servo.class,"flipper");
        //liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
        // rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        // rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        int pos = 0;
        int pos2 = 0;

        telemetry.addData("Say", "Hello Driver");
    }
    @Override
    public void loop() {
        drive();
        arm();
        duck();

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
            arm.setPower( -.4);
        }

        else if(gamepad2.left_trigger>=0.1){
            arm.setPower(.4);
        }
        else {
            arm.setPower(0);
        }


    }
    public void duck(){

        if(gamepad2.right_bumper){
            duck.setPower(/*-gamepad2.right_trigger*/ -.4);
        }

        else if(gamepad2.left_bumper){
            duck.setPower(.4);
        }
        else {
            duck.setPower(0);
        }


    }
}
