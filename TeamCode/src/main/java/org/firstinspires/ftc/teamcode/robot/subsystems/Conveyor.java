package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.core.lib.interfaces.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Constants;

public class Conveyor implements Subsystem {
  private static Conveyor instance;
  public DcMotor conveyorMotor;
  private int motorDirection = -1;

  @Override
  public void initialize(HardwareMap hardwareMap) {
    conveyorMotor = hardwareMap.get(DcMotor.class, Constants.Conveyor.CONVEYOR_NAME);

    conveyorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }

  @Override
  public void start() {}

  @Override
  public void stop() {}

  @Override
  public void execute() {}

  public static synchronized Conveyor getInstance() {
    if (instance == null) {
      instance = new Conveyor();
    }
    return instance;
  }

  public void startMotor() {
    conveyorMotor.setPower(motorDirection);
  }

  public void stopMotor() {
    conveyorMotor.setPower(0);
  }

  public void setDirection(int num) {
    motorDirection = num;
  }
}
