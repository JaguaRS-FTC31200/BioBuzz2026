package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.core.lib.interfaces.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Constants;

public class Shooter implements Subsystem {
    private static Shooter instance;
    public DcMotor shooterMotor;
    private int motorSpeed = 0;
    @Override
    public void initialize(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotor.class, Constants.Shooter.SHOOTER_MOTOR_NAME);
    }

    @Override
    public void start() {}

    @Override
    public void stop() {}

    @Override
    public void execute() {}

    public static synchronized Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    public void startMotor() {
        shooterMotor.setPower(motorSpeed);
    }

    public void stopMotor() {
        shooterMotor.setPower(0);
    }

    public void speedUp() {
        motorSpeed ++;
    }

    public void slowDown(){
        motorSpeed --;
    }
}
