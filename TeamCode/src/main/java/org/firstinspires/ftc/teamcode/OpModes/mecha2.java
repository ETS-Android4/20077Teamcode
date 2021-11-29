

package org.firstinspires.ftc.teamcode.OpModes;




        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.CRServo;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorEx;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.Servo;

        import java.text.DecimalFormat;

@TeleOp
public class mecha2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor leftFront = hardwareMap.dcMotor.get("leftFront");
        DcMotor leftRear = hardwareMap.dcMotor.get("leftRear");
        DcMotor rightFront = hardwareMap.dcMotor.get("rightFront");
        DcMotor rightRear = hardwareMap.dcMotor.get("rightRear");
        DcMotorEx arm = (DcMotorEx) hardwareMap.dcMotor.get("arm");
       // DcMotor arm2 = hardwareMap.dcMotor.get("arm2");
        DcMotor duck = hardwareMap.dcMotor.get("duck");
        Servo bucket = hardwareMap.servo.get("bucket");
        CRServo flipper = hardwareMap.crservo.get("flipper");
        double position = 0.5;
        boolean buttonDown = false;
        DecimalFormat df;
        int armPos = 0; //sets arm postion default ot 0
        int pos = 0;
        int pos2 = 0;

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        // arm.setDirection(DcMotorSimple.Direction.REVERSE);

        df = new DecimalFormat();
        df.setMaximumFractionDigits(2); arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        armPos = arm.getCurrentPosition();//stores current postion

        //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
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
//---------------arm
            double armMotorPower = gamepad2.right_trigger - gamepad2.left_trigger;

            if (armMotorPower >0.1){
                armPos += gamepad2.right_trigger * 5;
            }
            if (armMotorPower < -0.1){
                armPos -= gamepad2.left_trigger *5;
            }

            arm.setTargetPosition(armPos);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(0.4);

            //---duck
            if (gamepad2.b) {
                duck.setPower(-0.7);
            } else if (gamepad2.a) {
                duck.setPower(0.7);
            } else {
                duck.setPower(0);
            }
            //---bucket
            if (gamepad2.x) {
                if (!buttonDown) {
                    position -= 0.05;
                    bucket.setPosition(position);
                }
                buttonDown = true;}
             else if (gamepad2.y) {
                if (!buttonDown) {
                    position += 0.05;
                    bucket.setPosition(position);
                }
                buttonDown = true;
            } else {
                buttonDown = false;
            }
            telemetry.addData("position: ", df.format(position));

            if(gamepad2.left_bumper){
                flipper.setPower(1);
            }
            else{
                flipper.setPower(0);
            }
            if(gamepad2.right_bumper){
                flipper.setPower(-1);
            }
            else{
                flipper.setPower(0);
            }
            if(gamepad2.dpad_left) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());


                telemetry.update();



                arm.setTargetPosition(-125);



                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                arm.setPower(.4);


                telemetry.addData("Encoder value", arm.getCurrentPosition());



                telemetry.update();
            }
            if(gamepad2.dpad_right) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());


                telemetry.update();



                arm.setTargetPosition(-250);



                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                arm.setPower(.4);


                telemetry.addData("Encoder value", arm.getCurrentPosition());



                telemetry.update();
            }


            if(gamepad2.dpad_up) {
                telemetry.addData("Encoder value", arm.getCurrentPosition());


                telemetry.update();



                arm.setTargetPosition(-500);


                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                arm.setPower(.7);


                telemetry.addData("Encoder value", arm.getCurrentPosition());



                telemetry.update();
            }


        }
    }
}