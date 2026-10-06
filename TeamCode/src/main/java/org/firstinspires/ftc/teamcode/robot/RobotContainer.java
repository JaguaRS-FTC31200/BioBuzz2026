package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.core.lib.gamepad.SmartGamepad;
import org.firstinspires.ftc.teamcode.core.lib.internal.RobotContainerInternal;
import org.firstinspires.ftc.teamcode.robot.subsystems.Conveyor;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;

/** Central robot container responsible for subsystem and control management. */
public class RobotContainer extends RobotContainerInternal {
  private final SmartGamepad driver;
  private final SmartGamepad operator;

  private final Drivetrain drivetrain;

  private final Intake intake;

  private final Conveyor conveyor;

  public RobotContainer(Gamepad driverGamepad, Gamepad operatorGamepad) {
    super(Drivetrain.getInstance(), Intake.getInstance(), Conveyor.getInstance());

    this.driver = new SmartGamepad(driverGamepad);
    this.operator = new SmartGamepad(operatorGamepad);

    drivetrain = Drivetrain.getInstance();
    intake = Intake.getInstance();
    conveyor = Conveyor.getInstance();
  }

  @Override
  public void configureBindings() {

    // XDrive Controls
    driver
        .leftY()
        .or(driver.leftX())
        .or(driver.rightX())
        .whileTrue(
            () -> drivetrain.drive(-driver.getLeftY(), -driver.getLeftX(), -driver.getRightX()))
        .onFalse(drivetrain::stop);

    // intake controls: X to turn on/off
    driver.x().onTrue(intake::activate).onFalse(intake::deactivate);

    // Conveyor controls Bumper left to let ball off; Bumber right to Take balls in.
    driver
        .leftBumper()
        .and(driver.rightBumper().negate())
        .whileTrue(
            () -> {
              conveyor.startMotor();
              conveyor.setDirection(-1);
            })
        .onFalse(conveyor::stopMotor);
    driver
        .rightBumper()
        .and(driver.leftBumper().negate())
        .whileTrue(
            () -> {
              conveyor.startMotor();
              conveyor.setDirection(1);
            })
        .onFalse(conveyor::stopMotor);
  }
}
