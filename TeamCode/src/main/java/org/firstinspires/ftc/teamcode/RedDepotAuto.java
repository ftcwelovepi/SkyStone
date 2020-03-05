package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

@Autonomous(name = "RedDepotAuto",group = "SkyStone")
public class RedDepotAuto extends BaseAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        //Initialize the hardware using BaseAutonomous Function
        inithardware(false);


        //Wait for the start button to be pressed
        waitForStart();
//        encoderMecanumDrive(0.9,35,5,0,1);
        robot.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        String location = vuforiaJoint(haddi,buddi);

        telemetry.addData("location", location);
        telemetry.update();




        if (location.equals("Center")){
            //first block
            //strafe and move forward for block setup
//            encoderMecanumDrive(0.75,44,1,-1,0);
//
//            encoderMecanumDrive(0.9,40,5,0,1);
//            gyroTurn(0.7,-90);
//            encoderMecanumDrive(0.9,70,5,-1,0);
//            succ();
//            encoderMecanumDrive(0.5,20,5,0,1);
//            encoderMecanumDrive(0.9, 70,5,1,-0.5);
//            succstop();
//            robot.clamper.setPosition(0.15);
//            encoderMecanumDrive(0.9, 100,5,0,-1);
//            encoderMecanumDrive(0.6, 71.5,5,-1,-0.2);
//            bruh();
//            robot.clamper.setPosition(0.03);
//            verticalEncoder(500);
//            dumbencoderMecanumDrive(0.6,71.5,5,1,0.2,false);
//            verticalEncoder(0);
//            encoderMecanumDrive(0.9, 157,5,0,1);
//            encoderMecanumDrive(0.9, 45,5,-1,0);
//            succ();
//            encoderMecanumDrive(0.5,20,5,0,1);


//            gyroTurn(0.9,-52);
//            succ();
//            //move to collect block
//            encoderMecanumDrive(0.75,75,4,-1,0.95);
//            //fine tune grab block
//            encoderMecanumDrive(1,22.5,4,0.819152,-0.573576);
//            //move back to be able to move to foundation
//            encoderMecanumDrive(1,68,4,0,-1);
//            succstop();
//            robot.clamper.setPosition(0.15);
//            //turn towards foundation
//            gyroTurn(0.9,-85);
//            telemetry.addData("angle", robot.realgyro.getIntegratedZValue());
//            telemetry.update();
//            encoderMecanumDrive(1,90,10,0,-1);
//            //move to foundation
//            bruh();
//            robot.clamper.setPosition(0.03);
//
//            //second block
//
//            //coming back to get second block
//            dumbencoderMecanumDrive(1,180, 4, 0,1,false);
//
//            //strafing sideways
//            encoderMecanumDrive(1,50,10,-1,0);
//            succ();
//            encoderMecanumDrive(0.7,20,10,0,1);
//            gyroTurn(0.9,-85);
//            encoderMecanumDrive(1,130,10,1,-1);
//            succstop();
//            robot.clamper.setPosition(0.15);
//            encoderMecanumDrive(1,132,10,0,-1);
//            bruhbuddi();
//
//
//            //move to park
//            dumbencoderMecanumDrive(1,70, 4, 0,1,false);


        } else if (location.equals("Right")){


            //first block
            //strafe and move forward for block setup
//            encoderMecanumDrive(0.75,8,1,-1,0);
//
//            encoderMecanumDrive(0.9,25,5,0,1);
//            gyroTurn(0.9,-52);
//            succ();
//            //move to collect block
//            encoderMecanumDrive(0.75,75,4,-1,0.95);
//            //fine tune grab block
//            encoderMecanumDrive(1,20,4,0.819152,-0.573576);
//            //move back to be able to move to foundation
//            encoderMecanumDrive(1,73,4,0,-1);
//            succstop();
//            robot.clamper.setPosition(0.15);
//            //turn towards foundation
//            gyroTurn(0.9,-85);
//
//            encoderMecanumDrive(1,108,10,0,-1);
//            //move to foundation
//            bruh();
//            robot.clamper.setPosition(0.03);
//
//            //second block
//
//            //coming back to get second block
//            dumbencoderMecanumDrive(1,200, 4, 0,1,false);
//
//            //strafing sideways
//            encoderMecanumDrive(1,52,10,-1,0);
//            succ();
//            encoderMecanumDrive(0.7,20,10,0,1);
//            gyroTurn(0.9,-86);
//            encoderMecanumDrive(1,123,10,1,-1);
//            succstop();
//            robot.clamper.setPosition(0.15);
//            encoderMecanumDrive(1,160,10,0,-1);
//            bruhbuddi();
//
//
//            //move to park
//            dumbencoderMecanumDrive(1,70, 4, 0,1,false);


        } else {

            //move to block
            encoderMecanumDrive(0.9, 60,5,1,0);
            encoderMecanumDrive(0.4,2.5,1,0,-1);

            rightClamp();

            encoderMecanumDrive(0.5,25,5,1,0);

            bruhhh();
            sleep(500);
            liftClamp();
            encoderMecanumDrive(0.9,20,5,-1,0);
            encoderMecanumDrive(0.9,175,10,0,1);
            encoderMecanumDrive(0.9,31,5,1,0);
            dropThaBlock();
            gyroTurn(0.5,0);
            robot.turnoright.setPosition(0.48);
            encoderMecanumDrive(0.9,31,10,-1,0);
            bencoderMecanumDrive(0.9,240,10,0,-1);
            rightClamp();

            encoderMecanumDrive(0.5,30,5,1,0);

            bruhhh();
            sleep(500);
            liftClamp();
            encoderMecanumDrive(0.9,25,5,-1,0);
            encoderMecanumDrive(0.9,255,10,0,1);
            encoderMecanumDrive(0.9,31,5,1,0);
            dropThaBlock();
            encoderMecanumDrive(0.5,15,5,-1,0);
            gyroTurn(0.7,90);
            encoderMecanumDrive(0.9,15,5,1,0);
            encoderMecanumDrive(0.9,30,5,0,-1);

            grab();
            sleep(1000);
            encoderMecanumDrive(0.9,30,5,0,1);
            gyroCurve(0.7,180,0,0.5);



        }












































