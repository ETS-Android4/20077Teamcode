



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
        flipper = hardwareMap.get(Servo.class, "regular servo");

        // Put initialization blocks here.
        flipper.scaleRange(0.2, 0.8);
        flipper.setPosition(0.5);
        flipper.setDirection(Servo.Direction.FORWARD);
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
                telemetry.update();
            }
        }
    }
}
