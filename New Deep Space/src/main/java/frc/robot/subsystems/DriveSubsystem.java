// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private WPI_TalonSRX leftLeader;
  private WPI_TalonSRX leftFollower1;
  private WPI_TalonSRX leftFollower2;

  private WPI_TalonSRX rightLeader;
  private WPI_TalonSRX rightFollower1;
  private WPI_TalonSRX rightFollower2;

  private boolean isSlowMode = false;

  private DifferentialDrive tankDrive;

  public DriveSubsystem() {
    leftLeader = new WPI_TalonSRX(1);
    leftFollower1 = new WPI_TalonSRX(2);
    leftFollower2 = new WPI_TalonSRX(3);
    
    rightLeader = new WPI_TalonSRX(4);
    rightFollower1 = new WPI_TalonSRX(5);
    rightFollower2 = new WPI_TalonSRX(6);
    
    leftFollower1.follow(leftLeader);
    leftFollower2.follow(leftLeader);

    rightFollower1.follow(rightLeader);
    rightFollower2.follow(rightLeader);

    tankDrive = new DifferentialDrive(leftLeader, rightLeader);

    rightLeader.configPeakCurrentLimit(35);
    rightFollower1.configPeakCurrentLimit(35);
    rightFollower2.configPeakCurrentLimit(35);

    rightLeader.configPeakCurrentDuration(2000);
    rightFollower1.configPeakCurrentDuration(2000);
    rightFollower2.configPeakCurrentDuration(2000);

    leftLeader.configPeakCurrentLimit(35);
    leftFollower1.configPeakCurrentLimit(35);
    leftFollower2.configPeakCurrentLimit(35);

    leftLeader.configPeakCurrentDuration(2000);
    leftFollower1.configPeakCurrentDuration(2000);
    leftFollower2.configPeakCurrentDuration(2000);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double leftSpeed, double rightSpeed) {
    if(isSlowMode)
    {
    tankDrive.tankDrive(leftSpeed * .4, rightSpeed * .4);
    }
    else
    {
    tankDrive.tankDrive(leftSpeed * 0.8, rightSpeed * 0.8);
    }
  }

  public void stopDrive() {
    tankDrive.tankDrive(0, 0);
  }

  public void toggleSlowMode()
  {
    isSlowMode = !(isSlowMode);
  }

  public boolean getSlowMode() {
    return isSlowMode;
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Drivetrain");
    builder.addBooleanProperty  ("Slow Mode", this::getSlowMode, null);
  }
}
