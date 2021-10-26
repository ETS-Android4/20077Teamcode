package org.firstinspires.ftc.teamcode.RobotData;



        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.hardware.Servo;

        import java.text.DecimalFormat;

@TeleOp(name="ServoSetter")

public class ServoSetter extends OpMode {

    public Servo servo;
    private double position=0;
    private boolean buttonDown=false;
    DecimalFormat df;
    @Override
    public void init() {
        servo=hardwareMap.servo.get("set");
        servo.setPosition(position);
        df= new DecimalFormat();
        df.setMaximumFractionDigits(2);
    }

    @Override
    public void loop() {
        if(gamepad1.x)
        {
            if(!buttonDown) {
                position -= 0.01;
                servo.setPosition(position);
            }
            buttonDown=true;
        }
        else if(gamepad1.b)
        {
            if(!buttonDown) {
                position += 0.01;
                servo.setPosition(position);
            }
            buttonDown=true;
        }
        else if(gamepad1.y)
        {
            if(!buttonDown) {
                position -= 0.05;
                servo.setPosition(position);
            }
            buttonDown=true;
        }
        else if(gamepad1.a)
        {
            if(!buttonDown) {
                position += 0.05;
                servo.setPosition(position);
            }
            buttonDown=true;
        }
        else
        {
            buttonDown=false;
        }

        telemetry.addData("position: ",df.format(position));
    }
}
