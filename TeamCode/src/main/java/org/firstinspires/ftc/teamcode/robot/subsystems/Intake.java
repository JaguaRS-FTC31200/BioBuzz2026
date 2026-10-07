package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.core.lib.interfaces.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Constants;

public class Intake implements Subsystem {
  private static Intake instance;
  private DcMotor intake_motor;

  @Override
  public void initialize(HardwareMap hardwareMap) {

    intake_motor = hardwareMap.get(DcMotor.class, Constants.Intake.MOTOR_NAME);
    intake_motor.setDirection(DcMotorSimple.Direction.REVERSE);
  }

  @Override
  public void start() {}

  @Override
  public void stop() {}

  @Override
  public void execute() {}

  public void activate() {

    intake_motor.setPower(Constants.Intake.INTAKE_SPEED);
  }

  public void deactivate() {

    intake_motor.setPower(0);
  }

  public static Intake getInstance() {
    if (instance == null) instance = new Intake();
    return instance;
  }
}
