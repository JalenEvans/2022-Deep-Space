// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeOuttake;

public class Outtake extends CommandBase {
  private IntakeOuttake intakeOuttake;
  
  /** Creates a new Outtake. */
  public Outtake(IntakeOuttake intakeOuttake) {
    this.intakeOuttake = intakeOuttake;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeOuttake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intakeOuttake.spinRollers(-0.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeOuttake.stopRollers();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
