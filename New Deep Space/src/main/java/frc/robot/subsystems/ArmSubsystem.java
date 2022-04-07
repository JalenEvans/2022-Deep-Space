// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */

  private WPI_TalonSRX liftLeader;
  private WPI_TalonSRX liftFollower;
  private static double maxWristAngle = 187;
  private static double minWristAngle = 90;
  private static double wobble = 10;

  private double holdPosition;

  DigitalInput limitSwitch1;
  DigitalInput limitSwitch2;
  DigitalInput limitSwitchStop; 

  private boolean override;

  public ArmSubsystem() {
    liftLeader = new WPI_TalonSRX(7);
    liftFollower = new WPI_TalonSRX(8);

    liftFollower.follow(liftLeader);

    override = true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void liftUp(double speed) {
    if (override){
      liftLeader.set(speed * -1.0);
    } else {
      if (limitSwitch1.get()){
        liftLeader.set(speed * -1.0);
        holdPosition = getLiftEncoders();
      } else {
        liftStop();
      }
    }
  }

  public void liftDown(double speed) {
    if (override){
      liftLeader.set(speed);
    }else {    
      if (limitSwitch2.get()){
        liftLeader.set(speed);
        holdPosition = getLiftEncoders();
      
      }else {
        liftStop();
      }
    }
  }

  private double getLiftEncoders() {
    return liftLeader.getSensorCollection().getQuadraturePosition();
  }

  public void liftStop() {
    liftLeader.set(0);
  }
}
