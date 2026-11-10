package org.firstinspires.ftc.teamcode.robot.Autonomous.WithSleep;

import Ori.Coval.Logging.Logger.KoalaLog;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.core.lib.gamepad.Trigger;
import org.firstinspires.ftc.teamcode.robot.Constants;
import org.firstinspires.ftc.teamcode.robot.subsystems.Conveyor;
import org.firstinspires.ftc.teamcode.robot.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.robot.subsystems.Intake;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;

@Autonomous(group = "Autonomous COM Sleep", name = "Aliança vermelha SLEEPY | robo no lado DIREITO")
public class AutonomousRedRightSleepy extends com.qualcomm.robotcore.eventloop.opmode.LinearOpMode {
  private Shooter shooter;
  private Conveyor conveyor;
  private Intake intake;

  private Drivetrain drivetrain;
  Trigger andado;
  Trigger stopOpMode;
  private ElapsedTime runtime;

  @Override
  public void runOpMode() throws InterruptedException {
    Trigger.clearAll();
    shooter = Shooter.getInstance();
    conveyor = Conveyor.getInstance();
    intake = Intake.getInstance();
    drivetrain = Drivetrain.getInstance();

    shooter.initialize(hardwareMap);
    conveyor.initialize(hardwareMap);
    intake.initialize(hardwareMap);
    drivetrain.initialize(hardwareMap);

    conveyor.setDirection(1);

    KoalaLog.setup(hardwareMap);
    runtime = new ElapsedTime();

    waitForStart();
    andado = new Trigger(() -> runtime.milliseconds() > Constants.Timings.TIME_TO_WALK_IN_MS);
    sleep(20123);
    stopOpMode = new Trigger(() -> runtime.seconds() > 7);
    intake.setIntakePower(0.6);
    KoalaLog.start();
    runtime.reset();
    while (opModeIsActive()) {
      Trigger.updateAll();
      execute();
    }
  }

  private void execute() {
    intake.activate(false);
    andado.whileFalse(
        () -> {
          drivetrain.drive(0.45, 0, 0);
        });
    andado.whileTrue(
        () -> {
          drivetrain.drive(0, 0, 0);
          shoot();
        });
    stopOpMode.onTrue(
        () -> {
          drivetrain.drive(0, -0.67, 0);
          shooter.stop();
          intake.stop();
          conveyor.stop();
          sleep(2080);
          requestOpModeStop();
        });
  }

  private void shoot() {
    shooter.execute();
    shooter.spinUp();
    while (!shooter.readyToShoot() && opModeIsActive()) {
      //      System.out.println("Error: " + shooter.getPIDError());
      shooter.execute();
    }

    //    System.out.println("Error: " + shooter.getPIDError());
    //    if (shoow

    conveyor.startMotor();
  }
}
