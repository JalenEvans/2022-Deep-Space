// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeOuttake extends SubsystemBase {
  private WPI_TalonSRX intakeLeader;
  private WPI_TalonSRX intakeFollow;

  /** Creates a new IntakeOuttake. */
  public IntakeOuttake() {
    intakeLeader = new WPI_TalonSRX(10);
    intakeFollow = new WPI_TalonSRX(11);

    intakeFollow.follow(intakeLeader);

    intakeLeader.configPeakCurrentLimit(35);
    intakeFollow.configPeakCurrentLimit(35);

    intakeLeader.configPeakCurrentDuration(200);
    intakeFollow.configPeakCurrentDuration(200);
  }

  public void spinRollers(double speed) {
    intakeLeader.set(speed);
  }

  public void stopRollers() {
    intakeLeader.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
