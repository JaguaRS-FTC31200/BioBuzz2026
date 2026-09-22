package org.firstinspires.ftc.teamcode.robot;

/** Constantes centralizadas do robô. Geradas pelo FGCLib Studio. */
public class Constants {
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

  public static class Conveyor{

    public static final String CONVEYOR_NAME = "conveyor_motor";

  }
}
