package org.firstinspires.ftc.teamcode.match;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Disabled
@Autonomous
public class RedRR extends LinearOpMode {
    private CRServo flipper;
    private Servo bucket;
    private DcMotor duck;
    private DcMotor arm;

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        flipper = hardwareMap.get(CRServo.class, "flipper");
        bucket = hardwareMap.get(Servo.class, "bucket");
        arm = hardwareMap.get(DcMotor.class, "arm");
        duck = hardwareMap.get(DcMotor.class, "duck");

        Pose2d startPose = new Pose2d(-30, -60, 90);

        drive.setPoseEstimate(startPose);

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .splineTo(new Vector2d(-15.0, -42.0), 140.0)//Move to goal
                //.turn(Math.toRadians(90))
                .addTemporalMarker(() -> arms(-3000)) // raise arm
                .waitSeconds(.5)
                .addTemporalMarker(() -> bucket.setPosition(.4)) // Lower servo
                .waitSeconds(.5)
                .addTemporalMarker(() -> flipper.setPower(-1)) // flipper
                .waitSeconds(1)
                .addTemporalMarker(() -> flipper.setPower(0)) // Lower servo
                .addTemporalMarker(() -> arms(-100)) // Lower arm

                .splineTo(new Vector2d(-60.0, -68.0), 0.0)//move to duck
                .addTemporalMarker(()-> duck.setPower(-1))
                .waitSeconds(2.0)
                .addTemporalMarker(()-> duck.setPower(0))
                /*.turn(Math.toRadians(45))
                //.forward(10)
                //.strafeRight(5)
                //.turn(Math.toRadians(90))
                .strafeLeft(25)
                .waitSeconds(1)
                .splineToLinearHeading(new Pose2d(-10, -10, Math.toRadians(45)), 0)*/
                .forward(110)
                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
    //===================Arms==================
    public void arms(int encod) {
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setTargetPosition(encod);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        arm.setPower(1);

        while (arm.isBusy()) {
            sleep(50);
        }

    }
    //==========================duck
    public void duck(int time){
        duck.setPower(-.4);
        sleep(time);
        duck.setPower(0);




    }
}