package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore mockedPrimaryStore;
  TorpedoStore mockedSecondaryStore;

  @BeforeEach
  public void init(){

    this.mockedPrimaryStore=mock(TorpedoStore.class);
    this.mockedSecondaryStore=mock(TorpedoStore.class); 

    this.ship = new GT4500(mockedPrimaryStore,mockedSecondaryStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.mockedPrimaryStore,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedSecondaryStore.isEmpty()).thenReturn(false);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    when(mockedSecondaryStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.mockedPrimaryStore,times(1)).fire(1);
    verify(this.mockedSecondaryStore,times(1)).fire(1);
  }

  @Test
  public void fire_Torpedo_With_Empty_Stores(){
    //Arrange

    when(mockedPrimaryStore.isEmpty()).thenReturn(true);
    when(mockedSecondaryStore.isEmpty()).thenReturn(true);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert

    assertEquals(false, result);
  }

  @Test
  public void fire_Two_Torpedos_In_SingleFiring_Mode(){
    //Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedSecondaryStore.isEmpty()).thenReturn(false);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    when(mockedSecondaryStore.fire(1)).thenReturn(true);
    //Act
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert

    assertEquals(true, firstResult);
    assertEquals(true, secondResult);
    verify(this.mockedPrimaryStore,times(1)).fire(1);
    verify(this.mockedSecondaryStore,times(1)).fire(1);
  }


  @Test
  public void fire_Two_Torpedos_In_SingleFiring_Mode_With_Second_Store_Empty(){
    //Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedSecondaryStore.isEmpty()).thenReturn(true);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    when(mockedSecondaryStore.fire(1)).thenReturn(false);
    //Act
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert

    assertEquals(true, firstResult);
    assertEquals(true, secondResult);
    verify(this.mockedPrimaryStore,times(2)).fire(1);
    verify(this.mockedSecondaryStore,times(0)).fire(1);
  }

  @Test
  public void fire_Three_Torpedos_In_SingleFiring_Mode(){
    //Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedSecondaryStore.isEmpty()).thenReturn(false);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    when(mockedSecondaryStore.fire(1)).thenReturn(true);
    //Act
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean thirdResult = ship.fireTorpedo(FiringMode.SINGLE);
    //Assert

    assertEquals(true, firstResult);
    assertEquals(true, secondResult);
    assertEquals(true, thirdResult);
    verify(this.mockedPrimaryStore,times(2)).fire(1);
    verify(this.mockedSecondaryStore,times(1)).fire(1);
  }

  @Test
  public void fire_Torpedos_In_AllFiring_Mode_With_Second_Store_Empty(){
    //Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(false);
    when(mockedSecondaryStore.isEmpty()).thenReturn(true);
    when(mockedPrimaryStore.fire(1)).thenReturn(true);
    when(mockedSecondaryStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert

    assertEquals(true, result);
    verify(this.mockedPrimaryStore,times(1)).fire(1);
    verify(this.mockedSecondaryStore,times(0)).fire(1);
  }

  @Test
  public void fire_Torpedos_In_AllFiring_Mode_With_All_Stores_Empty(){
    //Arrange
    when(mockedPrimaryStore.isEmpty()).thenReturn(true);
    when(mockedSecondaryStore.isEmpty()).thenReturn(true);
    when(mockedPrimaryStore.fire(1)).thenReturn(false);
    when(mockedSecondaryStore.fire(1)).thenReturn(false);
    //Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);
    //Assert

    assertEquals(false, result);
    verify(this.mockedPrimaryStore,times(0)).fire(1);
    verify(this.mockedSecondaryStore,times(0)).fire(1);
  }

}
