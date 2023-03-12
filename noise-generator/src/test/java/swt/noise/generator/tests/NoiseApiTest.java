package swt.noise.generator.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import swt.noise.generator.*;
import swt.noise.generator.impl.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NoiseApiTest {

  private NoiseApi noiseApi;

  @BeforeAll
  void init(){
    noiseApi = new NoiseApiImpl();
  }

  @ParameterizedTest
  @CsvSource({"0,100", "0,0.8", "19,30", "5,10", "-3, -1"})
  void noiseWhenMinMaxCreatesCorrectNumber(double min, double max){
    double rand = noiseApi.noise(min, max);
    assertAll(
      () -> assertTrue(rand > min),
      () -> assertTrue(rand < max));
  }

  @RepeatedTest(3000)
  void noiseCheckValues(){
    double rand = noiseApi.noise(1, 100);
    assertAll(
      () -> assertTrue(rand > 1),
      () -> assertTrue(rand < 100));
  }
}
