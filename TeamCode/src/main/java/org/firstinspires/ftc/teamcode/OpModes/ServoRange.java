



package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ServoRange")
public class ServoRange extends LinearOpMode {

    private Servo flipper;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        flipper = hardwareMap.get(Servo.class, "flipper");
        final double MAX_POS     =  1.0;
         final double MIN_POS     =  0.0;
        // Put initialization blocks here.
        flipper.scaleRange(0.2, 0.8);
        flipper.setPosition(0.5);
        flipper.setDirection(Servo.Direction.FORWARD);
        double  position = (MAX_POS - MIN_POS) / 2;


        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                flipper.setPosition(0.2);
                flipper.setDirection(Servo.Direction.REVERSE);
                sleep(1000);
                flipper.setPosition(0.5);
                flipper.setDirection(Servo.Direction.FORWARD);
                sleep(1000);
                flipper.setPosition(0.2);
                flipper.setDirection(Servo.Direction.REVERSE);
                sleep(1000);
                //telemetry
                telemetry.addData("Servo Position", "%5.2f", position);
                telemetry.addData(">", "Press Stop to end test." );
                telemetry.update();
            }
        }
    }
}
