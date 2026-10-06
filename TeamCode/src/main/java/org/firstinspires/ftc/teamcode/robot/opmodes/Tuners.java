package org.firstinspires.ftc.teamcode.robot.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.robot.Constants;
import org.firstinspires.ftc.teamcode.core.lib.gamepad.SmartGamepad;
import org.firstinspires.ftc.teamcode.robot.subsystems.Shooter;

@TeleOp(name = "Tuners", group = "Tuning")
public class Tuners extends LinearOpMode {

    private final String[] components = {"Shooter"};
    private final String[] fields_Shooter = {"KP", "KI", "KD", "KF"};


    private int selectedComponent = 0;
    private int selectedField = 0;
    private String[] currentFields;

    private static final double MIN_INCREMENT = 0.000001;
    private static final double MAX_INCREMENT = 0.1;
    private double increment = 0.001;

    @Override
    public void runOpMode() throws InterruptedException {
        // Inicializa todos os subsistemas
        Shooter.getInstance().initialize(hardwareMap);


        currentFields = getCurrentFields();
        SmartGamepad driver = new SmartGamepad(gamepad1);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            // Trocar Componente
            if (driver.rightBumper().isTrue()) {
                selectedComponent = (selectedComponent + 1) % components.length;
                selectedField = 0;
                currentFields = getCurrentFields();
                sleep(200);
            }
            if (driver.leftBumper().isTrue()) {
                selectedComponent = (selectedComponent - 1 + components.length) % components.length;
                selectedField = 0;
                currentFields = getCurrentFields();
                sleep(200);
            }

            // Trocar Campo
            if (driver.dpadUp().isTrue()) {
                selectedField = (selectedField - 1 + currentFields.length) % currentFields.length;
                sleep(200);
            }
            if (driver.dpadDown().isTrue()) {
                selectedField = (selectedField + 1) % currentFields.length;
                sleep(200);
            }

            // Ajustar Incremento
            if (Math.abs(driver.getLeftY()) > 0.1) {
                increment += -driver.getRightX() * (increment / 2.0);
                increment = Math.max(MIN_INCREMENT, Math.min(MAX_INCREMENT, increment));
            }

            // Ajustar Valor
            String fieldName = currentFields[selectedField];
            double currentValue = getFieldValue(selectedComponent, fieldName);
            if (driver.dpadRight().isTrue()) {
                setFieldValue(selectedComponent, fieldName, currentValue + increment);
                updateSubsystemPID(selectedComponent);
                sleep(100);
            }
            if (driver.dpadLeft().isTrue()) {
                setFieldValue(selectedComponent, fieldName, Math.max(0, currentValue - increment));
                updateSubsystemPID(selectedComponent);
                sleep(100);
            }

            // Executar componente ativo
            switch (selectedComponent) {
                case 0: Shooter.getInstance().execute(); break;

            }

            telemetry.addLine("---------- FGCLib Tuners ----------");
            telemetry.addData("Componente (LB/RB)", "[%d/%d] %s", selectedComponent + 1, components.length, components[selectedComponent]);
            telemetry.addData("Incremento", "%.6f", increment);
            telemetry.addLine();

            for (int i = 0; i < currentFields.length; i++) {
                String prefix = (i == selectedField) ? ">> " : "   ";
                telemetry.addData(prefix + currentFields[i], "%.6f", getFieldValue(selectedComponent, currentFields[i]));
            }
            telemetry.update();
        }
    }

    private void updateSubsystemPID(int componentIndex) {
        switch (componentIndex) {
            case 0: Shooter.getInstance().updatePID(); break;
        }
    }

    private String[] getCurrentFields() {
        switch (selectedComponent) {
            case 0: return fields_Shooter;
            default: return new String[0];
        }
    }

    private double getFieldValue(int componentIndex, String fieldName) {
        switch (componentIndex) {
            case 0: // Shooter
                switch (fieldName) {
                    case "KP": return Constants.Shooter.PID.kP;
                    case "KI": return Constants.Shooter.PID.kI;
                    case "KD": return Constants.Shooter.PID.kD;
                    case "KF": return Constants.Shooter.PID.kF;
                }
                break;

        }
        return 0;
    }

    private void setFieldValue(int componentIndex, String fieldName, double value) {
        switch (componentIndex) {
            case 0: // Shooter
                switch (fieldName) {
                    case "KP": Constants.Shooter.PID.kP = value; break;
                    case "KI": Constants.Shooter.PID.kI = value; break;
                    case "KD": Constants.Shooter.PID.kD = value; break;
                    case "KF": Constants.Shooter.PID.kF = value; break;
                }
                break;

        }
    }
}
