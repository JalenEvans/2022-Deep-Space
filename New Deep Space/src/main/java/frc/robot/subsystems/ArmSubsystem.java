// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */

  private WPI_TalonSRX armLeader;
  private WPI_TalonSRX armFollow;
  private WPI_TalonSRX wrist;

  private PigeonIMU pigeon;

  private static double maxWristAngle = 187;
  private static double minWristAngle = 90;
  private static double wobble = 10;

  private double holdPosition;

  private DigitalInput limitSwitch1;
  private DigitalInput limitSwitch2;
  private DigitalInput limitSwitchStop; 

  private boolean override;

  public ArmSubsystem() {
    armLeader = new WPI_TalonSRX(7);
    armFollow = new WPI_TalonSRX(8);
    wrist = new WPI_TalonSRX(9);

    armLeader.setInverted(true);

    pigeon = new PigeonIMU(18);

    limitSwitch1 = new DigitalInput(1);
    limitSwitch2 = new DigitalInput(0);

    armFollow.follow(armLeader);

    override = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void armUp(double speed) {
    if (override){
      armLeader.set(speed);
    } else {
      if (limitSwitch1.get()){
        armLeader.set(speed * 0.3);
        holdPosition = getLiftEncoders();
      } else {
        armStop();
      }
    }
  }

  public void armDown(double speed) {
    if (override){
      armLeader.set(speed);
    }else {    
      if (limitSwitch2.get()){
        armLeader.set(speed * 0.3);
        holdPosition = getLiftEncoders();
      }else {
        armStop();
      }
    }
  }

  public void armStop() {
    armLeader.set(0);
  }

  public void wristUp(double speed) {

  }

  public void wristDown(double speed) {
    
  }

  public void wristStop() {
    wrist.set(0);
  }

  private double getLiftEncoders() {
    return armLeader.getSensorCollection().getQuadraturePosition();
  }

  // why we using a gyro INSIDE the thing bruh
  private double getWristAngle() {
    return pigeon.getRoll();
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.addDoubleProperty("Wrist Angle", this::getWristAngle, null);
    builder.addBooleanProperty("Limit Switch 1", limitSwitch1::get, null);
    builder.addBooleanProperty("Limit Switch 2", limitSwitch2::get, null);
  }
}
