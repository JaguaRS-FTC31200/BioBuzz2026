package org.firstinspires.ftc.teamcode.robot.subsystems;

import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.teamcode.core.lib.interfaces.Subsystem;
import org.firstinspires.ftc.teamcode.core.lib.pid.PIDController;
import org.firstinspires.ftc.teamcode.robot.Constants;

/**
 * Subsistema para controlar um mecanismo de lançador com controle de velocidade PIDF. Gerado pelo
 * FGCLib Studio.
 */
public class Shooter implements Subsystem {
  private static Shooter instance;
  private DcMotorEx motor1;
  private PIDController pidController;
  private boolean shooterIsActive = false;

  /** Construtor privado para o padrão Singleton */
  protected Shooter() {}

  /** Retorna a instância única do subsistema */
  public static synchronized Shooter getInstance() {
    if (instance == null) {
      instance = new Shooter();
    }
    return instance;
  }

  /** Acelera o motor do lançador até a velocidade alvo. */
  public void spinUp() {
    shooterIsActive = true;
  }

  /** Para o movimento do mecanismo. */
  public void stopShooter() {
    shooterIsActive = false;
  }

  /** Define a potência manual para os motores do mecanismo. */
  public void runMotorPower(double power) {
    shooterIsActive = false;
    motor1.setPower(power);
  }

  /** Atualiza os coeficientes do controlador PID a partir das Constantes. */
  public void updatePID() {
    pidController.setKP(Constants.Shooter.PID.kP);
    pidController.setKI(Constants.Shooter.PID.kI);
    pidController.setKD(Constants.Shooter.PID.kD);
    pidController.setKF(Constants.Shooter.PID.kF);
  }

  /** Inicializa hardware e controladores PID */
  @Override
  public void initialize(HardwareMap hardwareMap) {
    motor1 = hardwareMap.get(DcMotorEx.class, Constants.Shooter.MOTOR_1_NAME);
    motor1.setDirection(
        Constants.Shooter.IS_INVERTED1 ? DcMotor.Direction.REVERSE : DcMotor.Direction.FORWARD);
    motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    pidController =
        new PIDController(
            Constants.Shooter.PID.kP,
            Constants.Shooter.PID.kI,
            Constants.Shooter.PID.kD,
            Constants.Shooter.PID.kF);
    pidController.setTolerance(20.0);
  }

  /** Loop principal de controle, gerenciado pelo GamepadManager */
  @Override
  public void execute() {
    if (shooterIsActive) {
      double currentVelocity = motor1.getVelocity();
      double power = pidController.calculate(Constants.Shooter.TARGET_VELOCITY, currentVelocity);
      motor1.setPower(power);

    } else {
      motor1.setPower(0);
    }
  }

  /** Reseta o estado quando o OpMode inicia */
  @Override
  public void start() {
    shooterIsActive = false;
    stop();
  }

  /** Garante a segurança quando o OpMode para */
  @Override
  public void stop() {
    shooterIsActive = false;
    motor1.setPower(0);
  }
}