//        encoderMecanumDrive(DRIVE_SPEED,60,4,-1,0);
//        encoderMecanumDrive(DRIVE_SPEED,50,4,0,1);
//
//

//
//        if (location.equals("Right")){
//            gyroTurn(TURN_SPEED,45);
//            //encoderMecanumDrive(DRIVE_SPEED,8,2,0.93969,0.34202);
//            succ();
//            encoderMecanumDrive(0.2,67,10,-1,1);
//            succstop();
//            gyroTurn(0.4,90);
//            encoderMecanumDrive(1.0,50,10,0,-1);
//            encoderMecanumDrive(1.0,220,10,-1,0);
//            spit();
//            encoderMecanumDrive(DRIVE_SPEED,55,2,1,0);
//
//        } else if (location.equals("Left")){
//            gyroTurn(TURN_SPEED,120);
//            encoderMecanumDrive(DRIVE_SPEED,6,1,0.8660254,-0.5);
//
//            succ();
//
//            encoderMecanumDrive(0.2,67,10,0.5,0.8660254);
//
//            succstop();
//            gyroTurn(0.4,90);
//            encoderMecanumDrive(1.0,50,10,0,-1);
//            encoderMecanumDrive(1.0,220,10,-1,0);
//            spit();
//            encoderMecanumDrive(DRIVE_SPEED,50,2,1,0);
//        }else if (location.equals("Center")){
//            gyroTurn(TURN_SPEED,90);
//            succ();
//            encoderMecanumDrive(0.2,67,10,0,1);
//            succstop();
//            encoderMecanumDrive(1.0,50,10,0,-1);
//            encoderMecanumDrive(1.0,220,10,-1,0);
//            spit();
//            encoderMecanumDrive(DRIVE_SPEED,55,2,1,0);
//        }





    }
}
