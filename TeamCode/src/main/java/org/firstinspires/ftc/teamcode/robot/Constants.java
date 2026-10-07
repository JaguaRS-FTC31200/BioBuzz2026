package org.firstinspires.ftc.teamcode.robot;

import com.acmerobotics.dashboard.config.Config;

/** Constantes centralizadas do robô. Geradas pelo FGCLib Studio. */
public class Constants {
  @Config
  public static class Intake{
    public static final String MOTOR_NAME = "intake_motor";
    public static double INTAKE_SPEED = 1;
  }

  /**
   * Constantes do subsistema de exemplo. Você pode deletar esta classe se não estiver usando o
   * SubsystemExample.
   */
  public static class SubsystemExample {
    public static final String MOTOR_LEFT = "subsystemExample_motorLeft";
    public static final String MOTOR_RIGHT = "subsystemExample_motorRight";
    public static final String LIMIT_LEFT = "subsystemExample_limitLeft";
    public static final String LIMIT_RIGHT = "subsystemExample_limitRight";

    /** Constantes de sintonia PID para o subsistema de exemplo. */
    public static class PID {
      public static final double kP = 1.8;
      public static final double kI = 0.0;
      public static final double kD = 0.031;
      public static final double kF = 0.1;
    }
  }

  public static class Drivetrain {
    public static final String FRONT_LEFT_NAME = "front_left_motor";
    public static final String FRONT_RIGHT_NAME = "front_right_motor";
    public static final String BACK_LEFT_NAME = "back_left_motor";
    public static final String BACK_RIGHT_NAME = "back_right_motor";
    public static final boolean IS_FRONT_LEFT_INVERTED = true;
    public static final boolean IS_FRONT_RIGHT_INVERTED = false;
    public static final boolean IS_BACK_LEFT_INVERTED = true;
    public static final boolean IS_BACK_RIGHT_INVERTED = false;
  }

  public static class Conveyor {

    public static final String CONVEYOR_NAME = "conveyor_motor";
  }

  @Config
  public static class Shooter {
    public static final String MOTOR_1_NAME = "shooter_motor";
    public static final boolean IS_INVERTED1 = false;

    /** Velocidade alvo que o motor tentará alcançar (ticks/seg). */
    public static int TARGET_VELOCITY = 1500;

    /** Coeficientes de ajuste do controlador PID. */
    @Config
    public static class PID {
      /** (Proporcional) Reage à diferença de velocidade. */
      public static double kP = 0.005;

      /** (Integral) Corrige erros de velocidade pequenos e persistentes. */
      public static double kI = 0;

      /** (Derivativo) Evita oscilação de velocidade. */
      public static double kD = 0;

      /**
       * (Feedforward) Força base aplicada para atingir a velocidade alvo, antes mesmo de haver um
       * erro.
       */
      public static double kF = 0.05;
    }
  }
}
