package cellsociety;

/**
 * @author Muazzam Khan-Noorpuri
 * <p>
 * Purpose - The UnexpectedGridDimensionsException.java class is responsible for throwing an exception
 * with a given message when the expected row,column values for a grid are not equal to the actual
 * dimensions of the given setup in the csv file
 * <p>
 * Assumptions - N/A
 * <p>
 * Dependencies - N/A
 */
public class UnexpectedGridDimensionsException extends Exception {

  /**
   * Constructor, setup Exception with given message
   * @param msg
   */
  public UnexpectedGridDimensionsException(String msg) {
    super(msg);
  }
}
