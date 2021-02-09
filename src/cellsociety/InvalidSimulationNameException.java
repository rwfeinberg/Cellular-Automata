package cellsociety;

/**
 * @author Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The InvalidSimulationNameException.java class is responsible for throwing an exception
 * with a given message when an invalid simulation name is requested
 * <p>
 * Assumptions - N/A
 * <p>
 * Dependencies - N/A
 */
public class InvalidSimulationNameException extends Exception {

  /**
   * Constructor, setup Exception with given message
   * @param msg
   */
  public InvalidSimulationNameException(String msg) {
    super(msg);
  }
}
