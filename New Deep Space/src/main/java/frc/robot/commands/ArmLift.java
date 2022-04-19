// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmLift extends CommandBase {
  private ArmSubsystem arm;
  private PS4Controller logitech;

  /** Creates a new ArmLift. */
  public ArmLift(ArmSubsystem arm, PS4Controller logitech) {
    this.arm = arm;
    this.logitech = logitech;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // shawty triflin
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double deadband = -1 * MathUtil.applyDeadband(logitech.getRawAxis(1), 0.15);
    System.out.println(deadband);
    
    if (deadband > 0) {
      arm.armUp(deadband);
    } else if (deadband < 0) {
      arm.armDown(deadband);
    } else {
      arm.armStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.armStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
