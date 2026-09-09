package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.core.lib.interfaces.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Constants;

/** Subsistema para controlar o sistema de tração (propulsão) do robô. Gerado pelo FGCLib Studio. */
public class Drivetrain implements Subsystem {
  private static Drivetrain instance;
  private DcMotor frontLeft, frontRight, backLeft, backRight;

  /** Construtor privado para o padrão Singleton */
  protected Drivetrain() {}

  /** Retorna a instância única do subsistema */
  public static synchronized Drivetrain getInstance() {
    if (instance == null) {
      instance = new Drivetrain();
    }
    return instance;
  }

  /** Inicializa hardware e controladores PID */
  @Override
  public void initialize(HardwareMap hardwareMap) {
    frontLeft = hardwareMap.get(DcMotor.class, Constants.Drivetrain.FRONT_LEFT_NAME);
    frontRight = hardwareMap.get(DcMotor.class, Constants.Drivetrain.FRONT_RIGHT_NAME);
    backLeft = hardwareMap.get(DcMotor.class, Constants.Drivetrain.BACK_LEFT_NAME);
    backRight = hardwareMap.get(DcMotor.class, Constants.Drivetrain.BACK_RIGHT_NAME);

    frontLeft.setDirection(
        Constants.Drivetrain.IS_FRONT_LEFT_INVERTED
            ? DcMotor.Direction.REVERSE
            : DcMotor.Direction.FORWARD);
    frontRight.setDirection(
        Constants.Drivetrain.IS_FRONT_RIGHT_INVERTED
            ? DcMotor.Direction.REVERSE
            : DcMotor.Direction.FORWARD);
    backLeft.setDirection(
        Constants.Drivetrain.IS_BACK_LEFT_INVERTED
            ? DcMotor.Direction.REVERSE
            : DcMotor.Direction.FORWARD);
    backRight.setDirection(
        Constants.Drivetrain.IS_BACK_RIGHT_INVERTED
            ? DcMotor.Direction.REVERSE
            : DcMotor.Direction.FORWARD);
  }

  /** Loop principal de controle, gerenciado pelo GamepadManager */
  @Override
  public void execute() {
    // XDrive execution runs passively or wait for commands
  }

  /**
   * Move o robô usando coordenadas cartesianas (y: frente/trás, x: translação lateral, rx:
   * rotação).
   */
  public void drive(double y, double x, double rx) {
    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double frontLeftPower = (y + x + rx) / denominator;
    double backLeftPower = (y - x + rx) / denominator;
    double frontRightPower = (y - x - rx) / denominator;
    double backRightPower = (y + x - rx) / denominator;

    frontLeft.setPower(frontLeftPower);
    backLeft.setPower(backLeftPower);
    frontRight.setPower(frontRightPower);
    backRight.setPower(backRightPower);
  }

  /** Reseta o estado quando o OpMode inicia */
  @Override
  public void start() {}

  /** Garante a segurança quando o OpMode para */
  @Override
  public void stop() {
    frontLeft.setPower(0);
    frontRight.setPower(0);
    backLeft.setPower(0);
    backRight.setPower(0);
  }
}
