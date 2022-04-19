// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmLift;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Outtake;
import frc.robot.commands.Intake;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeOuttake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private XboxController xbox;
  private PS4Controller logitech;

  private JoystickButton intakeBtn;
  private JoystickButton outtakeBtn;
  private JoystickButton slowModeButton;

  private DriveSubsystem drivetrain;
  private IntakeOuttake intakeOuttake;
  private ArmSubsystem arm;

  private DriveWithJoystick driveWithJoystick;
  private Intake intake;
  private Outtake outtake;
  private ArmLift armLift;
  private InstantCommand toggleSlowMode;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    xbox = new XboxController(0);
    logitech = new PS4Controller(1);

    intakeBtn = new JoystickButton(logitech, PS4Controller.Button.kL1.value);
    outtakeBtn = new JoystickButton(logitech, PS4Controller.Button.kR1.value);
    slowModeButton = new JoystickButton(xbox, 4);

    drivetrain = new DriveSubsystem();
    intakeOuttake = new IntakeOuttake();
    arm = new ArmSubsystem();

    driveWithJoystick = new DriveWithJoystick(drivetrain, xbox);
    intake = new Intake(intakeOuttake);
    outtake = new Outtake(intakeOuttake);
    armLift = new ArmLift(arm, logitech);
    toggleSlowMode = new InstantCommand(drivetrain::toggleSlowMode, drivetrain);

    drivetrain.setDefaultCommand(driveWithJoystick);
    //arm.setDefaultCommand(armLift);

    SmartDashboard.putData(arm);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Operator controls
    intakeBtn.whileHeld(intake);
    outtakeBtn.whileHeld(outtake);
    slowModeButton.whenPressed(toggleSlowMode);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
