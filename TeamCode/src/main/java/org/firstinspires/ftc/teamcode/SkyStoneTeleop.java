/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


import static org.firstinspires.ftc.teamcode.HardwareSkyStone.TeleOpRunMode;


/**
 * This file provides basic Telop driving for a RoverRuckus robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common RoverRuckus hardware class to define the devices on the robot.
 * All device access is managed through the HardwareRoverRuckus class.
 *
 */

@TeleOp(name="Skystone Teleop: Mecanum", group="RoverRuckus")
public class SkyStoneTeleop extends OpMode{


    // declaring variables


    float bucketLimiter = 1f;

    boolean pastStateX;
    boolean pastStateY;
    boolean pastStateRbumper;
    boolean pastStateLbumper;

    boolean pastStateB;
    int bPresses;

    boolean autolifting;
    boolean horilifting;

    boolean pastNani;
    boolean pastOof;
    boolean pastBruh;
    boolean pastXp;

    boolean spinX;
    boolean spinY;
    boolean clamp;
    boolean unclamp;

    boolean startTheDrop;

    int bin;
    int liftTarget;


    Integer gyroAngle;

    static final double     COUNTS_PER_MOTOR_REV    = 537.6 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.5 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_CM       = 6 ;     // This measurement is more exact than inches
    static final double     COUNTS_PER_CM         = ((COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CM * Math.PI));



    MecanumDriveTrain vroom;


    private ElapsedTime runtime = new ElapsedTime();





    /* Declare OpMode members. */
    HardwareSkyStone robot       = new HardwareSkyStone(false); // use the class created to define a RoverRuckus's hardware


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */


        robot.init(hardwareMap);
        robot.setMode(TeleOpRunMode);

        // initializing the variables


        pastStateX = false;
        pastStateY = false;
        pastStateRbumper = false;
        pastStateLbumper = false;

        pastStateB = false;
        bPresses = 0;
        autolifting = false;
        horilifting = false;

        pastBruh = false;
        pastNani = false;
        pastOof = false;
        pastXp = false;

        gyroAngle = null;

        spinX = false;
        spinY = false;
        clamp = false;
        unclamp = false;
        startTheDrop = false;

        bin = 0;


        vroom = new MecanumDriveTrain(robot, gamepad1,telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Haddi", "Haddi");

        telemetry.update();


    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        stopLift(robot.verticalSlider);
        stopLift(robot.horizontalSlider);


        //Mecanum Drivetrain function to set powers
        vroom.loop();

        if (gamepad2.left_trigger != 0){
            telemetry.addData("bruh","bruh");
            robot.rightclaw.setPower(-1.0);
            robot.leftclaw.setPower(-1.0);
        }else if(gamepad2.right_trigger != 0){
            robot.rightclaw.setPower(1.0);
            robot.leftclaw.setPower(1.0);
        } else{
            robot.rightclaw.setPower(0);
            robot.leftclaw.setPower(0);
        }


        // turn on/off spinner
        if (gamepad2.x && !pastStateX) {
            spinX = !spinX;
            if (spinX) {
                spinY = false;
            }
        }
        pastStateX = gamepad2.x;

        // turn on/off spinner in opposite direction
        if (gamepad2.y && !pastStateY) {
            spinY = !spinY;
            if (spinY) {
                spinX = false;
            }
        }
        pastStateY = gamepad2.y;


        //If you press a, the spinner will stop spinning regardless of its initial state
        if (gamepad2.a) {
            spinY = false;
            spinX = false;
        }

        if (gamepad2.right_bumper) {
            robot.clamper.setPosition(0.9);
        } else if (gamepad2.left_bumper) {
            robot.clamper.setPosition(0.4);
        }

        // spinner limiting and logic
        if (spinX) {
            robot.spinner.setPower(-1.0 * bucketLimiter);
            robot.spinner2.setPower(-1.0 * bucketLimiter);
        } else if (spinY) {
            robot.spinner.setPower(bucketLimiter);
            robot.spinner2.setPower(bucketLimiter);
        } else {
            robot.spinner.setPower(0);
            robot.spinner2.setPower(0);
        }

        if (gamepad2.b && !pastStateB){
            bPresses++;
            runtime.reset();
        }
        pastStateB = gamepad2.b;

        if (runtime.seconds()>0.3){
            if (bPresses!=0){
                startvertlift();
            }
            bPresses = 0;
        }


        if(!autolifting){
            if (gamepad2.left_stick_y > 0.2 && robot.verticalSlider.getCurrentPosition() > -50) {
                telemetry.addData("going", " down");
                robot.verticalSlider.setTargetPosition(-50);
                robot.verticalSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.verticalSlider.setPower(Math.abs(gamepad2.left_stick_y));

            } else if (gamepad2.left_stick_y < -0.2 && robot.verticalSlider.getCurrentPosition() < 2000){
                telemetry.addData("going", " up");
                robot.verticalSlider.setTargetPosition(2000);
                robot.verticalSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.verticalSlider.setPower(Math.abs(gamepad2.left_stick_y));

            } else {

                robot.verticalSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.verticalSlider.setPower(0);

            }

        }

        if (gamepad2.dpad_left && !horilifting){
            startLift(1.0,0,robot.horizontalSlider);
        }

        if (!horilifting){
            if (gamepad2.right_stick_x > 0 ){
                robot.horizontalSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.horizontalSlider.setPower(gamepad2.right_stick_x);

            } else if (gamepad2.right_stick_x < 0 && robot.horizontalSlider.getCurrentPosition() > 0 ){
                robot.horizontalSlider.setTargetPosition(0);
                robot.horizontalSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.horizontalSlider.setPower(gamepad2.right_stick_x);

            } else {
                robot.horizontalSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.horizontalSlider.setPower(0);
            }
        }







//        telemetry.addData("vertical left position:",robot.verticalLeft.getCurrentPosition());
//        telemetry.addData("vertical right position:",robot.verticalRight.getCurrentPosition());
//        telemetry.addData("horizontal position:",robot.horizontal.getCurrentPosition());


        telemetry.addData("vertical encoder",robot.verticalSlider.getCurrentPosition());
        telemetry.addData("horizontal encoder", robot.horizontalSlider.getCurrentPosition());



        telemetry.update();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

    public void startvertlift(){

        switch(bPresses){
            case 1:
                startLift(1.0,400,robot.verticalSlider);
                break;
            case 2:
                startLift(1.0,1000,robot.verticalSlider);
                break;
            case 3:
                startLift(1.0,1480,robot.verticalSlider);
                break;
            case 4:
                startLift(1.0,2000,robot.verticalSlider);
                break;
        }

    }

    public void startLift(double speed, int target, DcMotor input ) {
        if (input.equals(robot.verticalSlider)){
            autolifting = true;
        }

        if (input.equals(robot.horizontalSlider)){
            horilifting = true;
        }


        input.setTargetPosition(target);
        input.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        input.setPower(speed);

    }

    public void stopLift(DcMotor input){
        if (!input.isBusy()){

            if (input.equals(robot.verticalSlider)){
                autolifting = false;
            }

            if (input.equals(robot.horizontalSlider)){
                horilifting = false;
            }

            // Stop all motion;
            input.setPower(0);

            // Turn off RUN_TO_POSITION
            input.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}