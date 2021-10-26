



package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "ServoRange")
public class ServoRange extends LinearOpMode {

    private Servo bucket;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        bucket = hardwareMap.get(Servo.class, "bucket");
        final double MAX_POS     =  1.0;
         final double MIN_POS     =  0.0;
        // Put initialization blocks here.
        bucket.scaleRange(0.2, 0.8);
        bucket.setPosition(0.5);
        bucket.setDirection(Servo.Direction.FORWARD);
        double  position = (MAX_POS - MIN_POS) / 2;


        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                // Put loop blocks here.
                bucket.setPosition(0.2);
                bucket.setDirection(Servo.Direction.REVERSE);
                sleep(1000);
                bucket.setPosition(0.5);
                bucket.setDirection(Servo.Direction.FORWARD);
                sleep(1000);
                bucket.setPosition(0.2);
                bucket.setDirection(Servo.Direction.REVERSE);
                sleep(1000);
                //telemetry
                telemetry.addData("Servo Position", "%5.2f", position);
                telemetry.addData(">", "Press Stop to end test." );
                telemetry.update();
            }
        }
    }
}
