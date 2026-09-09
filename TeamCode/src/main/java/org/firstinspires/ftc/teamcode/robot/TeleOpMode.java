package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Modelo de classe TeleOp para construir modos TeleOp. Esta classe é o seu modo TeleOp principal.
 */
@TeleOp(name = "Not Official TeleOp", group = "TeleOp")
public class TeleOpMode extends OpMode {

  private RobotContainer robot;

  @Override
  public void init() {
    robot = new RobotContainer(gamepad1, gamepad2);
    robot.init(hardwareMap, telemetry);
  }

  @Override
  public void start() {
    robot.start();
  }

  @Override
  public void loop() {
    robot.loop();
  }

  @Override
  public void stop() {
    robot.stop();
  }
}
